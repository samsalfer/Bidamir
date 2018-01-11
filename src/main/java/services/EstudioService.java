package services;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.rowset.serial.SerialException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

import repositories.EstudioRepository;
import domain.Contest;
import domain.Estudio;
import domain.Resumen;
import domain.ResumenXML;
import domain.Sentencia;
import domain.SentenciaXML;
import domain.User;
import forms.EstudioForm;
import forms.EstudioForm2;






import java.io.File;

import javax.xml.bind.*;

@Service
@Transactional
public class EstudioService {

	// Managed repository
	// -----------------------------------------------------------------------

	@Autowired
	private EstudioRepository estudioRepository;

	// Supporting services
	// -----------------------------------------------------------------------
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrganiserService organiserService;

	// Constructors
	// --------------------------------------------------------------------------------

	// Simple CRUD methods
	// ------------------------------------------------------------------

	public Estudio create(){
		Estudio result;
		Date now;
		User user;
		
		result = new Estudio();
		now = new Date();
		
		user = userService.findByPrincipal();
		
		result.setCreation_date(now);
		result.setUser(user);
		
		return result;
	}
	
	public Estudio findOne(int estudioId){
		Estudio result;
		User user;
		
		user = userService.findByPrincipal();
		Assert.notNull(estudioId);
				
		result = estudioRepository.findOne(estudioId);
		
		Assert.isTrue(user.equals(result.getUser()));
		
		Assert.notNull(result);
		
		return result;
	}
	
	public void save(Estudio estudio){
		Assert.notNull(estudio);
		estudioRepository.save(estudio);
		
		
		
	}
	
	//Others methods

	public Collection<Estudio> findEstudiosByUser(int userAccountId){
		Collection<Estudio> result;
		result = estudioRepository.findEstudioByUser(userAccountId);
		
		return result;
	}
	
	public void delete(int estudioId) {
		Estudio estudio;
		User user;
		
		user = userService.findByPrincipal();
		Assert.notNull(estudioId);
				
		estudio = findOne(estudioId);
		
		Assert.isTrue(user.equals(estudio.getUser()));
		
		Assert.notNull(estudio);
		
		estudioRepository.delete(estudio);
	
		
	}
	
	public Estudio findEstudioByTitle(String title){
		Estudio result;
		result = estudioRepository.findEstudioByTitle(title);
		
		return result;
		
	}
	
	///forms estudio 1
	
	public Estudio reconstruc(EstudioForm estudioForm) throws SerialException,
	SQLException, IOException {

	DateFormat df = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
	Date today = Calendar.getInstance().getTime(); 
	String reportDate = df.format(today);
	
		
	Estudio estudio;
	
	
	if (estudioForm.getEstudioId() == 0) {
		estudio = create();
	

	
		
	estudio.setTitle(estudioForm.getTitle());
	
	estudio.setTipe(estudioForm.getTipe());
	estudio.setAlgoritmo(estudioForm.getAlgoritmo());
	estudio.setPath(estudio.getUser().getUserAccount().getUsername()+"_"+reportDate+""+estudioForm.getFile().getOriginalFilename());
	
	estudio.setFinalizado(false);
	
	estudio.setExcel(readExcel(estudioForm.getFile()));
	enviardatos(estudioForm.getFile(), estudio);


	} else {
		
		estudio = findOne(estudioForm.getEstudioId());
		
		estudio.setTitle(estudioForm.getTitle());
		
		estudio.setTipe(estudioForm.getTipe());
		estudio.setPath(estudioForm.getPath());
		
		
	}
	
	return estudio;
}
	
	///Convierte de form a estudio forms estudio pagina 2
	
	public Estudio reconstruc2(EstudioForm2 estudioForm) throws SerialException,
	SQLException, IOException {

	Estudio estudio;
	
	
	
		
	estudio = findOne(estudioForm.getEstudioId());
	
		
	estudio.setLabel(estudioForm.getLabel());
	estudio.setAtributosSeleccionados(estudioForm.getAtributosSeleccionados());
		
		
	
	
	return estudio;
}
	
	///Paso de estudio a form (No utilizado)
	

