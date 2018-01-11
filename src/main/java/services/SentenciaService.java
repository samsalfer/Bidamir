package services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SentenciaRepository;
import domain.Resumen;
import domain.ResumenXML;
import domain.Sentencia;
import domain.SentenciaXML;
import domain.User;


@Service
@Transactional
public class SentenciaService {

	// Managed repository
	// -----------------------------------------------------------------------

	@Autowired
	private SentenciaRepository sentenciaRepository;

	// Supporting services
	// -----------------------------------------------------------------------
	
	@Autowired
	private EstudioService estudioService;
	
	@Autowired
	private UserService userService;
	

	// Constructors
	// --------------------------------------------------------------------------------

	// Simple CRUD methods
	// ------------------------------------------------------------------

	public Sentencia create(SentenciaXML sentenciaXML, Resumen resumen){
		Sentencia result;
		
		result = new Sentencia();
		
		result.setHojaEvolucion(sentenciaXML.getHojaEvolucion());
		result.setIdSentencia(sentenciaXML.getIdSentencia());
		result.setTexto(sentenciaXML.getTexto());
		result.setResumen(resumen);
		
		return result;
	}
	
	public Sentencia findOne(int sentenciaId){
		Sentencia result;
		User user;
		
		user = userService.findByPrincipal();
		Assert.notNull(sentenciaId);
				
		result = sentenciaRepository.findOne(sentenciaId);
		
		Assert.isTrue(user.equals(result.getResumen().getUser()));
		
		Assert.notNull(result);
		
		return result;
	}
	
	public void save(Sentencia sentencia){
		Assert.notNull(sentencia);
		
		sentenciaRepository.save(sentencia);
		
		
		
	}
	
	//Others methods
	
	public void delete(int sentenciaId) {
		Sentencia sentencia;
		User user;
		
		user = userService.findByPrincipal();
		Assert.notNull(sentenciaId);
				
		sentencia = findOne(sentenciaId);
		
		Assert.isTrue(user.equals(sentencia.getResumen().getUser()));
		
		Assert.notNull(sentencia);
		
		sentenciaRepository.delete(sentencia);
	
		
	}
	
	
	//Metodo a partir de un resumen XML crea una list

	public void addResumenList(ResumenXML resumenXML, Resumen resumen){
		Sentencia sentencia;
		for(SentenciaXML aux:resumenXML.getSentencias()){
			
			sentencia = create(aux, resumen);
			save(sentencia);
			
		}
		
		
	}

}
