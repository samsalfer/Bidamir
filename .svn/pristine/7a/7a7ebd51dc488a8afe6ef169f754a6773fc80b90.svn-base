package administrator;


import java.util.Collection;

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

import repositories.AdministratorRepository;
import security.Authority;
import security.UserAccount;
import services.AdministratorService;
import services.AuthorService;
import services.ContestService;
import services.EssayService;
import services.OrganiserService;
import services.PublicSessionService;
import utilities.AbstractTest;
import utilities.PopulateDatabase;
import domain.Administrator;
import domain.Author;
import domain.Contest;
import domain.Organiser;
import domain.PublicSession;
import forms.RegisterAdministratorForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
 "classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AdminTest extends AbstractTest{

	// Authentication service -------------------------------------------------

	// Service under test -----------------------------------------------------

	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private ContestService contestService;
	
	@Autowired
	private EssayService essayService;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private OrganiserService organiserService;
	
	@Autowired
	private PublicSessionService publicSessionService;
	
	// Repository
	@Autowired
	private AdministratorRepository administratorRepository;
	


	// Others Service -----------------------------------------------------

	@Before
	public void setUp() {
		PopulateDatabase.main(null);
	}
	
	//---------------------------------------------------------------------------------------
	// Register to the system as an administrator -------------------- -----------------------------
	//---------------------------------------------------------------------------------------
		
		@Test
		public void administratorRegisterTest(){
			System.out.println("Â· Test for method: administratorRegisterTest()");
			
			RegisterAdministratorForm regForm = newRegForm();
			
			Administrator uReconstruct = administratorService.reconstruct(regForm);
			
			Administrator aux = newAdministrator();
			
			/*Comprobamos que los datos no han cambiado al hacer reconstruct*/
			
				Assert.notNull(uReconstruct, "reconstruct() devolviÃ³ NULL");
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
			
			//Other relations

				
				
			/* Guardamos y comprobamos que todo estÃ¡ correcto */
			administratorService.save(uReconstruct);
			
			authenticate("Pacopa");
			Administrator auxBD = administratorService.findByPrincipal();
				Assert.notNull(auxBD, "La consulta devolviÃ³ NULL");

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
			

			
			//Other relations

			
			System.out.println("# All ok.");
		}
		
		
		
		//Caso Negativo en el que intentamos crear dos veces el mismo administrador
		@Test (expected = DataIntegrityViolationException.class)
		public void negAdministratorRegisterTest(){
			
			System.out.println("Â· Test for method: negAdministratorRegisterTest()");
			
			RegisterAdministratorForm regForm = newRegForm();
			
			Administrator uReconstruct = administratorService.reconstruct(regForm);
			administratorService.save(uReconstruct);
			administratorRepository.flush();
			administratorService.save(uReconstruct);
			administratorRepository.flush();
			
			unauthenticate();
			
			System.out.println("Â· # All ok.");
		}
		
		
		// auxiliary methods ------------------------------------------------------

		
		//RegisterPilgrimForm
		public RegisterAdministratorForm newRegForm(){
			RegisterAdministratorForm regF=new RegisterAdministratorForm();
			
			regF.setCondition(true);
			regF.setEmail("pacopaco@pacopaco.com");
			regF.setName("Paco");
			regF.setSurname("Palotes");
			regF.setPassword("123otravez");
			regF.setPasswordVerificada("123otravez");
			regF.setUsername("Pacopa");
			regF.setPhone("955955955");

			return regF;
		}
		
		public Administrator newAdministrator(){
			
			UserAccount ua=new UserAccount();
			ua.setUsername("Pacopa");
			ua.setPassword("123otravez");
			Authority authority = new Authority();//Auth
			authority.setAuthority("ADMIN");//Auth
			ua.getAuthorities().add(authority);//Auth
			
			//USER
			Administrator aux=new Administrator();
			aux.setName("Paco"); aux.setSurname("Palotes");aux.setEmail("pacopaco@pacopaco.com"); aux.setPhone("955955955");
			aux.setUserAccount(ua);
			return aux;
		}
		
		//DASHBOARD
		
		//probamos que muestra todos los contest de un organizador
		@Test
		public void findAllContestOrderByEssays() {
			System.out.println("· Test for method: findAllContestOrderByEssays()");
			Collection<Contest> result;

			result = contestService.findAllContestOrderByEssays();
			
			Assert.notNull(result, "La consulta devolvió un elemento nulo");
			
			Assert.isTrue(result.size() == 5);
			
			System.out.println("# All ok.");
		}

		
		//No existen 3 contest
		@Transactional
		@Test(expected = IllegalArgumentException.class)
		public void findAllContestOrderByEssaysNegativoo() {
			System.out.println("· Test for method: findAllContestOrderByEssays()");
			Collection<Contest> result;

			result = contestService.findAllContestOrderByEssays();
			
			Assert.notNull(result, "La consulta devolvió un elemento nulo");
			
			Assert.isTrue(result.size() == 0);
			
			System.out.println("# All ok.");
		}
		
		//El author con mas essay
		@Test
		public void findAuthorWithMoreEssay() {
			System.out.println("· Test for method: findAuthorWithMoreEssay()");
			Collection<Author> result;

			result = authorService.findAuthorWithMoreEssay();
			
			Assert.notNull(result, "La consulta devolvió un elemento nulo");
			
			Assert.isTrue(result.size() == 1);
			
			System.out.println("# All ok.");
		}

		
		//El author con mas essay negativo
		@Transactional
		@Test(expected = IllegalArgumentException.class)
		public void findAuthorWithMoreEssayNegative() {
			System.out.println("· Test for method: findAuthorWithMoreEssay()");
			Collection<Author> result;

			result = authorService.findAuthorWithMoreEssay();
			
			Assert.notNull(result, "La consulta devolvió un elemento nulo");
			
			Assert.isTrue(result.size() == 3);
			
			System.out.println("# All ok.");
		}
		
		//El author con mas essay
		@Test
		public void findAuthorWithMorePublishedEssay() {
			System.out.println("· Test for method: findAuthorWithMoreEssay()");
			Collection<Author> result;

			result = authorService.findAuthorWithMorePublishedEssay();
			
			Assert.notNull(result, "La consulta devolvió un elemento nulo");
			
			Assert.isTrue(result.size() == 1);
			
			System.out.println("# All ok.");
		}

		
		//El author con mas essay negativo
		@Transactional
		@Test(expected = IllegalArgumentException.class)
		public void findAuthorWithMorePublishedEssayNegative() {
			System.out.println("· Test for method: findAuthorWithMoreEssay()");
			Collection<Author> result;

			result = authorService.findAuthorWithMorePublishedEssay();
			
			Assert.notNull(result, "La consulta devolvió un elemento nulo");
			
			Assert.isTrue(result.size() == 3);
			
			System.out.println("# All ok.");
		}
		
		//El author con mas essay
		@Test
		public void findUserAuthorLessPublishedEssay() {
			System.out.println("· Test for method: findUserAuthorLessPublishedEssay()");
			Collection<Author> result;

			result = authorService.findUserAuthorLessPublishedEssay();
			
			Assert.notNull(result, "La consulta devolvió un elemento nulo");
			
			Assert.isTrue(result.size() == 1);
			
			System.out.println("# All ok.");
		}

		
		//El author con mas essay negativo
		@Transactional
		@Test(expected = IllegalArgumentException.class)
		public void findUserAuthorLessPublishedEssayNegative() {
			System.out.println("· Test for method: findUserAuthorLessPublishedEssay()");
			Collection<Author> result;

			result = authorService.findUserAuthorLessPublishedEssay();
			
			Assert.notNull(result, "La consulta devolvió un elemento nulo");
			
			Assert.isTrue(result.size() == 3);
			
			System.out.println("# All ok.");
		}
		
		
		//Media de essay por autor
		@Test
		public void avgEssayForAuthor() {
			System.out.println("· Test for method: avgEssayForAuthor()");
			Double result;

			result = essayService.avgEssayForAuthor();
			
			Assert.notNull(result, "La consulta devolvió un elemento nulo");
			
			Assert.isTrue(result == 2);
			
			System.out.println("# All ok.");
		}

		
		//Media de essay por autor negativo
		@Transactional
		@Test(expected = IllegalArgumentException.class)
		public void avgEssayForAuthorNegative() {
			System.out.println("· Test for method: avgEssayForAuthor()");
			Double result;

			result = essayService.avgEssayForAuthor();
			
			Assert.notNull(result, "La consulta devolvió un elemento nulo");
			
			Assert.isTrue(result == 43);
			
			System.out.println("# All ok.");
		}
		
		//Media de contest por organizador
		@Test
		public void avgContestForOrganiser() {
			System.out.println("· Test for method: avgContestForOrganiser()");
			Double result;

			result = contestService.avgContestForOrganiser();
			
			Assert.notNull(result, "La consulta devolvió un elemento nulo");
			
			Assert.isTrue(result == 4);
			
			System.out.println("# All ok.");
		}

		
		//Media de contest por organizador negativo
		@Transactional
		@Test(expected = IllegalArgumentException.class)
		public void avgContestForOrganiserNegative() {
			System.out.println("· Test for method: avgContestForOrganiser()");
			Double result;

			result = contestService.avgContestForOrganiser();
			
			Assert.notNull(result, "La consulta devolvió un elemento nulo");
			
			Assert.isTrue(result == 43);
			
			System.out.println("# All ok.");
		}
		
		
		//probamos que muestra todos los contest de un organizador
		@Test
		public void findContestWithHoldingDate() {
			System.out.println("· Test for method: findContestWithHoldingDate()");
			Collection<Contest> result;

			result = contestService.findContestWithHoldingDate();
			
			Assert.notNull(result, "La consulta devolvió un elemento nulo");
			
			Assert.isTrue(result.size() == 1);
			
			System.out.println("# All ok.");
		}

		
		//No existen 3 contest
		@Transactional
		@Test(expected = IllegalArgumentException.class)
		public void findContestWithHoldingDateNegativoo() {
			System.out.println("· Test for method: findAllContestOrderByEssays()");
			Collection<Contest> result;

			result = contestService.findContestWithHoldingDate();
			
			Assert.notNull(result, "La consulta devolvió un elemento nulo");
			
			Assert.isTrue(result.size() == 23);
			
			System.out.println("# All ok.");
		}
		
		//DASHBOARD 1
		

		@Test
		public void selectContestWithPublicSessions() {
			System.out.println("· Test for method: selectContestWithPublicSessions()");
			Collection<Object[]> result;

			result = contestService.selectContestWithPublicSessions();
			
			Assert.notNull(result, "La consulta devolvió un elemento nulo");
			
			Assert.isTrue(result.size() == 2);
			
			System.out.println("# All ok.");
		}

		
		//No existen 0 contest
		@Transactional
		@Test(expected = IllegalArgumentException.class)
		public void selectContestWithPublicSessionsNegativoo() {
			System.out.println("· Test for method: selectContestWithPublicSessions()");
			Collection<Object[]> result;

			result = contestService.selectContestWithPublicSessions();
			
			Assert.notNull(result, "La consulta devolvió un elemento nulo");
			
			Assert.isTrue(result.size() == 0);
			
			System.out.println("# All ok.");
		}
		
		
		//DASHBOARD 2
		
		
		@Test
		public void findPresident() {
			System.out.println("· Test for method: findPresident()");
			Collection<Organiser> result;

			result = organiserService.findPresident();
			
			Assert.notNull(result, "La consulta devolvió un elemento nulo");
			
			Assert.isTrue(result.size() == 1);
			
			System.out.println("# All ok.");
		}

		
		
		@Transactional
		@Test(expected = IllegalArgumentException.class)
		public void findPresidentNegativoo() {
			System.out.println("· Test for method: selectContestWithPublicSessions()");
			Collection<Organiser> result;

			result = organiserService.findPresident();
			
			Assert.notNull(result, "La consulta devolvió un elemento nulo");
			
			Assert.isTrue(result.size() == 0);
			
			System.out.println("# All ok.");
		}
		
		//DASHBOARD 3
		
		
		@Test
		public void findSessionWithChairmanOrder() {
			System.out.println("· Test for method: findSessionWithChairmanOrder()");
			Collection<PublicSession> result;

			

			result = publicSessionService.findSessionWithChairmanOrder();
			
			Assert.notNull(result, "La consulta devolvió un elemento nulo");
			
			Assert.isTrue(result.size() == 2);
			
			System.out.println("# All ok.");
		}

		
		
		@Transactional
		@Test(expected = IllegalArgumentException.class)
		public void findSessionWithChairmanOrderNegativoo() {
			System.out.println("· Test for method: findSessionWithChairmanOrder()");
			Collection<PublicSession> result;

			

			result = publicSessionService.findSessionWithChairmanOrder();
			
			Assert.notNull(result, "La consulta devolvió un elemento nulo");
			
			Assert.isTrue(result.size() == 0);
			
			System.out.println("# All ok.");
		}
}
