/* CustomerController.java
 *

 * 
 */

package controllers.administrator;


import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.AuthorService;
import services.ContestService;
import services.EssayService;
import services.OrganiserService;
import services.PublicSessionService;
import controllers.AbstractController;
import domain.Administrator;
import domain.Author;
import domain.Contest;
import domain.Organiser;
import domain.PublicSession;
import forms.RegisterAdministratorForm;

@Controller
@RequestMapping("/administrator") 
public class AdministratorController extends AbstractController {
	// Services ---------------------------------------------------------------

		@Autowired
		private AdministratorService administratorService;
		
		@Autowired
		private ContestService contestService;
		
		@Autowired
		private AuthorService authorService;
		
		@Autowired
		private EssayService essayService;

		@Autowired
		private PublicSessionService publicSessionService;
		
		@Autowired
		private OrganiserService organiserService;
		
	// Constructors -----------------------------------------------------------

	public AdministratorController() {
		super();
	}
		
		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create() {
			ModelAndView result;
			Administrator administrator;
			RegisterAdministratorForm registerAdministratorForm = new RegisterAdministratorForm();


			administrator = administratorService.create();
			result = new ModelAndView("administrator/create");
			result.addObject("administrator", administrator);
			result.addObject("registerAdministratorForm", registerAdministratorForm);
			
			return result;
		}

		@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid RegisterAdministratorForm registerAdministratorForm, BindingResult bindingResult) {
			ModelAndView result;
			Boolean contraseña;
			
			contraseña = registerAdministratorForm.getPasswordVerificada().equals(
					registerAdministratorForm.getPassword());
			
			if (bindingResult.hasErrors() || !contraseña || !registerAdministratorForm.getCondition()) {
				result = new ModelAndView("administrator/create");
				result.addObject("registerAdministratorForm", registerAdministratorForm);
				if (!contraseña) {
					result.addObject("message", "register.commit.password");
				}
				if (!registerAdministratorForm.getCondition()) {
					result.addObject("message", "register.commit.condition");
				}
			} else {
				try {
						Administrator administrator = administratorService.reconstruct(registerAdministratorForm);
						administratorService.save(administrator);
						
					result = new ModelAndView("redirect:/");
				} catch (Throwable oops) {
					result = new ModelAndView("administrator/create");
					result.addObject("registerAdministratorForm", registerAdministratorForm);
	
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

		
		protected ModelAndView createEditModelAndView(Administrator administrator) {
			ModelAndView result;

			result = createEditModelAndView(administrator, null);

			return result;
		}

		protected ModelAndView createEditModelAndView(Administrator administrator, String message) {
			ModelAndView result;

			result = new ModelAndView("administrator/create");
			result.addObject("administrator", administrator);
			result.addObject("message2", message);

			return result;
		}
		
		
		// DASHBOARD
		
		@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
		public ModelAndView listDashboard(){
			ModelAndView result;
			Collection<Contest> allContestOrderBySubmited;
			Collection<Author> authorWithMoreEssays;
			Collection<Author> authorWithMoreEssaysPublished;
			Collection<Author> authorWithLessEssaysPublished;
			Double avgEssaysByAuthor; 
			Double avgContestByOrganiser;
			Collection<Contest> contestDuringLastMonth;
			

			allContestOrderBySubmited = contestService.findAllContestOrderByEssays();
			authorWithMoreEssays = authorService.findAuthorWithMoreEssay();
			authorWithMoreEssaysPublished = authorService.findAuthorWithMorePublishedEssay();
			authorWithLessEssaysPublished = authorService.findUserAuthorLessPublishedEssay();
			avgEssaysByAuthor = essayService.avgEssayForAuthor();
			avgContestByOrganiser = contestService.avgContestForOrganiser();
			contestDuringLastMonth = contestService.findContestWithHoldingDate();
			
			
			
			result = new ModelAndView("administrator/dashboard");
			result.addObject("allContestOrderBySubmited", allContestOrderBySubmited);
			result.addObject("authorWithMoreEssays", authorWithMoreEssays);
			result.addObject("authorWithMoreEssaysPublished", authorWithMoreEssaysPublished);
			result.addObject("authorWithLessEssaysPublished", authorWithLessEssaysPublished);
			result.addObject("avgEssaysByAuthor", avgEssaysByAuthor);
			result.addObject("avgContestByOrganiser", avgContestByOrganiser);
			result.addObject("contestDuringLastMonth", contestDuringLastMonth);

			return result;
		}
		
		
		@RequestMapping(value = "/dashboard1", method = RequestMethod.GET)
		public ModelAndView listDashboard1(){
			ModelAndView result;
			Collection<Object[]> contests;

			

			contests = contestService.selectContestWithPublicSessions();
		
			
			
			
			result = new ModelAndView("administrator/dashboard1");
			result.addObject("contests", contests);

			return result;
		}
		
		@RequestMapping(value = "/dashboard2", method = RequestMethod.GET)
		public ModelAndView listDashboard2(){
			ModelAndView result;
			Collection<Organiser> presidents;

			

			presidents = organiserService.findPresident();
		
			
			
			
			result = new ModelAndView("administrator/dashboard2");
			result.addObject("presidents", presidents);

			return result;
		}
		
		@RequestMapping(value = "/dashboard3", method = RequestMethod.GET)
		public ModelAndView listDashboard3(){
			ModelAndView result;
			Collection<PublicSession> publicSessions;

			

			publicSessions = publicSessionService.findSessionWithChairmanOrder();
		
			
			
			
			result = new ModelAndView("administrator/dashboard3");
			result.addObject("publicSessions", publicSessions);

			return result;
		}
		
}