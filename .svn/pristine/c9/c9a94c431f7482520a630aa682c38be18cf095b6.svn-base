package services;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.OrganiserRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Organiser;
import domain.CreditCard;
import forms.RegisterOrganiserForm;

@Service
@Transactional
public class OrganiserService {

	// Managed repository
	// -----------------------------------------------------------------------

	@Autowired
	private OrganiserRepository organiserRepository;

	// Supporting services
	// -----------------------------------------------------------------------

	// Constructors
	// --------------------------------------------------------------------------------

	// Simple CRUD methods
	// ------------------------------------------------------------------

	public Organiser create() {
		Organiser result;
		CreditCard creditCard;
		UserAccount userAccount;

		result = new Organiser();
		Assert.notNull(result);

		Authority authority = new Authority();
		authority.setAuthority(Authority.AUTHOR);

		userAccount = new UserAccount();
		userAccount.addAuthority(authority);

		result.setUserAccount(userAccount);

		creditCard = new CreditCard();
		result.setCreditCard(creditCard);

		return result;
	}

	public void save(Organiser organiser) {
		Assert.notNull(organiser);

		if (organiser.getId() == 0) {
			String password = organiser.getUserAccount().getPassword();
			Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			password = encoder.encodePassword(password, null);
			organiser.getUserAccount().setPassword(password);

		}

		organiserRepository.save(organiser);

	}

	public Collection<Organiser> findAll() {
		Collection<Organiser> result;

		result = organiserRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Organiser findOne(int organiserId) {
		Organiser organiser;

		organiser = organiserRepository.findOne(organiserId);
		Assert.notNull(organiser);

		return organiser;
	}
	


	// Other business methods
	// -----------------------------------------------------------------

	public Organiser findByPrincipal() {
		Organiser result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Organiser findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);

		Organiser result;

		result = organiserRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	
	public Organiser reconstruct(RegisterOrganiserForm RegisterOrganiserForm) {
		Organiser organiser= new Organiser();
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH)+1; // Note: zero based!
		
		Boolean res = true;
		
		
		organiser.setEmail(RegisterOrganiserForm.getEmail());
		organiser.setName(RegisterOrganiserForm.getName());
		organiser.setSurname(RegisterOrganiserForm.getSurname());
		organiser.setPhone(RegisterOrganiserForm.getPhone());
		organiser.setHomePage(RegisterOrganiserForm.getHomePage());
		organiser.setBirthDate(RegisterOrganiserForm.getBirthDate());
		organiser.setNationality(RegisterOrganiserForm.getNationality());
		organiser.setCreditCard(RegisterOrganiserForm.getCreditCard());
		
		Assert.isTrue(RegisterOrganiserForm.getCondition());
		
		if (RegisterOrganiserForm.getCreditCard().getExpirationYear() == year){
			if (RegisterOrganiserForm.getCreditCard().getExpirationMonth() < month){
				res = false;
			}
		}
		
		Assert.isTrue(res);

		UserAccount userAccount= new UserAccount();
		userAccount.setUsername(RegisterOrganiserForm.getUsername());
		userAccount.setPassword(RegisterOrganiserForm.getPassword());
		Authority authority = new Authority();
		authority.setAuthority("ORGANISER");
		userAccount.getAuthorities().add(authority);
		
		organiser.setUserAccount(userAccount);
		return organiser;
	}
	

	//Dashboard

	public Collection<Organiser> findPresident() {
		
		Collection<Organiser> result;

		result = organiserRepository.findPresidents();

		return result;
	}

	public Collection<Organiser> findAllOrganisersCanAdd(int contestId) {
		Collection<Organiser> organisers;
		Collection<Organiser> organisersToDelete;
		Organiser organiser;
		
		organiser = findByPrincipal();
		organisers = findAll();
		organisers.remove(organiser);
		organisersToDelete = organiserRepository.findAllOrganisersToDelete(contestId);
		organisers.removeAll(organisersToDelete);
		
		return organisers;
		
	}
	

}
