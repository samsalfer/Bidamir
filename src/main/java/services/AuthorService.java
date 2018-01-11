package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AuthorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Author;
import domain.CreditCard;
import forms.RegisterAuthorForm;

@Service
@Transactional
public class AuthorService {

	// Managed repository
	// -----------------------------------------------------------------------

	@Autowired
	private AuthorRepository authorRepository;

	// Supporting services
	// -----------------------------------------------------------------------

	// Constructors
	// --------------------------------------------------------------------------------

	// Simple CRUD methods
	// ------------------------------------------------------------------

	public Author create() {
		Author result;
		CreditCard creditCard;
		UserAccount userAccount;

		result = new Author();
		Assert.notNull(result);

		Authority authority = new Authority();
		authority.setAuthority(Authority.AUTHOR);

		userAccount = new UserAccount();
		userAccount.addAuthority(authority);

		result.setUserAccount(userAccount);

		return result;
	}

	public void save(Author author) {
		Assert.notNull(author);

		if (author.getId() == 0) {
			String password = author.getUserAccount().getPassword();
			Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			password = encoder.encodePassword(password, null);
			author.getUserAccount().setPassword(password);

		}

		authorRepository.save(author);

	}

	public Collection<Author> findAll() {
		Collection<Author> result;

		result = authorRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Author findOne(int authorId) {
		Author author;

		author = authorRepository.findOne(authorId);
		Assert.notNull(author);

		return author;
	}

	// Other business methods
	// -----------------------------------------------------------------

	public Author findByPrincipal() {
		Author result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Author findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);

		Author result;

		result = authorRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	
	public Author reconstruct(RegisterAuthorForm RegisterAuthorForm) {
		Author author= new Author();
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH)+1; // Note: zero based!

		Boolean res = true;
		
		
		author.setEmail(RegisterAuthorForm.getEmail());
		author.setName(RegisterAuthorForm.getName());
		author.setSurname(RegisterAuthorForm.getSurname());
		author.setPhone(RegisterAuthorForm.getPhone());
		author.setHomePage(RegisterAuthorForm.getHomePage());
		author.setBirthDate(RegisterAuthorForm.getBirthDate());
		author.setNationality(RegisterAuthorForm.getNationality());
		author.setCreditCard(RegisterAuthorForm.getCreditCard());
		
		Assert.isTrue(RegisterAuthorForm.getCondition());
		
		if (RegisterAuthorForm.getCreditCard().getExpirationYear() == year){
			if (RegisterAuthorForm.getCreditCard().getExpirationMonth() < month){
				res = false;
			}
		}
		
		Assert.isTrue(res);
		

		UserAccount userAccount= new UserAccount();
		userAccount.setUsername(RegisterAuthorForm.getUsername());
		userAccount.setPassword(RegisterAuthorForm.getPassword());
		Authority authority = new Authority();
		authority.setAuthority("AUTHOR");
		userAccount.getAuthorities().add(authority);
		
		author.setUserAccount(userAccount);
		return author;
	}

	
	public Collection<Author> findAuthorWithMoreEssay(){
		
		Collection<Author> result;
		
		result= authorRepository.findAuthorWithMoreEssay();
		
		return result;
	}

	public Collection<Author> findAuthorWithMorePublishedEssay(){
		
		Collection<Author> result;
		
		result= authorRepository.findAuthorWithMorePublishedEssay();
		
		return result;
	}
	
	public Collection<Author> findUserAuthorLessPublishedEssay(){
		
		Collection<Author> result;
		
		result= authorRepository.findAuthorWithLessPublishedEssay();
		
		return result;
	}
}
