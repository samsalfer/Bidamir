package services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.sql.rowset.serial.SerialException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import repositories.ResultadoRepository;
import repositories.ResumenRepository;
import domain.Contest;
import domain.DocumentXML;
import domain.Estudio;
import domain.FactualXML;
import domain.Resumen;
import domain.ResumenXML;
import domain.SentenceXML;
import domain.SentenciaXML;
import domain.User;
import domain.BusquedaXML;
import domain.XcopeXML;
import forms.BusquedaForm;
import forms.IndexarForm;
import forms.ResultadoForm;
import forms.ResumenForm;
import domain.DocumentSetXML;


@Service
@Transactional
public class BusquedaService {

	// Managed repository
	// -----------------------------------------------------------------------

	@Autowired
	private ResumenRepository resumenRepository;

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


	
	public List<BusquedaXML> buscar(BusquedaForm busquedaForm) throws SolrServerException, IOException{
		//Mejor dividir este metodo en varios. 
		
		HttpSolrServer server = new HttpSolrServer("http://10.232.31.70:8081/solr/example");
		  SolrQuery query = new SolrQuery();
		  String condiciones="";
		  List<String> lista;	
		  List<String> condicionesString = new ArrayList<String>();
		  List<String> afirmacionesString = new ArrayList<String>();
		  List<String> negacionesString = new ArrayList<String>();
		  List<String> especulacionesString = new ArrayList<String>();
		  //Obtengo query
		  if(busquedaForm.getAfirmacion().equals("")){
			  
			  condiciones="cat:*";
		  }
		  else{
			  lista = new ArrayList<String>(Arrays.asList(busquedaForm.getAfirmacion().split(",")));
			  afirmacionesString.addAll(lista);
			  condiciones+="cat:*"+lista.get(0)+"* ";
			  lista.remove(0);
			  
			  for(String i:lista){
				  condiciones+="&& cat:*"+i+"*";
			  }
		  }
		  
		  if(busquedaForm.getNegacion().equals("")){
			  
			  condiciones+=" && content_type:*";
		  }
		  else{
			  lista = new ArrayList<String>(Arrays.asList(busquedaForm.getNegacion().split(",")));
			  negacionesString.addAll(lista);
			  condiciones+=" && content_type:*"+lista.get(0)+"* ";
			  lista.remove(0);
			  
			  for(String i:lista){
				  condiciones+=" && content_type:*"+i+"*";
			  }
		  }
		  
		  if(busquedaForm.getEspeculacion().equals("")){
			  
			  condiciones+=" && links:*";
		  }
		  else{
			  lista = new ArrayList<String>(Arrays.asList(busquedaForm.getEspeculacion().split(",")));
			  especulacionesString.addAll(lista);
			  condiciones+=" && links:*"+lista.get(0)+"* ";
			  lista.remove(0);
			  
			  for(String i:lista){
				  condiciones+=" && links:*"+i+"*";
			  }
		  }
		  ///
		  ///Realizo query
		  query.setQuery( condiciones);
		  QueryResponse rsp = server.query( query );
		  List<BusquedaXML> result= rsp.getBeans(BusquedaXML.class);
		  
		  
		  //Cortamos la cadena para mostrar una previsualización antes de colorear.
		  String previ="";
		  Integer numChar = 25;
		  Integer indexPalabra;
		  Integer inicio;
		  Integer fin;
		  for (BusquedaXML aux:result){
			  previ="";
			  //Debemos controlar si la afirmacion está vacía, si el texto está en el comienzo o en el final.
			  if(!afirmacionesString.isEmpty()){
				  indexPalabra = aux.getTexto().toLowerCase().indexOf(afirmacionesString.get(0));
				  //Si no coge los extremos lo ponemos con el valor por defecto
				  inicio=indexPalabra-numChar;
				  fin= indexPalabra+numChar;
				  // vemos si los caracteres no cortan el final o principio
				  if(inicio<0)
					  inicio = 0;
				
				  if(fin>aux.getTexto().length())
					  fin=aux.getTexto().length();
					  
				  previ += aux.getTexto().substring(inicio, fin)+"...";
				  aux.setPrevisualizacion(previ);
			  }
			  if(!negacionesString.isEmpty()){
				  indexPalabra = aux.getTexto().toLowerCase().indexOf(negacionesString.get(0));
				  //Si no coge los extremos lo ponemos con el valor por defecto
				  inicio=indexPalabra-numChar;
				  fin= indexPalabra+numChar;
				  // vemos si los caracteres no cortan el final o principio
				  if(inicio<0)
					  inicio = 0;
				
				  if(fin>aux.getTexto().length())
					  fin=aux.getTexto().length();
					  
				  previ += aux.getTexto().substring(inicio, fin)+"...";
				  aux.setPrevisualizacion(previ);
			  }
			  if(!especulacionesString.isEmpty()){
				  indexPalabra = aux.getTexto().toLowerCase().indexOf(especulacionesString.get(0));
				  //Si no coge los extremos lo ponemos con el valor por defecto
				  inicio=indexPalabra-numChar;
				  fin= indexPalabra+numChar;
				  // vemos si los caracteres no cortan el final o principio
				  if(inicio<0)
					  inicio = 0;
				
				  if(fin>aux.getTexto().length())
					  fin=aux.getTexto().length();
					  
				  previ += aux.getTexto().substring(inicio, fin);
				  aux.setPrevisualizacion(previ);
			  }
		
		  }
		  
		  
		  //Compruebo los resultados de los textos en la busqueda para resaltarlos en color de cada tipo
		  
		  for (BusquedaXML aux:result){
			  for (String condicion:afirmacionesString){
				  //pintamos también la previsualización
				 if(condicion.equals(afirmacionesString.get(0))){
					 Integer indiceParcial = aux.getPrevisualizacion().toLowerCase().indexOf(condicion.toLowerCase());
					 while (indiceParcial >=0){				
						 String textoAux = aux.getPrevisualizacion().substring(0, indiceParcial)+"<b>"+aux.getPrevisualizacion().substring(indiceParcial, indiceParcial+condicion.length())+"</b>"+aux.getPrevisualizacion().substring(indiceParcial+condicion.length());
						 aux.setPrevisualizacion(textoAux);
						 indiceParcial = aux.getPrevisualizacion().toLowerCase().indexOf(condicion.toLowerCase(), indiceParcial+condicion.length()+6);
					 
					 }
				}
				 // y ahora el texto
				 Integer indice = aux.getTexto().toLowerCase().indexOf(condicion.toLowerCase());
				 while (indice >=0){				
					 String textoAux = aux.getTexto().substring(0, indice)+"<b>"+aux.getTexto().substring(indice, indice+condicion.length())+"</b>"+aux.getTexto().substring(indice+condicion.length());
				 
					 aux.setTexto(textoAux);
					 indice = aux.getTexto().toLowerCase().indexOf(condicion.toLowerCase(), indice+condicion.length()+6);
				 }
			  }
			  //Repetimos el proceso para la negacion
			  for (String condicion:negacionesString){
					 if(condicion.equals(negacionesString.get(0))){
						 Integer indiceParcial = aux.getPrevisualizacion().toLowerCase().indexOf(condicion.toLowerCase());
						 while (indiceParcial >=0){				
							 String textoAux = aux.getPrevisualizacion().substring(0, indiceParcial)+"<b><font color=red>"+aux.getPrevisualizacion().substring(indiceParcial, indiceParcial+condicion.length())+"</font></b>"+aux.getPrevisualizacion().substring(indiceParcial+condicion.length());
							 aux.setPrevisualizacion(textoAux);
							 indiceParcial = aux.getPrevisualizacion().toLowerCase().indexOf(condicion.toLowerCase(), indiceParcial+condicion.length()+30);
						 
						 }
					}
				 Integer indice = aux.getTexto().toLowerCase().indexOf(condicion.toLowerCase());
				 while (indice >=0){				
					 String textoAux = aux.getTexto().substring(0, indice)+"<b><font color=red>"+aux.getTexto().substring(indice, indice+condicion.length())+"</font></b>"+aux.getTexto().substring(indice+condicion.length());
				 
					 aux.setTexto(textoAux);
					 //para pasar al siguiente indice le sumamos todo el texto que hemos añadido.
					 indice = aux.getTexto().toLowerCase().indexOf(condicion.toLowerCase(), indice+condicion.length()+30);
				 }
			  }
			  
			  for (String condicion:especulacionesString){
					 if(condicion.equals(especulacionesString.get(0))){
						 Integer indiceParcial = aux.getPrevisualizacion().toLowerCase().indexOf(condicion.toLowerCase());
						 while (indiceParcial >=0){				
							 String textoAux = aux.getPrevisualizacion().substring(0, indiceParcial)+"<b><font color=blue>"+aux.getPrevisualizacion().substring(indiceParcial, indiceParcial+condicion.length())+"</font></b>"+aux.getPrevisualizacion().substring(indiceParcial+condicion.length());
							 aux.setPrevisualizacion(textoAux);
							 indiceParcial = aux.getPrevisualizacion().toLowerCase().indexOf(condicion.toLowerCase(), indiceParcial+condicion.length()+31);
						 
						 }
					}
				 Integer indice = aux.getTexto().toLowerCase().indexOf(condicion.toLowerCase());
				 while (indice >=0){				
					 String textoAux = aux.getTexto().substring(0, indice)+"<b><font color=blue>"+aux.getTexto().substring(indice, indice+condicion.length())+"</font></b>"+aux.getTexto().substring(indice+condicion.length());
				 
					 aux.setTexto(textoAux);
					 //para pasar al siguiente indice le sumamos todo el texto que hemos añadido.
					 indice = aux.getTexto().toLowerCase().indexOf(condicion.toLowerCase(), indice+condicion.length()+31);
				 }
			  }
		  }
		  
		  

		  
		return result;
	}
	
