/* WelcomeController.java
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

	// Constructors -----------------------------------------------------------
	
	
	@Autowired
	UserService userService;
	
	public WelcomeController() {
		super();
	}
		
	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/index")
	public ModelAndView index(@RequestParam(required=false, defaultValue="John Doe") String name) {
		ModelAndView result;
		String apellidos;
		
		try{
			name = userService.findByPrincipal().getName();
			apellidos = userService.findByPrincipal().getSurname();
		}
		catch(Exception e){
			name="";
			apellidos="";
		}
		
		
		
				
		result = new ModelAndView("welcome/index");
		result.addObject("name", name);
		result.addObject("apellidos", apellidos);


		return result;
	}
}