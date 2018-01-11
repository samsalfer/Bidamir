

package controllers;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ContestService;
import domain.Contest;

@Controller
@RequestMapping("/contest")
public class ContestController extends AbstractController {

	// Constructors -----------------------------------------------------------
	
	public ContestController() {
		super();
	}
	// Services ---------------------------------------------------------------

	@Autowired
	private ContestService contestService;
		

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Contest> contests;
		Date now;

		contests = contestService.findAll();
		now = new Date(System.currentTimeMillis());
		
		result = new ModelAndView("contest/list");
		result.addObject("contests", contests);
		result.addObject("requestURI", "contest/list.do");
		result.addObject("now", now);
		
		return result;
	}
	

	
}