

package controllers.organiser;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ContestService;
import controllers.AbstractController;
import domain.Contest;

@Controller
@RequestMapping("/contest/organiser")
public class ContestOrganiserController extends AbstractController {
	// Services ---------------------------------------------------------------

	@Autowired
	private ActorService actorService;
	@Autowired
	private ContestService contestService;

	// Constructors -----------------------------------------------------------

	public ContestOrganiserController() {
		super();
	}

	@RequestMapping(value = "/my-contests", method = RequestMethod.GET)
	public ModelAndView myContest() {
		ModelAndView result;
		Collection<Contest> contest;

		contest = contestService.findAllContestByOrganiserId();

		result = new ModelAndView("contest/organiser/list");
		result.addObject("contests", contest);
		result.addObject("requestURI", "contest/organiser/my-contests.do");

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int contestId) {
		ModelAndView result;
		Contest contest;
		Boolean canBeDeleted;

		contest = contestService.findOneToEdit(contestId);
		canBeDeleted = contestService.canBeDeleted(contest);
		Assert.isTrue(contestService.canEditTheContest(contest));

		result = createEditModelAndView(contest);
		result.addObject("canBeDeleted", canBeDeleted);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Contest contest, BindingResult bindingResult) {
		ModelAndView result;
		Boolean haHabidoError = false;

		if (bindingResult.hasErrors()) {
			result = createEditModelAndView(contest);
		} else {

			try {

				contestService.save(contest);
				result = new ModelAndView("redirect:my-contests.do");

			} catch (Throwable oops) {
				result = createEditModelAndView(contest, "error");
				haHabidoError = true;
				result.addObject("haHabidoError", haHabidoError);

			}

		}

		return result;
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int contestId) {
		ModelAndView result;
		Contest contest;
		Boolean sepuedeeditar;
		Boolean canPutResult;

		contest = contestService.findOne(contestId);
		sepuedeeditar = contestService.canEditTheContest(contest);
		canPutResult = contestService.canPutResult(contest);

		result = new ModelAndView("contest/organiser/display");
		result.addObject("contest", contest);
		result.addObject("sepuedeeditar", sepuedeeditar);
		result.addObject("canPutResult", canPutResult);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Contest contest;
		Boolean canPutResult = false;

		contest = contestService.create();

		result = new ModelAndView("contest/organiser/edit");
		result.addObject("contest", contest);
		result.addObject("canPutResult", canPutResult);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Contest contest, BindingResult binding) {
		ModelAndView result;

		try {
			contestService.delete(contest);
			result = new ModelAndView("redirect:my-contests.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(contest, "error");
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(Contest contest) {
		ModelAndView result;

		result = createEditModelAndView(contest, null);

		return result;
	}

	@RequestMapping(value = "/putResult", method = RequestMethod.GET)
	public ModelAndView putResult(@RequestParam int contestId) {
		ModelAndView result;
		Contest contest;

		contest = contestService.findOneToEdit(contestId);
		result = new ModelAndView("contest/organiser/putResult");
		result.addObject("contest", contest);

		return result;
	}

	protected ModelAndView createEditModelAndView(Contest contest,
			String message) {
		ModelAndView result;

		result = new ModelAndView("contest/organiser/edit");
		result.addObject("contest", contest);
		result.addObject("message2", message);

		return result;
	}

}