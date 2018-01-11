/* consumerController.java
 *

 */

package controllers.administrator;

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
@RequestMapping("/publicSession/administrator")
public class AdministratorPublicSessionController extends AbstractController {

	// Services-----------------------------------------------------------------

	@Autowired
	private PublicSessionService publicSessionService;
	
	@Autowired
	private OrganiserService organiserService;
	
	@Autowired
	private ContestService contestService;
	
	


	// Constructors -----------------------------------------------------------

	public AdministratorPublicSessionController() {
		super();
	}

	// list ---------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int contestId) {
		ModelAndView result;
		Collection<PublicSession> publicSessions;
		
		publicSessions = publicSessionService.findSessionByContest(contestId);

		result = new ModelAndView("publicSession/list");
		result.addObject("requestURI", "publicSession/administrator/list.do");
		result.addObject("publicSessions", publicSessions);
		
		return result;
	}
	
	

	
}