package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;

@Service
@Transactional
public class ActorService {
	
		
	// Managed repository -----------------------------------------------------------------------
	
	@Autowired
	private ActorRepository actorRepository;
	
	// Supporting services -----------------------------------------------------------------------
			
				
	// Constructors --------------------------------------------------------------------------------


	// Simple CRUD methods ------------------------------------------------------------------
				
				
	// Other business methods -----------------------------------------------------------------
	
	public Actor findByPrincipal() {
		Actor result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}
	
	public Actor findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);
		
		Actor result;
		
		result = actorRepository.findByUserAccountId(userAccount.getId());
		
		return result;
	}
	
	public Actor findOne(int actorId){
		Assert.notNull(actorId);
		Actor result;
		
		result = actorRepository.findOne(actorId);
		Assert.notNull(result);
		
		return result;
		
	}
	
	public Collection<Actor> finAll(){
		
		Collection<Actor> result;
				
		result=actorRepository.findAll();
		return result;
	}


	

	}


