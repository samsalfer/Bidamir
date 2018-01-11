package author;

import java.util.Collection;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AuthorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import services.AuthorService;
import services.ContestService;
import services.EssayService;
import utilities.AbstractTest;
import utilities.PopulateDatabase;
import domain.Author;
import domain.Contest;
import domain.CreditCard;
import domain.Essay;
import forms.RegisterAuthorForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
 "classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AuthorTest extends AbstractTest {

	 @Autowired
	 private AuthorService authorService;
	 
	 @Autowired
	 private EssayService essayService;
	 
	 @Autowired
	 private ContestService contestService;
	 
	 @Autowired
	 private AuthorRepository authorRepository;
	
	 @Before
	 public void setUp() {
	 PopulateDatabase.main(null);
	 }

	//---------------------------------------------------------------------------------------
	// Register to the system as a author -------------------- -----------------------------
	//---------------------------------------------------------------------------------------
			
			@Test
			public void authorRegisterTest(){
				System.out.println("· Test for method: authorRegisterTest()");
				
				RegisterAuthorForm regForm = newRegForm();
				
				Author uReconstruct = authorService.reconstruct(regForm);
				
				Author aux=newAuthor();
				
				/*Comprobamos que los datos no han cambiado al hacer reconstruct*/
				
					Assert.notNull(uReconstruct, "reconstruct() devolvió NULL");
					Assert.isTrue(uReconstruct.getName().equals(aux.getName()), "El nombre no coincide");
					Assert.isTrue(uReconstruct.getSurname().equals(aux.getSurname()), "El apellido no coincide");
					Assert.isTrue(uReconstruct.getEmail().equals(aux.getEmail()), "El email no coincide");
				
				//UAccount
				boolean b=uReconstruct.getUserAccount().getAuthorities().containsAll(aux.getUserAccount().getAuthorities());
					Assert.isTrue(b, "El rol no coincide");
				b=uReconstruct.getUserAccount().getPassword().equals(aux.getUserAccount().getPassword());
					Assert.isTrue(b, "La password no coincide");
				b=uReconstruct.getUserAccount().getUsername().equals(aux.getUserAccount().getUsername());
					Assert.isTrue(b, "El UserName no coincide");
					
				/* Guardamos y comprobamos que todo está correcto */
				authorService.save(uReconstruct);
				
				authenticate("Pacopal");
				Author auxBD = authorService.findByPrincipal();
					Assert.notNull(auxBD, "La consulta devolvió NULL");

					Assert.isTrue(uReconstruct.getName().equals(auxBD.getName()), "El nombre no coincide");
					Assert.isTrue(uReconstruct.getSurname().equals(auxBD.getSurname()), "El apellido no coincide");
					Assert.isTrue(uReconstruct.getEmail().equals(auxBD.getEmail()), "El email no coincide");


				//UAccount
				boolean b2=uReconstruct.getUserAccount().getAuthorities().containsAll(auxBD.getUserAccount().getAuthorities());
					Assert.isTrue(b2, "El rol no coincide");
				b2=uReconstruct.getUserAccount().getPassword().equals(auxBD.getUserAccount().getPassword());
					Assert.isTrue(b2, "La password no coincide");
				b2=uReconstruct.getUserAccount().getUsername().equals(auxBD.getUserAccount().getUsername());
					Assert.isTrue(b2, "El UserName no coincide");
				
				System.out.println("# All ok.");
			}
			
			
			
			//Caso Negativo
			@Test (expected = DataIntegrityViolationException.class)
			public void negAuthorRegisterTest(){
				
				System.out.println("· Test for method: negAuthorRegisterTest()");
				
				RegisterAuthorForm regForm = newRegForm();
				
				Author uReconstruct = authorService.reconstruct(regForm);
				authorService.save(uReconstruct);
				authorRepository.flush();
				authorService.save(uReconstruct);
				authorRepository.flush();
				
				System.out.println("· # All ok.");
			}

			
		// auxiliary methods ------------------------------------------------------

		
		//RegisterAuthorForm
		public RegisterAuthorForm newRegForm(){
			RegisterAuthorForm regF=new RegisterAuthorForm();
			Date now;
			long miliseconds;

			miliseconds = System.currentTimeMillis() - 100000;
			now = new Date(miliseconds);
			CreditCard creditCard = new CreditCard();
			creditCard.setHolderName("Prueba");
			creditCard.setBrandName("Visa");
			creditCard.setNumber("4960251888100228");
			creditCard.setExpirationMonth(12);
			creditCard.setExpirationYear(2017);
			creditCard.setCvv(888);
			
			regF.setCondition(true);
			regF.setEmail("pacopaco@pacopaco.com");
			regF.setName("Paco");
			regF.setSurname("Palotes");
			regF.setPassword("123otravez");
			regF.setPasswordVerificada("123otravez");
			regF.setUsername("Pacopal");
			regF.setPhone("955955955");
			regF.setBirthDate(now);
			regF.setNationality("spain");
			regF.setCreditCard(creditCard);

			return regF;
		}
		
		public Author newAuthor(){
			Date now;
			long miliseconds;

			miliseconds = System.currentTimeMillis() - 100000;
			now = new Date(miliseconds);
			CreditCard creditCard = new CreditCard();
			creditCard.setHolderName("Prueba");
			creditCard.setHolderName("Visa");
			creditCard.setNumber("4960251888100228");
			creditCard.setExpirationMonth(12);
			creditCard.setExpirationYear(2017);
			creditCard.setCvv(888);
			
			UserAccount ua=new UserAccount();
			ua.setUsername("Pacopal");
			ua.setPassword("123otravez");
			Authority authority = new Authority();//Auth
			authority.setAuthority("AUTHOR");//Auth
			ua.getAuthorities().add(authority);//Auth
			
			//USER
			Author aux=new Author();
			aux.setName("Paco"); aux.setSurname("Palotes");aux.setEmail("pacopaco@pacopaco.com"); aux.setPhone("955955955");aux.setBirthDate(now);aux.setNationality("spain");aux.setCreditCard(creditCard);;
			aux.setUserAccount(ua);
			return aux;
		}
		
		
		//Submit an essay test
		
		@Test
		public void submitTest(){
			System.out.println("· Test for method: submit()");
			Essay essay;
			Contest contest;
			
			authenticate("author1");
			
			contest = contestService.findOne(14);
			
			essay=essayService.submitEssay(contest);
			
			essay.setTitle("titleeeee");
			essay.setSummary("summaryyy");
			essay.setContent("conteent");
			
			essayService.save(essay);
			
			unauthenticate();
			
			System.out.println("· # All ok.");
		}
		
		//Submit an essay test negative intentando crear un essay en un contest que no existe
		
		@Test(expected= IllegalArgumentException.class)
		public void submitTestNegative(){
			System.out.println("· Test for method: submit()");
			Essay essay;
			
			authenticate("author1");
			
			essay= essayService.findOne(176);
			
			essay.setTitle("Essay 2");
			
			essayService.save(essay);
			
			unauthenticate();
			
			System.out.println("· # All ok.");			
		}
		
		
		//Buscar los essay de un determinado author y contest
		@Test
		public void findAuthorEssayByContest(){
			System.out.println("· Test for method: findAuthorEssayByContest()");
			Collection<Essay> essays;
			int contestId;
			int userAccountId;
			
			authenticate("author1");
			
			contestId = contestService.findOne(14).getId();
			userAccountId = LoginService.getPrincipal().getId();
			
			essays = essayService.findAuthorEssayByContest(userAccountId, contestId);
			
			Assert.notNull(essays);
			
			unauthenticate();
			
			System.out.println("· # All ok.");		
		}
		
		
		//Buscar los essays de un contest y un author que no existe
		
		@Test(expected= IllegalArgumentException.class)
		public void findAuthorEssayByContestNegative(){
			System.out.println("· Test for method: findAuthorEssayByContest()");
			Collection<Essay> essays;
			int contestId;
			int userAccountId;

			authenticate("author1");

			contestId = contestService.findOne(123).getId();
			userAccountId = 879978978;

			essays = essayService.findAuthorEssayByContest(userAccountId, contestId);

			Assert.notNull(essays);
			unauthenticate();

			System.out.println("· # All ok.");

		}
		
		//Buscar los titulos de los essays de un determinado contest
		@Test
		public void findEssayTitleByContest(){
			System.out.println("· Test for method: findEssayTitleByContest()");
			Collection<String> titles;
			int contestId;
			
			contestId = contestService.findOne(14).getId();
			
			titles = essayService.findEssayTitleByContest(contestId);
			
			Assert.notNull(titles);
			
			System.out.println("· # All ok.");
		}
		
		//Buscar los titulos de los essays de un contest que no existe		
		@Test(expected = IllegalArgumentException.class)
		public void findEssayTitleByContestNegative(){
			System.out.println("· Test for method: findEssayTitleByContest()");
			Collection<String> titles;
			int contestId;
			
			contestId = contestService.findOne(123).getId();
			
			titles = essayService.findEssayTitleByContest(contestId);
			
			Assert.notNull(titles);
			
			System.out.println("· # All ok.");
		}
		
	
		//probamos que muestra todos los essays de un organizador
		@Test
		public void findOrganiserEssay() {
			System.out.println("· Test for method: findOrganiserEssay()");
			Collection<Essay> result;
						
			authenticate("organiser1");
			
			result = essayService.findOrganiserEssay();
			
			Assert.notNull(result, "La consulta devolvió un elemento nulo");
			
			Assert.isTrue(result.size() == 4);
			
			System.out.println("# All ok.");
		}
		
		//test negativo nos logeamos como otro rol que no sea organizador
		@Transactional
		@Test(expected = IllegalArgumentException.class)
		public void findOrganiserEssayNegative() {
			System.out.println("· Test for method: findOrganiserEssay()");
			Collection<Essay> result;
						
			authenticate("admin1");
			
			result = essayService.findOrganiserEssay();
			
			Assert.notNull(result, "La consulta devolvió un elemento nulo");
			
			Assert.isTrue(result.size() == 3);
			
			System.out.println("# All ok.");
		}
		
		//probamos que podemos publicar un essay de algun concurso nuestro
		@Test
		public void publish() {
			System.out.println("· Test for method: publish()");
			
			authenticate("organiser1");
			
			Essay essay;
			
			essay = essayService.findOne(19);
			essayService.publish(19);
			
			Assert.isTrue(essay.getIsPublished()==true);
			
			
			
			System.out.println("# All ok.");
		}
		
		//No podemos publicar un essay que no corresponde a nuestros concursos
		@Transactional
		@Test(expected = IllegalArgumentException.class)
		public void publishNegative() {
			System.out.println("· Test for method: publish()");
			
			authenticate("organiser2");
			
			Essay essay;
			
			essay = essayService.findOne(17);
			essayService.publish(17);
			
			Assert.isTrue(essay.getIsPublished()==true);
			
			
			
			System.out.println("# All ok.");
		}
	
}