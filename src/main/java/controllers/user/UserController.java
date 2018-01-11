/* CustomerController.java
 *

 */

package controllers.user;

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

import services.EstudioService;
import services.ResumenService;
import services.UserService;
import controllers.AbstractController;
import domain.User;
import forms.RegisterUserForm;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {
	// Services ---------------------------------------------------------------

	@Autowired
	private UserService userService;
	@Autowired
	private EstudioService estudioService;
	@Autowired
	private ResumenService resumenService;

	// Constructors -----------------------------------------------------------

	public UserController() {
		super();
	}

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;
		Collection<User> users;

		users = userService.findAll();

		result = new ModelAndView("user/list");
		result.addObject("users", users);
		result.addObject("requestURI", "user/list.do");

		return result;
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int userId) {
		ModelAndView result;
		User user;

		user = userService.findOne(userId);

		result = new ModelAndView("user/display");
		result.addObject("user", user);

		return result;
	}


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		User user;
		RegisterUserForm registerUserForm = new RegisterUserForm();
		user = userService.create();
		result = new ModelAndView("user/create");
		result.addObject("user", user);
		result.addObject("registerUserForm", registerUserForm);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(
			@Valid RegisterUserForm registerUserForm,
			BindingResult bindingResult) {
		ModelAndView result;
		Boolean contraseña;

		contraseña = registerUserForm.getPasswordVerificada().equals(
				registerUserForm.getPassword());

		if (bindingResult.hasErrors() || !contraseña) {
			result = new ModelAndView("user/create");
			result.addObject("registerUserForm", registerUserForm);
			if (!contraseña) {
				result.addObject("message", "register.commit.password");
			}
			
		} else {
			try {
				User user = userService
						.reconstruct(registerUserForm);
				userService.save(user);

				result = new ModelAndView("redirect:/security/login.do?showAccount=true");
			} catch (Throwable oops) {
				result = new ModelAndView("user/create");
				result.addObject("registerUserForm", registerUserForm);

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

	protected ModelAndView createEditModelAndView(User user) {
		ModelAndView result;

		result = createEditModelAndView(user, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(User user,
			String message) {
		ModelAndView result;

		result = new ModelAndView("user/create");
		result.addObject("user", user);
		result.addObject("message2", message);

		return result;
	}

}