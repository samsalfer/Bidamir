package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.UserRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.User;
import forms.RegisterUserForm;

@Service
@Transactional
public class UserService {

	// Managed repository
	// -----------------------------------------------------------------------

	@Autowired
	private UserRepository userRepository;

	// Supporting services
	// -----------------------------------------------------------------------

	// Constructors
	// --------------------------------------------------------------------------------

	// Simple CRUD methods
	// ------------------------------------------------------------------

	public User create() {
		User result;
		UserAccount userAccount;

		result = new User();
		Assert.notNull(result);

		Authority authority = new Authority();
		authority.setAuthority(Authority.ADMIN);

		userAccount = new UserAccount();
		userAccount.addAuthority(authority);

		result.setUserAccount(userAccount);

		return result;
	}

	public void save(User user) {
		Assert.notNull(user);

		if (user.getId() == 0) {
			String password = user.getUserAccount().getPassword();
			Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			password = encoder.encodePassword(password, null);
			user.getUserAccount().setPassword(password);

		}

		userRepository.save(user);

	}

	public Collection<User> findAll() {
		Collection<User> result;

		result = userRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public User findOne(int userId) {
		User user;

		user = userRepository.findOne(userId);
		Assert.notNull(user);

		return user;
	}

	// Other business methods
	// -----------------------------------------------------------------

	public User findByPrincipal() {
		User result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public User findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);

		User result;

		result = userRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	
	public User reconstruct(RegisterUserForm RegisterUserForm) {
		User user= new User();		
		
		user.setEmail(RegisterUserForm.getEmail());
		user.setName(RegisterUserForm.getName());
		user.setSurname(RegisterUserForm.getSurname());
		user.setPhone(RegisterUserForm.getPhone());
		user.setHomePage(RegisterUserForm.getHomePage());

		
		
		UserAccount userAccount= new UserAccount();
		userAccount.setUsername(RegisterUserForm.getUsername());
		userAccount.setPassword(RegisterUserForm.getPassword());
		Authority authority = new Authority();
		authority.setAuthority("ADMIN");
		userAccount.getAuthorities().add(authority);
		
		user.setUserAccount(userAccount);
		return user;
	}

	

}
