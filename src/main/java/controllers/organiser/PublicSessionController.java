

package controllers.organiser;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ContestService;
import services.OrganiserService;
import services.PublicSessionService;
import controllers.AbstractController;
import domain.Contest;
import domain.Essay;
import domain.Organiser;
import domain.PublicSession;
import forms.PublicSessionForm;

@Controller
@RequestMapping("/publicSession/organiser")
public class PublicSessionController extends AbstractController {

	// Services-----------------------------------------------------------------

	@Autowired
	private PublicSessionService publicSessionService;
	
	@Autowired
	private OrganiserService organiserService;
	
	@Autowired
	private ContestService contestService;
	
	


	// Constructors -----------------------------------------------------------

	public PublicSessionController() {
		super();
	}

	// list ---------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<PublicSession> publicSessions;
		Organiser principal;
		
		principal = organiserService.findByPrincipal();
		publicSessions = publicSessionService.findByChairman(principal.getId());

		result = new ModelAndView("publicSession/list");
		result.addObject("requestURI", "publicSession/organiser/list.do");
		result.addObject("publicSessions", publicSessions);
		result.addObject("principal", principal);

		return result;
	}
	
	@RequestMapping(value = "/listbycontest", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int contestId) {
		ModelAndView result;
		Collection<PublicSession> publicSessions;
		Organiser principal;
		
		principal = organiserService.findByPrincipal();
		publicSessions = publicSessionService.findSessionByContest(contestId);

		result = new ModelAndView("publicSession/list");
		result.addObject("requestURI", "publicSession/organiser/listbycontest.do");
		result.addObject("publicSessions", publicSessions);
		result.addObject("principal", principal);

		return result;
	}
	
	@RequestMapping(value = "/photo/publicSessionId")
	public void renderPhoto(HttpServletResponse response,
			@RequestParam int publicSessionId) throws IOException, SQLException {
		PublicSession publicSession = publicSessionService.findOne(publicSessionId);
		if (publicSession.getPhoto() != null) {
			byte[] photo = publicSession.getPhoto().getBytes(1,
					(int) publicSession.getPhoto().length());
			response.setContentType("image/jpeg");
			response.setContentLength(photo.length);
			response.getOutputStream().write(photo);
			response.getOutputStream().flush();
		}
	}
	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int publicSessionId) {
		ModelAndView result;
		PublicSession publicSession;
		Collection<Essay> essays;
		
		publicSession = publicSessionService.findOne(publicSessionId);
		essays = publicSessionService.findEssaysByPublicSession(publicSessionId);
		boolean check = publicSessionService.checkAddEssays(essays, publicSession.getEssayNumber());
		
		result = new ModelAndView("publicSession/display");
		result.addObject("publicSession", publicSession);
		result.addObject("essays", essays);
		result.addObject("check", check);
		
		return result;
	}
	
	@RequestMapping(value = "/addEssay", method = RequestMethod.GET)
	public ModelAndView addEssay(@RequestParam int publicSessionId) {
		ModelAndView result;
		PublicSession publicSession;
		Collection<Essay> essays;
		
		publicSession = publicSessionService.findOne(publicSessionId);
		essays = contestService.findEssaysWithoutPublicSessionByContest(publicSession.getContest().getId());
		
		result = new ModelAndView("essay/list");
		result.addObject("publicSession", publicSession);
		result.addObject("requestURI", "publicSession/organiser/addEssay.do");
		result.addObject("essays", essays);
		
		return result;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(@RequestParam int essayId, int publicSessionId) {
		ModelAndView result;
		Collection<PublicSession> publicSessions;
		Organiser principal;
		
		publicSessionService.checkAdd(essayId, publicSessionId);
		
		principal = organiserService.findByPrincipal();

		publicSessionService.addEssayToPublicSession(essayId, publicSessionId);
		
		publicSessions = publicSessionService.findByChairman(principal.getId());
		
		result = new ModelAndView("publicSession/list");
		result.addObject("requestURI", "publicSession/organiser/list.do");
		result.addObject("publicSessions", publicSessions);
		
		return result;
	}
	
	
	@RequestMapping(value = "/create")
	public ModelAndView create() {
		ModelAndView result;
		PublicSessionForm publicSessionForm;
		Collection<Contest> contests;
		Collection<Organiser> organisers;
		
		publicSessionForm = new PublicSessionForm();
		contests = contestService.findAll();
		organisers = organiserService.findAll();
		

		result = new ModelAndView("publicSession/create");
		result.addObject("publicSessionForm", publicSessionForm);
		result.addObject("requestURI", "publicSession/organiser/create.do");
		result.addObject("contests", contests);
		result.addObject("organisers", organisers);

		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView saveC(@Valid PublicSessionForm publicSessionForm,
			BindingResult binding) {
		ModelAndView result;
		Boolean imagenType = true;
		Collection<Contest> contests;
		Collection<Organiser> organisers;
		
		contests = contestService.findAll();
		organisers = organiserService.findAll();

		if (publicSessionForm.getPhoto().getSize() != 0) {
			String contentType = publicSessionForm.getPhoto().getContentType()
					.toString();
			imagenType = contentType.contains("image/");
		}
		if (binding.hasErrors() || !imagenType) {
			result = new ModelAndView("publicSession/create");
			result.addObject("publicSessionForm", publicSessionForm);
			result.addObject("contests", contests);
			result.addObject("organisers", organisers);
			if (!imagenType) {
				result.addObject("message", "publicSession.commit.imagenType");
				result.addObject("requestURI", "publicSession/organiser/create.do");
			}
		} else {
			try {
				PublicSession publicSession = publicSessionService.reconstruc(publicSessionForm);
				publicSessionService.checkDate(publicSession);
				publicSessionService.save(publicSession);
				result = new ModelAndView(
						"redirect:/");
				result.addObject("requestURI", "publicSession/organiser/create.do");
			} catch (Throwable oops) {
				result = new ModelAndView("publicSession/create");
				result.addObject("publicSessionForm", publicSessionForm);
				result.addObject("message", "publicSession.commit.error");
				result.addObject("requestURI", "publicSession/organiser/create.do");
				result.addObject("contests", contests);
				result.addObject("organisers", organisers);
			}	
		}
		return result;
	}
	
	
	
	
	
	@RequestMapping(value = "/edit")
	public ModelAndView edit(@RequestParam int publicSessionId) {
		ModelAndView result;
		PublicSessionForm publicSessionForm;
		PublicSession publicSession;
		Collection<Contest> contests;
		Collection<Essay> essayList;
		Collection<Organiser> organisers;
		
		publicSession = publicSessionService.findOneToEdit(publicSessionId);
		publicSessionForm = publicSessionService.publicSessionToForm(publicSession);
		
		contests = new ArrayList<Contest>();
		contests.add(publicSession.getContest());
		essayList = contestService.findEssaysByContest(publicSession.getContest().getId());
		


		organisers = organiserService.findAll();

		result = new ModelAndView("publicSession/edit");
		result.addObject("publicSessionForm", publicSessionForm);
		result.addObject("requestURI", "publicSession/organiser/edit.do");
		result.addObject("contests", contests);
		result.addObject("essayList", essayList);
		result.addObject("organisers", organisers);

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid PublicSessionForm publicSessionForm, BindingResult bindingResult) {
		ModelAndView result;
		Boolean imagenType = true;
		PublicSession publicSession;
		Collection<Organiser> organisers;
		Collection<Contest> contests;
		
		contests = new ArrayList<Contest>();
		contests.add(publicSessionForm.getContest());
		organisers = organiserService.findAll();
		
		if (publicSessionForm.getPhoto() != null) {
			if (publicSessionForm.getPhoto().getSize() != 0) {
				String contentType = publicSessionForm.getPhoto().getContentType()
						.toString();
				imagenType = contentType.contains("image/");
			}
		}
		
		if (bindingResult.hasErrors() || !imagenType) {
			result = new ModelAndView("publicSession/edit");
			result.addObject("publicSessionForm", publicSessionForm);
			result.addObject("contests", contests);
			result.addObject("organisers", organisers);

		} else {
			try {
				publicSession = publicSessionService.reconstruc(publicSessionForm);
				publicSessionService.save(publicSession);
				result = new ModelAndView("redirect:/");
				
			} catch (Throwable oops) {
				result = createEditModelAndView(publicSessionForm,
						"publicSession.commit.error");
				result.addObject("contests", contests);
				result.addObject("organisers", organisers);

			}
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(PublicSessionForm publicSessionForm) {
		ModelAndView result;

		result = createEditModelAndView(publicSessionForm, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(PublicSessionForm publicSessionForm,
			String message) {
		ModelAndView result;


			result = new ModelAndView("publicSession/edit");
			result.addObject(
					"requestURI",
					"publicSession/organiser/edit.do");

		result.addObject("publicSessionForm", publicSessionForm);
		result.addObject("message", message);

		return result;
	}

}
