/* CustomerController.java
 *

 * 
 */

package controllers.user;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.BusquedaService;
import services.EstudioService;
import services.ResultadoService;
import services.ResumenService;
import services.SentenciaService;
import controllers.AbstractController;
import domain.BusquedaXML;
import domain.DocumentSetXML;
import domain.Essay;
import domain.Estudio;
import domain.Resultado;
import domain.Resumen;
import domain.ResumenXML;
import domain.Sentencia;
import domain.SentenciaXML;
import domain.User;
import forms.BusquedaForm;
import forms.EstudioForm;
import forms.IndexarForm;
import forms.ResultadoForm;
import forms.ResumenForm;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

@Controller
@RequestMapping("/user/busqueda")
public class UserBusquedaController extends AbstractController {
	// Services ---------------------------------------------------------------

	@Autowired
	private EstudioService estudioService;
	
	@Autowired
	private ResumenService resumenService;
	
	@Autowired
	private BusquedaService busquedaService;


	// Constructors -----------------------------------------------------------

	public UserBusquedaController() {
		super();
	}


	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView buscar() {
		ModelAndView result;
		BusquedaForm busquedaForm = new BusquedaForm();

		
		result = new ModelAndView("busqueda/edit");
		result.addObject("busquedaForm", busquedaForm);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "buscar")
	public ModelAndView buscador(
			@Valid BusquedaForm busquedaForm,
			BindingResult bindingResult) {
		ModelAndView result;
		
		

		if (bindingResult.hasErrors()) {
			result = new ModelAndView("busqueda/edit");
			result.addObject("busquedaForm", busquedaForm);
						
		} else {
			try {
				
				List<BusquedaXML> lista;
				lista= busquedaService.buscar(busquedaForm);
				result = new ModelAndView("busqueda/edit");
				result.addObject("busquedaForm", busquedaForm);
				result.addObject("listaResultados", lista);
				
			} catch (Throwable oops) {
				result = new ModelAndView("busqueda/edit");
				result.addObject("busquedaForm", busquedaForm);

			
				result.addObject("message", "register.commit.error");
		
			}
		}
		return result;

	}
	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView buscar(@RequestParam String id, String texto) {
		ModelAndView result;
		
		result = new ModelAndView("busqueda/display");
		result.addObject("nombre", id);
		result.addObject("texto", texto);

		return result;
	}
	
	@RequestMapping(value = "/indexar", method = RequestMethod.GET)
	public ModelAndView indexar() {
		ModelAndView result;
		IndexarForm indexarForm = new IndexarForm();

		
		result = new ModelAndView("busqueda/indexar");
		result.addObject("indexarForm", indexarForm);
		return result;
	}
	
	@RequestMapping(value = "/indexar", method = RequestMethod.POST, params = "eliminarIndex")
	public ModelAndView borrarIndex(
			@Valid IndexarForm indexarForm,
			BindingResult bindingResult) {
		ModelAndView result;
		
		

		if (bindingResult.hasErrors()) {
			result = new ModelAndView("busqueda/indexar");
			result.addObject("busquedaForm", indexarForm);
						
		} else {
			try {
				
				busquedaService.deleteIndex();
				result = new ModelAndView("busqueda/indexar");
				result.addObject("message2", "estudio.borradoCorrecto");

				
			} catch (Throwable oops) {
				result = new ModelAndView("busqueda/edit");
				result.addObject("busquedaForm", indexarForm);

			
				result.addObject("message", "register.commit.error");
		
			}
		}
		return result;

	}
	
	@RequestMapping(value = "/indexar", method = RequestMethod.POST, params = "indexar")
	public ModelAndView sbuir(
			@Valid IndexarForm indexarForm,
			BindingResult bindingResult) {
		ModelAndView result;
		
		

		if (bindingResult.hasErrors()) {
			result = new ModelAndView("busqueda/indexar");
			result.addObject("indexarForm", indexarForm);
						
		} else {
			try {
				
				DocumentSetXML documentSetXML;
				documentSetXML = busquedaService.lecturaXML(indexarForm);
				busquedaService.indexarXML(documentSetXML);
				
				result = new ModelAndView("busqueda/indexar");
				result.addObject("indexarForm", indexarForm);
				result.addObject("message2", "estudio.indexado");

				
			} catch (Throwable oops) {
				result = new ModelAndView("busqueda/indexar");
				result.addObject("indexarForm", indexarForm);

			
				result.addObject("message", "estudio.faltaIndex");
		
			}
		}
		return result;

	}
}