	public void deleteIndex() throws SolrServerException, IOException{
		@SuppressWarnings("deprecation")
		HttpSolrServer server = new HttpSolrServer("http://10.232.31.70:8081/solr/example");

		server.deleteByQuery("*:*");
		server.commit();
		
	}
	
	public DocumentSetXML lecturaXML(IndexarForm indexarForm) throws IOException{
		
		DocumentSetXML result=null;
		
		try {
//			ClassLoader classLoader = getClass().getClassLoader();
//			File file = new File(classLoader.getResource("/file2.xml").getFile());
			InputStream file =	 indexarForm.getFile().getInputStream();
				JAXBContext jaxbContext = JAXBContext.newInstance(DocumentSetXML.class);

				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				
				
				result = (DocumentSetXML) jaxbUnmarshaller.unmarshal(file);
				
			  } catch (JAXBException e) {
				e.printStackTrace();
			  }
		
		return result;
		
	}

	
	public void indexarXML(DocumentSetXML documentSet) throws SolrServerException, IOException{
		@SuppressWarnings("deprecation")
		HttpSolrServer server = new HttpSolrServer("http://10.232.31.70:8081/solr/example");
		SolrInputDocument doc1 = new SolrInputDocument();
		Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
		
		for(DocumentXML documentoAux:documentSet.getDocumentos()){
			//aqui añadimos texto completo
			doc1.addField("id", documentoAux.getName());
			doc1.addField("description", documentoAux.getText());
			//Para que aparezcan en las busquedas *:* debe al menos existir el campo
			doc1.addField("content_type","-");
			doc1.addField("cat","-");
			doc1.addField("links","-");


			for(SentenceXML sentenceAux:documentoAux.getSentenceSet().getSentences()){
				
				for(FactualXML factualAux:sentenceAux.getFactuals()){
					doc1.addField("cat", documentoAux.getText().substring(factualAux.getBegin(), factualAux.getEnd()));
				}
				for(XcopeXML xcopeAux:sentenceAux.getXcopes()){
					if(xcopeAux.getType().equals("Negation")){
						doc1.addField("content_type", documentoAux.getText().substring(xcopeAux.getBegin(), xcopeAux.getEnd()));
					}
					else{
						doc1.addField("links", documentoAux.getText().substring(xcopeAux.getBegin(), xcopeAux.getEnd()));
					}
				}
				
			}
			
			docs.add(doc1);
			doc1 = new SolrInputDocument();
		}
		
		server.add( docs );
		server.commit();
		
		  UpdateRequest req = new UpdateRequest();
		  req.setAction( UpdateRequest.ACTION.COMMIT, false, false );
		  req.add( docs );
		  UpdateResponse rsp = req.process( server );
	}
	
}
