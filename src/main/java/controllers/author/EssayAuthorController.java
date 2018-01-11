package controllers.author;

import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.ContestService;
import services.EssayService;
import domain.Contest;
import domain.Essay;

@Controller
@RequestMapping("/essay/author")
public class EssayAuthorController {
	
	// Services ---------------------------------------------------------------

		@Autowired
		private EssayService essayService;
		
		@Autowired
		private ContestService contestService;


		// Constructors -----------------------------------------------------------
		
		public EssayAuthorController() {
			super();
		}
		
		
		
		@RequestMapping(value="/submit", method=RequestMethod.GET)
		public ModelAndView submit(@RequestParam int contestId){
			ModelAndView result;
			Essay essay;
			Contest contest;
			
			contest = contestService.findOne(contestId);
			
			essay = essayService.submitEssay(contest);
			
			result = createEditModelAndView(essay);
			
			return result;
		}
		
		//METODO POST/SAVE
		
		@RequestMapping(value="/edit",method=RequestMethod.POST, params="save")
		public ModelAndView save(@Valid Essay essay, BindingResult binding){
			
			ModelAndView result;
			Boolean haHabidoError = false;
					
			if (binding.hasErrors()) {
				result = createEditModelAndView(essay);
			} else {
				try {
					Assert.isTrue(!essay.getIsPublished());
					Assert.isTrue(!essayService.findEssayTitleByContest(essay.getContest().getId()).contains(essay.getTitle()));
					Assert.isTrue(essay.getContest().getDeadlineDate().after(new Date(System.currentTimeMillis()-100)));
					essayService.save(essay);
					result= new ModelAndView("redirect:/contest/list.do");
				} catch (Throwable oops) {
					result= createEditModelAndView(essay, "error");
					haHabidoError = true;
					result.addObject("haHabidoError", haHabidoError);
				}
			}
					
			return result;
		}
		
		@RequestMapping(value="/list")
		public ModelAndView list(@RequestParam int contestId){
			ModelAndView result;
			Collection<Essay> essays;
			int userAccountId;
			Date now;
			
			userAccountId=LoginService.getPrincipal().getId();
			
			essays = essayService.findAuthorEssayByContest(userAccountId, contestId);
			now = new Date(System.currentTimeMillis());
			
			result = new ModelAndView("essay/list");
			result.addObject("essays", essays);
			result.addObject("requestURI", "essay/author/list.do");
			result.addObject("now", now);
			
			return result;
		}
		
		@RequestMapping(value="/edit", method=RequestMethod.GET)
		public ModelAndView edit(@RequestParam int essayId){
			ModelAndView result;
			Essay essay;
			
			essay = essayService.findOne(essayId);
			Assert.notNull(essay);
			
			result = createEditModelAndView(essay);
			
			return result;
		}
		
				

		//Ancillary methods
				
		protected ModelAndView createEditModelAndView(Essay essay) {
			assert essay!= null;
					
			ModelAndView result;

			result = createEditModelAndView(essay, null);
					
			return result;
		}
				
		protected ModelAndView createEditModelAndView(Essay essay, String message) {
					
			ModelAndView result;				

			result = new ModelAndView("essay/edit");
			result.addObject("essay", essay);
			result.addObject("essay", essay);
					
			return result;
		}		
}