	public EstudioForm estudioToForm(Estudio estudio) {
		Assert.notNull(estudio);
		EstudioForm res;
		
		res = new EstudioForm();
		
		res.setEstudioId(estudio.getId());
		res.setTitle(estudio.getTitle());
		
		res.setTipe(estudio.getTipe());
		res.setPath(estudio.getPath());
		
		
		return res;
	}

	//Cambio el estado de finalizado a true y a�ado el path2(donde se guarda el modelo)
	public Boolean finalizar(int estudioId) throws IOException{
		
		Estudio estudio;
		Boolean result;
		
		DateFormat df = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date today = Calendar.getInstance().getTime(); 
		String reportDate = df.format(today);
		estudio = findOne(estudioId);
		estudio.setFinalizado(true);
		//quito los espacios
		String path2 = (estudio.getUser().getUserAccount().getUsername()+"_"+reportDate+""+estudio.getTitle()+".mod");
		path2= path2.replace(" ","_");
		
		String atributos = atributosAString(estudio);
		//hago la llamada a la url que crea el modelo y lo guarda en el servidor
		
		String servicio = "";
		
		if(estudio.getAlgoritmo().equals("�rbol de decisi�n")){
			servicio = "WriteModel";
		}
		if(estudio.getAlgoritmo().equals("M�quina de soporte vectorial")){
			servicio = "vectorMachine";
		}
		
		if(estudio.getAlgoritmo().equals("Subgroup Discovery")){
			servicio = "subgroupDiscovery";
		}
		
		if(estudio.getAlgoritmo().equals("Regresi�n Lineal")){
			servicio = "linearRegresion";
		}
		
		URL ficheroUrl = new URL("http://se41sap37:8080/RA/public_process/"+servicio+"?ruta=C%3A%5CDocuments+and+Settings%5Cesalud%5CMis+documentos%5CFTP%5C"+estudio.getPath()+"&objetivo="+estudio.getLabel()+"&atributos="+atributos+"&ruta2=C%3A%5CDocuments+and+Settings%5Cesalud%5CMis+documentos%5CFTP%5C"+path2+"&amp;");
		try{
		BufferedReader in = new BufferedReader(new InputStreamReader(ficheroUrl.openStream()));
		 
		//Cambio el path del excel por el del modelo ya creado.
		estudio.setPath2(path2);
		
		URL ficheroUrl1 = new URL("http://10.232.0.145:8080/RA/public_process/ReadModel?ruta=C%3A%5CDocuments+and+Settings%5Cesalud%5CMis+documentos%5CFTP%5C"+path2+"&amp;width=800&amp;height=600");
		
		
		BufferedReader in1 = new BufferedReader(new InputStreamReader(ficheroUrl1.openStream()));
		
		save(estudio);
		
		result=true;
		}
		catch (Exception e){
			delete(estudio.getId());
			result=false;
		}
		
		return result;
	}
	
	
	//Copia un arcihvo local a un directorio remoto por SSH. 
	public void enviardatos(CommonsMultipartFile cmfile, Estudio estudio) throws IOException{
	    JSch jsch = new JSch();
        Session session = null;
        
        Boolean enviado;
        //recibo dato y paso a file.
        InputStream input = cmfile.getInputStream();
        
        enviado = false;
        
        try {
            session = jsch.getSession("esalud", "10.232.0.145", 23);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword("se41sap37");
            session.connect();

            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            String name = estudio.getPath();
            sftpChannel.put(input, name);
            sftpChannel.exit();
            session.disconnect();
            enviado=true;
        } catch (JSchException e) {
            e.printStackTrace();  
        } catch (SftpException e) {
            e.printStackTrace();
        }
        
        Assert.isTrue(enviado);
        
	}
	
