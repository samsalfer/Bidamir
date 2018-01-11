/* CustomerController.java
 *
 */

package controllers.user;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.EstudioService;
import services.NegacionService;
import services.ResultadoService;
import services.ResumenService;
import services.SentenciaService;
import controllers.AbstractController;
import domain.Essay;
import domain.Estudio;
import domain.Resultado;
import domain.Resumen;
import domain.ResumenXML;
import domain.Sentencia;
import domain.SentenciaXML;
import domain.User;
import forms.EstudioForm;
import forms.NegacionForm;
import forms.ResultadoForm;
import forms.ResumenForm;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

@Controller
@RequestMapping("/user/negacion")
public class UserNegacionController extends AbstractController {
	// Services ---------------------------------------------------------------

	@Autowired
	private EstudioService estudioService;
	
	@Autowired
	private ResumenService resumenService;
	
	@Autowired
	private NegacionService negacionService;


	// Constructors -----------------------------------------------------------

	public UserNegacionController() {
		super();
	}




	@RequestMapping(value="/list")
	public ModelAndView list(){
		ModelAndView result;
		Collection<Resumen> resumenes;
		int userAccountId;
		
		userAccountId=LoginService.getPrincipal().getId();
		
		resumenes = resumenService.findResumenByUser(userAccountId);
		
		
		result = new ModelAndView("resumen/list");
		result.addObject("resumenes", resumenes);
		result.addObject("requestURI", "user/resumen/list.do");
		
		return result;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int resumenId) {
		ModelAndView result;

		resumenService.delete(resumenId);
		result = new ModelAndView("redirect:list.do");
		
		return result;
	}

	
	////Display///////////////////////
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {
		ModelAndView result;
		
		result = new ModelAndView("negacion/display");
		return result;
	}
	
	
	@RequestMapping(value = "/display2", method = RequestMethod.GET)
	public ModelAndView display2(@RequestParam int resumenId) {
		ModelAndView result;
		Resumen resumen;
		resumen = resumenService.findOne(resumenId);
		
		result = new ModelAndView("resumen/display2");
		result.addObject("resumen", resumen);
		return result;
	}
	////////////////////////////////////////
		
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		NegacionForm negacionForm = new NegacionForm();
		
		result = new ModelAndView("negacion/edit");
		result.addObject("negacionForm", negacionForm);
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "subir")
	public ModelAndView sbuir(
			@Valid NegacionForm negacionForm,
			BindingResult bindingResult) {
		ModelAndView result;
		
		

		if (bindingResult.hasErrors()) {
			result = new ModelAndView("negacion/edit");
			result.addObject("negacionForm", negacionForm);
						
		} else {
			try {
				
				String aux;
				aux= negacionService.leerTXT(negacionForm);
				negacionForm.setTexto(aux);
				
				result = new ModelAndView("negacion/edit");
				result.addObject("negacionForm", negacionForm);
				
			} catch (Throwable oops) {
				result = new ModelAndView("negacion/edit");
				result.addObject("negacionForm", negacionForm);

			
				result.addObject("message", "register.commit.error");
		
			}
		}
		return result;

	}
	
	////////////////////////////////////////
	
}