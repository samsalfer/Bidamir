

package controllers.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import controllers.AbstractController;

@Controller
@RequestMapping("/customer")
public class CustomerController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public CustomerController() {
		super();
	}

}