	// LEo las cabeceras del excel del cliente.
	public ArrayList<String> readExcel(CommonsMultipartFile cmfile){
		ArrayList<String> column = new ArrayList<String>();
		Workbook workbook = null;
		
        try
        {
 
        	if(cmfile.getOriginalFilename().endsWith("xlsx")){
            //Create Workbook instance holding reference to .xlsx file
        		workbook = new XSSFWorkbook(cmfile.getInputStream());
        	}
        	else{
        		workbook = new HSSFWorkbook(cmfile.getInputStream());
        	}
            //Get first/desired sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0);
 
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.hasNext(); 
            Row row = rowIterator.next();
             
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                
                
                while (cellIterator.hasNext()) 
                {
                    Cell cell = cellIterator.next();
                
                    //Check the cell type and format accordingly
                    switch (cell.getCellType()) 
                    {
                        case Cell.CELL_TYPE_NUMERIC:
                            column.add(String.valueOf(cell.getNumericCellValue()));
                            break;
                        case Cell.CELL_TYPE_STRING:
                        	column.add(String.valueOf(cell.getStringCellValue()));
                            break;
                    }
                }
            
            workbook.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
		
        
        return column;
	}
	
	
	//////Convierto la lista de atributos seleccionados en String
	
	public String atributosAString(Estudio estudio){
		
		String result="";
		
		result=estudio.getAtributosSeleccionados().get(0);
		///Aunque se repetir� una vez el primer atributo, es un OR
		for (String atributo:estudio.getAtributosSeleccionados()){
			result+="|"+atributo;
		}
		
		return result;
		
	}
	
	//Leer desde servicio rapidminer un txt
	
	public String rapidminerToAPP(int estudioId) throws IOException{
		String result= "";
		Estudio estudio;
		String atributos;

		estudio =  findOne(estudioId);
		atributos = atributosAString(estudio);
		URL ficheroUrl = new URL("http://se41sap37:8080/RA/public_process/pruebaXVAL?ruta=C%3A%5CDocuments+and+Settings%5Cesalud%5CMis+documentos%5CFTP%5C"+estudio.getPath()+"&objetivo="+estudio.getLabel()+"&atributos="+atributos+"&amp;");
		   BufferedReader in = new BufferedReader(new InputStreamReader(ficheroUrl.openStream()));
		 
		   String linea;

		   while ((linea = in.readLine()) != null){
			   Pattern p = Pattern.compile("-----.+&#13;");
			   Matcher m = p.matcher(linea);

			   if(m.matches()){
				   result+=linea.substring(5,linea.length()-5)+"<br>";
			   }
		   }
		 
		   in.close();
		return result;
	}

	
	//Leo para dar formato a la tabla
	
	public String rapidminerToTable(int estudioId) throws IOException{
		String result= "";
		Estudio estudio;

		estudio =  findOne(estudioId);
		URL ficheroUrl = new URL("http://se41sap37:8080/RA/public_process/ReadModel?ruta=C%3A%5CDocuments+and+Settings%5Cesalud%5CMis+documentos%5CFTP%5C"+estudio.getPath2()+"&amp;");
		   BufferedReader in = new BufferedReader(new InputStreamReader(ficheroUrl.openStream()));
		 
		   String linea;
		   while ((linea = in.readLine()) != null){
			 	
				result+=linea.substring(50,linea.length()-30);
			    result = "<div class='table-responsive'><table class='table table-striped table-bordered table-hover'><thead><tr><th style='color:white;' bgcolor='#3c8dbc'>Premise</th><th style='color:white;' bgcolor='#3c8dbc'>Conclusi�n</th><th style='color:white;' bgcolor='#3c8dbc'>Pos</th><th style='color:white;' bgcolor='#3c8dbc'>Neg</th><th style='color:white;' bgcolor='#3c8dbc'>Size</th><th style='color:white;' bgcolor='#3c8dbc'>Coverage</th><th style='color:white;' bgcolor='#3c8dbc'>Precision</th><th style='color:white;' bgcolor='#3c8dbc'>Accuracy</th><th style='color:white;' bgcolor='#3c8dbc'>Blas</th><th style='color:white;' bgcolor='#3c8dbc'>Lift</th><th style='color:white;' bgcolor='#3c8dbc'>Binomial</th><th style='color:white;' bgcolor='#3c8dbc'>WRAcc</th><th style='color:white;' bgcolor='#3c8dbc'>Squared</th><th style='color:white;' bgcolor='#3c8dbc'>Odds</th><th style='color:white;' bgcolor='#3c8dbc'>Odds Ratio</th><th style='color:white;' bgcolor='#3c8dbc'>Length</th></tr></thead><tbody>"+result+"</tbody></table></div>";
		   }
		 
		   in.close();
		return result;
	}	
	
}

