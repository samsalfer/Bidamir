/* CustomerController.java
 *
 */

package controllers.author;

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

import services.AuthorService;
import controllers.AbstractController;
import domain.Author;
import forms.RegisterAuthorForm;

@Controller
@RequestMapping("/author")
public class AuthorController extends AbstractController {
	// Services ---------------------------------------------------------------

	@Autowired
	private AuthorService authorService;


	// Constructors -----------------------------------------------------------

	public AuthorController() {
		super();
	}

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;
		Collection<Author> authors;

		authors = authorService.findAll();

		result = new ModelAndView("author/list");
		result.addObject("authors", authors);
		result.addObject("requestURI", "author/list.do");

		return result;
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int authorId) {
		ModelAndView result;
		Author author;

		author = authorService.findOne(authorId);

		result = new ModelAndView("author/display");
		result.addObject("author", author);

		return result;
	}


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Author author;
		RegisterAuthorForm registerAuthorForm = new RegisterAuthorForm();

		author = authorService.create();
		result = new ModelAndView("author/create");
		result.addObject("author", author);
		result.addObject("registerAuthorForm", registerAuthorForm);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(
			@Valid RegisterAuthorForm registerAuthorForm,
			BindingResult bindingResult) {
		ModelAndView result;
		Boolean contrase�a;

		contrase�a = registerAuthorForm.getPasswordVerificada().equals(
				registerAuthorForm.getPassword());

		if (bindingResult.hasErrors() || !contrase�a
				|| !registerAuthorForm.getCondition()) {
			result = new ModelAndView("author/create");
			result.addObject("registerAuthorForm", registerAuthorForm);
			if (!contrase�a) {
				result.addObject("message", "register.commit.password");
			}
			if (!registerAuthorForm.getCondition()) {
				result.addObject("message", "register.commit.condition");
			}
		} else {
			try {
				Author author = authorService
						.reconstruct(registerAuthorForm);
				authorService.save(author);

				result = new ModelAndView("redirect:/");
			} catch (Throwable oops) {
				result = new ModelAndView("author/create");
				result.addObject("registerAuthorForm", registerAuthorForm);

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

	protected ModelAndView createEditModelAndView(Author author) {
		ModelAndView result;

		result = createEditModelAndView(author, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Author author,
			String message) {
		ModelAndView result;

		result = new ModelAndView("author/create");
		result.addObject("author", author);
		result.addObject("message2", message);

		return result;
	}

}