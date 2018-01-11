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
import org.apache.solr.client.solrj.response.QueryResponse;
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
import domain.Estudio;
import domain.Resumen;
import domain.ResumenXML;
import domain.SentenciaXML;
import domain.User;
import domain.BusquedaXML;
import forms.BusquedaForm;
import forms.NegacionForm;
import forms.ResultadoForm;
import forms.ResumenForm;


@Service
@Transactional
public class NegacionService {

	// Managed repository
	// -----------------------------------------------------------------------

// Supporting services
	// -----------------------------------------------------------------------

	
	
	
	public String leerTXT(NegacionForm negacionForm) throws IOException{
		Assert.notNull(negacionForm);
		
		String result;
		
		StringWriter writer = new StringWriter();
		IOUtils.copy(negacionForm.getFile().getInputStream(), writer);
		result = writer.toString();
		
		
		return result;
		
		
		
	}

	
	
	

}
