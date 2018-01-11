

package controllers.organiser;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ContestService;
import services.OrganiserService;
import controllers.AbstractController;
import domain.Contest;
import domain.Organiser;
import forms.RegisterOrganiserForm;

@Controller
@RequestMapping("/organiser")
public class OrganiserController extends AbstractController {
	// Services ---------------------------------------------------------------

	@Autowired
	private OrganiserService organiserService;

	@Autowired
	private ContestService contestService;

	// Constructors -----------------------------------------------------------

	public OrganiserController() {
		super();
	}

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;
		Collection<Organiser> organisers;

		organisers = organiserService.findAll();

		result = new ModelAndView("organiser/list");
		result.addObject("organisers", organisers);
		result.addObject("requestURI", "organiser/list.do");

		return result;
	}
	
	@RequestMapping(value = "/addorganiser", method = RequestMethod.GET)
	public ModelAndView addorganiser(@RequestParam int contestId, @RequestParam int organiserId ) {
		ModelAndView result;
		Organiser organiserP;
		Organiser organiser;
		Contest contest;
		boolean seHaAnadidoConExito= true;
		organiserP = organiserService.findByPrincipal();
		
		organiser = organiserService.findOne(organiserId);
		contest = contestService.findOneToEdit(contestId);
		
		contest.getOrganisers().add(organiser);
		organiser.getContests().add(contest);
		contestService.saveOrganiser(contest);
		
		result = new ModelAndView("redirect:../contest/organiser/my-contests.do");
		result.addObject("organiser", organiserP);
		result.addObject("seHaAnadidoConExito", seHaAnadidoConExito);
		return result;
	}
	
	@RequestMapping(value = "/selectOrganiser", method = RequestMethod.GET)
	public ModelAndView selectOrganiser(@RequestParam int contestId) {
		ModelAndView result;
		Organiser organiserP;
		Collection<Organiser> organisers;
		organiserP = organiserService.findByPrincipal();
		
		
		organisers = organiserService.findAllOrganisersCanAdd(contestId);
		
		
		result = new ModelAndView("organiser/selectOrganiser");
		result.addObject("organiser", organiserP);
		result.addObject("organisers", organisers);
		result.addObject("contestId", contestId);
		return result;
	}
	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int organiserId) {
		ModelAndView result;
		Organiser organiser;

		organiser = organiserService.findOne(organiserId);

		result = new ModelAndView("organiser/display");
		result.addObject("organiser", organiser);

		return result;
	}


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Organiser organiser;
		RegisterOrganiserForm registerOrganiserForm = new RegisterOrganiserForm();

		organiser = organiserService.create();
		result = new ModelAndView("organiser/create");
		result.addObject("organiser", organiser);
		result.addObject("registerOrganiserForm", registerOrganiserForm);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(
			@Valid RegisterOrganiserForm registerOrganiserForm,
			BindingResult bindingResult) {
		ModelAndView result;
		Boolean contrase�a;

		contrase�a = registerOrganiserForm.getPasswordVerificada().equals(
				registerOrganiserForm.getPassword());

		if (bindingResult.hasErrors() || !contrase�a
				|| !registerOrganiserForm.getCondition()) {
			result = new ModelAndView("organiser/create");
			result.addObject("registerOrganiserForm", registerOrganiserForm);
			if (!contrase�a) {
				result.addObject("message", "register.commit.password");
			}
			if (!registerOrganiserForm.getCondition()) {
				result.addObject("message", "register.commit.condition");
			}
		} else {
			try {
				Organiser organiser = organiserService
						.reconstruct(registerOrganiserForm);
				organiserService.save(organiser);

				result = new ModelAndView("redirect:/");
			} catch (Throwable oops) {
				result = new ModelAndView("organiser/create");
				result.addObject("registerOrganiserForm", registerOrganiserForm);

				if (oops instanceof DataIntegrityViolationException) {
					result.addObject("message",
							"register.commit.duplicatedUsername");
				} else {
					result.addObject("message", "register.commit.error");
				}
			}
		}
		return result;

	}

	protected ModelAndView createEditModelAndView(Organiser organiser) {
		ModelAndView result;

		result = createEditModelAndView(organiser, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Organiser organiser,
			String message) {
		ModelAndView result;

		result = new ModelAndView("organiser/create");
		result.addObject("organiser", organiser);
		result.addObject("message2", message);

		return result;
	}

}