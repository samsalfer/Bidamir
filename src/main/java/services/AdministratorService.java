package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import forms.RegisterAdministratorForm;

@Service
@Transactional
public class AdministratorService {
	
	// Managed repository -----------------------------------------------------------------------
	
		@Autowired
		private AdministratorRepository administratorRepository;
		
		// Supporting services -----------------------------------------------------------------------
					
		// Constructors --------------------------------------------------------------------------------


		// Simple CRUD methods ------------------------------------------------------------------
		
		public Administrator create() {
			Administrator result;
			UserAccount userAccount;

			result = new Administrator();
			Assert.notNull(result);

			Authority authority = new Authority();
			authority.setAuthority(Authority.ADMIN);

			userAccount = new UserAccount();
			userAccount.addAuthority(authority);

			result.setUserAccount(userAccount);

			return result;
		}
					
		public void save(Administrator administrator){
			Assert.notNull(administrator);
			
			if (administrator.getId() == 0) {
				String password = administrator.getUserAccount().getPassword();
				Md5PasswordEncoder encoder = new Md5PasswordEncoder();
				password = encoder.encodePassword(password, null);
				administrator.getUserAccount().setPassword(password);
				
			}
			administratorRepository.save(administrator);
		}
		
		public Collection<Administrator> findAll(){
			Collection<Administrator> result;
			
			result = administratorRepository.findAll();
			Assert.notNull(result);
			
			return result;
		}
		

		

						
		// Other business methods -----------------------------------------------------------------
		
		public Administrator findByPrincipal(){
			Administrator result;
			UserAccount userAccount;
			
			userAccount = LoginService.getPrincipal();
			Assert.notNull(userAccount);
			result = findByUserAccount(userAccount);
			Assert.notNull(result);
			
			return result;
		}
		
		
		public Administrator findByUserAccount(UserAccount userAccount) {
			Assert.notNull(userAccount);
			
			Administrator result;
			
			result = administratorRepository.findByUserAccountId(userAccount.getId());
			
			return result;
		}
		
		public Administrator reconstruct(RegisterAdministratorForm RegisterAdministratorForm) {
			Administrator administrator= new Administrator();
			
			
			administrator.setEmail(RegisterAdministratorForm.getEmail());
			administrator.setName(RegisterAdministratorForm.getName());
			administrator.setSurname(RegisterAdministratorForm.getSurname());
			administrator.setPhone(RegisterAdministratorForm.getPhone());
			administrator.setHomePage(RegisterAdministratorForm.getHomePage());
			
			Assert.isTrue(RegisterAdministratorForm.getCondition());

			UserAccount userAccount= new UserAccount();
			userAccount.setUsername(RegisterAdministratorForm.getUsername());
			userAccount.setPassword(RegisterAdministratorForm.getPassword());
			Authority authority = new Authority();
			authority.setAuthority("ADMIN");
			userAccount.getAuthorities().add(authority);
			
			administrator.setUserAccount(userAccount);
			return administrator;
		}
		
	
		
}
