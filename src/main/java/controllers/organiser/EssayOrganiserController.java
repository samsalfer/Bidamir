

package controllers.organiser;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.EssayService;
import controllers.AbstractController;
import domain.Essay;

@Controller
@RequestMapping("/essay/organiser") 
public class EssayOrganiserController extends AbstractController {
	// Services ---------------------------------------------------------------

		@Autowired
		private EssayService essayService;

		
	// Constructors -----------------------------------------------------------

	public EssayOrganiserController() {
		super();
	}
		
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Essay> essays;

		essays = essayService.findOrganiserEssay();
		
		result = new ModelAndView("essay/list");
		result.addObject("essays", essays);
		result.addObject("requestURI", "essay/organiser/list.do");
		
		return result;
	}
	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int essayId) {
		ModelAndView result;
		Essay essay;

		essay = essayService.findOne(essayId);
		
		result = new ModelAndView("essay/display");
		result.addObject("essay", essay);
		
		return result;
	}
	
	@RequestMapping(value = "/publish", method = RequestMethod.GET)
	public ModelAndView publish(@RequestParam int essayId) {
		ModelAndView result;

		essayService.publish(essayId);
		result = new ModelAndView("redirect:list.do");
		
		return result;
	}
	
	
		
}