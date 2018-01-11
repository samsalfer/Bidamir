

package controllers.notAuthenticated;


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
@RequestMapping("/essay/notAuthenticated") 
public class EssayController extends AbstractController {
	// Services ---------------------------------------------------------------

		@Autowired
		private EssayService essayService;

		
	// Constructors -----------------------------------------------------------

	public EssayController() {
		super();
	}
		
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(Integer contestId) {
		ModelAndView result;
		Collection<Essay> essays;
		
		if(contestId != null ){
			essays = essayService.findPublishedEssayWithContest(contestId);
		}else{
		essays = essayService.findPublishedEssay();
		}
		
			
		result = new ModelAndView("essay/list");
		result.addObject("essays", essays);
		result.addObject("requestUri", "essay/notAuthenticated/list.do");
		
		return result;
		
	}
	
	
	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int essayId) {
		ModelAndView result;
		Essay essay;

		essay = essayService.findOne(essayId);
		
		result = new ModelAndView("essay/display");
		result.addObject("essay", essay);
		
		return result;
	}
		
}