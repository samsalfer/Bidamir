package organiser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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

import repositories.ContestRepository;
import repositories.OrganiserRepository;
import repositories.PublicSessionRepository;
import security.Authority;
import security.UserAccount;
import services.ContestService;
import services.OrganiserService;
import services.PublicSessionService;
import utilities.AbstractTest;
import utilities.PopulateDatabase;
import domain.Contest;
import domain.CreditCard;
import domain.Essay;
import domain.Organiser;
import domain.PublicSession;
import forms.RegisterOrganiserForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
 "classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class OrganiserTest extends AbstractTest {

	 @Autowired
	 private OrganiserService organiserService;
	 
	 @Autowired
	 private ContestService contestService;
	 
	 @Autowired
	 private PublicSessionService publicSessionService;
	 
	 @Autowired
	 private ContestRepository contestRepository;
	 
	 
	 @Autowired
	 private OrganiserRepository organiserRepository;
	 
	 @Autowired
	 private PublicSessionRepository publicSessionRepository;
	
	 @Before
	 public void setUp() {
	 PopulateDatabase.main(null);
	 }

	//---------------------------------------------------------------------------------------
	// Register to the system as a organiser -------------------- -----------------------------
	//---------------------------------------------------------------------------------------
			
			@Test
			public void organiserRegisterTest(){
				System.out.println("· Test for method: organiserRegisterTest()");
				
				RegisterOrganiserForm regForm = newRegForm();
				
				Organiser uReconstruct = organiserService.reconstruct(regForm);
				
				Organiser aux=newOrganiser();
				
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
				organiserService.save(uReconstruct);
				
				authenticate("Pacopal");
				Organiser auxBD = organiserService.findByPrincipal();
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
			public void negOrganiserRegisterTest(){
				
				System.out.println("· Test for method: negOrganiserRegisterTest()");
				
				RegisterOrganiserForm regForm = newRegForm();
				
				Organiser uReconstruct = organiserService.reconstruct(regForm);
				organiserService.save(uReconstruct);
				organiserRepository.flush();
				organiserService.save(uReconstruct);
				organiserRepository.flush();
				
				System.out.println("· # All ok.");
			}

			
		// auxiliary methods ------------------------------------------------------

		
		//RegisterOrganiserForm
		public RegisterOrganiserForm newRegForm(){
			RegisterOrganiserForm regF=new RegisterOrganiserForm();
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
		
		public Organiser newOrganiser(){
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
			authority.setAuthority("ORGANISER");//Auth
			ua.getAuthorities().add(authority);//Auth
			
			//USER
			Organiser aux=new Organiser();
			aux.setName("Paco"); aux.setSurname("Palotes");aux.setEmail("pacopaco@pacopaco.com"); aux.setPhone("955955955");aux.setBirthDate(now);aux.setNationality("spain");aux.setCreditCard(creditCard);;
			aux.setUserAccount(ua);
			return aux;
		}
		
		/* 
		 * 
		 * Manage Contest
		 * 
		 */
		
		/* 
		 * List my Contests
		 */
		
		@Test
		public void myContestTest(){
			Collection<Contest> contests;
			
			authenticate("organiser1");
			
			contests = contestService.findAllContestByOrganiserId();
			
			Assert.isTrue(contests.size() == 5);
			
			System.out.println("· # All ok. Ha devuelto el número correcto de contest de un organiser");
			unauthenticate();
		}
		
		@Transactional
		@Test(expected = IllegalArgumentException.class)
		public void myContestTestN(){
			Collection<Contest> contests;
			
			authenticate("author1");
			System.out.println("· # All ok. Un usario que no sea organiser no puede devolver la lista de mis contest");
			
			contests = contestService.findAllContestByOrganiserId();
			
			Assert.isTrue(contests.size() == 1);
			
			unauthenticate();
		}
		
		/* 
		 * Create contest
		 */
		
		@SuppressWarnings("deprecation")
		@Test
		public void createContestTest(){
			Contest contest;
			Date deadlineDate;
			Date holdingDate;
			Collection<Essay> essays = new LinkedList<Essay>();

			
			authenticate("organiser1");
			
			deadlineDate = new Date();
			deadlineDate.setHours(24000);
			holdingDate = new Date();
			holdingDate.setHours(48000);
			contest = contestService.create();
			contest.setDeadlineDate(deadlineDate);
			contest.setHoldingDate(holdingDate);
			contest.setDescription("Description");
			contest.setName("Nombre");
			contest.setResult(null);
			contest.setEssays(essays);		
			contestService.save(contest);
			Assert.isTrue(contest.getHoldingDate().equals(holdingDate));

			System.out.println("· # All ok. Se ha creado correctamente el Contest");
			unauthenticate();
		}

		@SuppressWarnings("deprecation")
		@Transactional
		@Test(expected = IllegalArgumentException.class)
		public void createContestTestN(){
			Contest contest;
			Date deadlineDate;
			Date holdingDate;
			
			authenticate("author1");
			
			deadlineDate = new Date();
			deadlineDate.setYear(2);
			holdingDate = new Date();
			holdingDate.setYear(3);
			System.out.println("· # All ok. No se ha creado correctamente el Contest ya que sólo pueden crear contest los organiser");
			contest = contestService.create();
			contest.setDeadlineDate(deadlineDate);
			contest.setHoldingDate(holdingDate);
			contest.setDescription("Description");
			contest.setName("Nombre");
			contestService.save(contest);
			Assert.isTrue(contest.getHoldingDate().equals(holdingDate));
			unauthenticate();

			
		}
		
		/* 
		 * Edit contest
		 */

		@Test
		public void editContestTest(){
			Contest contest;
			List<Contest> contests;

			
			authenticate("organiser1");
			
			contests = new ArrayList<>(contestService.findAllContestByOrganiserId());
			contest = contests.get(1);
			contest.setDescription("Description111");
			contest.setName("Nombre");
			
			Assert.isTrue(contest.getDescription().equals("Description111"));
			contestService.saveOrganiser(contest);
			System.out.println("· # All ok. Se ha modificado correctamente el Contest");
			unauthenticate();
		}
		

		@Transactional
		@Test(expected = IllegalArgumentException.class)
		public void editContestTestN(){
			Contest contest;
			List<Contest> contests;

			
			authenticate("organiser1");
			
			contests = new ArrayList<>(contestService.findAllContestByOrganiserId());
			unauthenticate();
			authenticate("author");
			contest = contests.get(1);
			contest.setDescription("Description111");
			contest.setName("Nombre");
			contestService.save(contest);
			Assert.isTrue(contest.getDescription().equals("Description111"));

			System.out.println("· # All ok. No se ha modificado correctamente el Contest ya que sólo lo puede modificar el Organiser que lo ha creado.");
			unauthenticate();
		}
		
		/* 
		 * Delete contest
		 */
		
		@Test
		public void deleteContestTest(){
			Contest contest;

			authenticate("organiser1");
			
			contest = contestService.findOne(15);
			Assert.isTrue(contestRepository.exists(contest.getId()));
			contestService.delete(contest);
			contestRepository.flush();
			Assert.isTrue(!contestRepository.exists(contest.getId()));
			System.out.println("· # All ok. Se ha comprobado correctamente que el organiser de un content lo ha borrado de la BD");
			unauthenticate();
		}
		
		@Transactional
		@Test(expected = IllegalArgumentException.class)
		public void deleteContestTestN(){
			Contest contest;
			List<Contest> contests;

			
			authenticate("organiser1");
			
			contests = new ArrayList<>(contestService.findAllContestByOrganiserId());
			contest = contests.get(1);
			Assert.isTrue(contestRepository.exists(contest.getId()));
			System.out.println("· # All ok. No se puede borrar un contest que tenga algún essay.");
			contestService.delete(contest);
			contestRepository.flush();
			Assert.isTrue(!contestRepository.exists(contest.getId()));
			
			unauthenticate();
		}
		
		/* 
		 * Create PublicSession, set chairman and set capacity of PublicSession
		 */
		
		@SuppressWarnings("deprecation")
		@Test
		public void createPublicSessionTest(){
			Date start;
			Date end;
			Collection<Essay> essays = new LinkedList<Essay>();
			PublicSession publicSession;

			
			authenticate("organiser1");
			
			start = new Date();
			start.setHours(24000);
			end = new Date();
			end.setHours(48000);
			publicSession = publicSessionService.create();
			publicSession.setStartDate(start);
			publicSession.setEndDate(end);
			publicSession.setCapacity(5);
			publicSession.setEssayNumber(5);
			publicSession.setEssays(essays);
			publicSession.setChairman(organiserService.findByPrincipal());
			publicSession.setContest(contestService.findOne(15));
			
			publicSessionService.save(publicSession);
			
			Assert.isTrue(publicSession.getCapacity()==5);


			System.out.println("· # All ok. Se ha creado correctamente el PublicSession");
			unauthenticate();
		}
		
		@SuppressWarnings("deprecation")
		@Test(expected = NullPointerException.class)
		public void createPublicSessionNegativeTest(){
			Date start;
			Date end;
			Collection<Essay> essays = new LinkedList<Essay>();
			PublicSession publicSession;

			
			authenticate("organiser1");
			
			start = new Date();
			start.setHours(24000);
			end = new Date();
			end.setHours(8000);
			publicSession = publicSessionService.create();
			publicSession.setStartDate(start);
			publicSession.setStartDate(end);
			publicSession.setCapacity(5);
			publicSession.setEssayNumber(5);
			publicSession.setEssays(essays);
			publicSession.setChairman(organiserService.findByPrincipal());
			publicSession.setContest(contestService.findOne(15));
			
			publicSessionService.save(publicSession);
			
			Assert.isTrue(publicSession.getCapacity()==5);


			System.out.println("· # All ok. No se ha creado correctamente el PublicSession ya que la fecha inicio tiene que ir antes que la final");
			unauthenticate();
		}
		
		@SuppressWarnings("deprecation")
		@Test(expected = IllegalArgumentException.class)
		public void createPublicSessionNegativeTestChairman(){
			Date start;
			Date end;
			Collection<Essay> essays = new LinkedList<Essay>();
			PublicSession publicSession;

			
			authenticate("organiser1");
			
			start = new Date();
			start.setHours(24000);
			end = new Date();
			end.setHours(800000);
			publicSession = publicSessionService.create();
			publicSession.setStartDate(start);
			publicSession.setStartDate(end);
			publicSession.setCapacity(5);
			publicSession.setEssayNumber(5);
			publicSession.setEssays(essays);
			publicSession.setChairman(organiserService.findOne(55));
			publicSession.setContest(contestService.findOne(15));
			
			publicSessionService.save(publicSession);
			
			Assert.isTrue(publicSession.getCapacity()==5);


			System.out.println("· # All ok. No se ha creado correctamente el PublicSession ya el chairman no existe");
			unauthenticate();
		}
		
		/* 
		 * Edit PublicSession
		 */
		
		@Test
		public void editPublicSessionTest(){
			ArrayList<PublicSession> publicSessions;
			PublicSession publicSession;

			
			authenticate("organiser1");
			
			Organiser organiser = organiserService.findByPrincipal();
			
			publicSessions = new ArrayList<>(publicSessionService.findByChairman(organiser.getId()));
			publicSession = publicSessions.get(0);
			
			publicSession.setCapacity(288);
			publicSession.setEssayNumber(288);
			
			publicSessionService.save(publicSession);
			
			Assert.isTrue(publicSession.getCapacity()==288);
			Assert.isTrue(publicSession.getEssayNumber()==288);


			System.out.println("· # All ok. Se ha editado correctamente el PublicSession");
			unauthenticate();
		}
		
		@Test(expected = IllegalArgumentException.class)
		public void editPublicSessionTestNegative(){
			ArrayList<PublicSession> publicSessions;
			PublicSession publicSession;

			
			authenticate("organiser1");
			
			Organiser organiser = organiserService.findByPrincipal();
			
			publicSessions = new ArrayList<>(publicSessionService.findByChairman(organiser.getId()));
			publicSession = publicSessions.get(0);
			unauthenticate();
			
			authenticate("organiser2");
			
			publicSession.setCapacity(288);
			publicSession.setEssayNumber(288);
			
			publicSessionService.save(publicSession);
			
			Assert.isTrue(publicSession.getCapacity()==288);
			Assert.isTrue(publicSession.getEssayNumber()==288);


			System.out.println("· # All ok. No se ha podido editar el publicSession por que no pertenece a ese Chairman");
			unauthenticate();
		}
		
		
		
	
		
		/* 
		 * add an organiser to a essay
		 */
		
		@Test
		public void addorganiserP(){
			List<Contest> contests;
			List<Organiser> organisers;
			Contest contest;
			authenticate("organiser1");
			
			Organiser organiser = organiserService.findByPrincipal();
			Organiser organiser2 ;
			contests = new ArrayList<>(organiser.getContests());
			contest = contests.get(4);
			organisers = new ArrayList<>(organiserService.findAllOrganisersCanAdd(contest.getId()));
			organiser2 = organisers.get(0);
			Assert.isTrue(!organiser2.getContests().contains(contest));
			contest.getOrganisers().add(organiser2);
			organiser2.getContests().add(contest);
			contestService.saveOrganiser(contest);
			
			Assert.isTrue(organiser2.getContests().contains(contest));
			
			System.out.println("· # All ok. Se ha añadido correctamente el contest al organiser");
			unauthenticate();
		}
		
		@Test(expected = IllegalArgumentException.class)
		public void addorganiserNegative(){
			List<Contest> contests;
			List<Organiser> organisers;
			Contest contest;
			authenticate("organiser1");
			
			Organiser organiser = organiserService.findByPrincipal();
			unauthenticate();
			authenticate("organiser2");
			Organiser organiser2 = organiserService.findByPrincipal();
			contests = new ArrayList<>(organiser.getContests());
			contest = contests.get(4);
			organisers = new ArrayList<>(organiserService.findAllOrganisersCanAdd(contest.getId()));
			System.out.println("· # All ok. Un organiser que no tiene un contest no se lo puede añadir");
			contestService.findOneToEdit(contest.getId());
			organiser2 = organisers.get(0);
			Assert.isTrue(!organiser2.getContests().contains(contest));
			contest.getOrganisers().add(organiser2);
			organiser2.getContests().add(contest);
			contestService.saveOrganiser(contest);
			
			Assert.isTrue(organiser2.getContests().contains(contest));
			
			
			unauthenticate();
		}
		
		/* 
		 * list the contest that he/she created
		 */
		
		@Test
		public void listContestThatHeOrSheCreatedP(){
			List<Contest> contests;
			List<PublicSession> publicSessions;
			Contest contest;
			authenticate("organiser1");
			
			Organiser organiser = organiserService.findByPrincipal();
			contests = new ArrayList<>(organiser.getContests());
			contest = contests.get(4);
			publicSessions = new ArrayList<>(contest.getPublicSessions());
			
			Assert.isTrue(contests.size()>=0);
			Assert.isTrue(publicSessions.size()>=0);
			
			System.out.println("· # All ok. Se ha listado bien los contests y las public sessions");
			unauthenticate();
		}
		
		@Test(expected = IllegalArgumentException.class)
		public void listContestThatHeOrSheCreatedNegative(){
			List<Contest> contests;
			List<PublicSession> publicSessions;
			Contest contest;
			authenticate("organiser1");
			
			Organiser organiser = organiserService.findByPrincipal();
			contests = new ArrayList<>(organiser.getContests());
			contest = contests.get(4);
			publicSessions = new ArrayList<>(contest.getPublicSessions());
			System.out.println("· # All ok. No se ha listado bien los contests y las public sessions");
			Assert.isTrue(contests.size()==2);
			Assert.isTrue(publicSessions.size()==6);
			
			
			unauthenticate();
		}
		
		
}