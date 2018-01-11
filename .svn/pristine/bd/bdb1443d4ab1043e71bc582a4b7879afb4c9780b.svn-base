package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Actor;

public interface ActorRepository extends JpaRepository<Actor, Integer>{
	
	//Seleccionamos el actor de un específico usserAccountId
	@Query("select a from Actor a where a.userAccount.id  = ?1")
	Actor findByUserAccountId(int userAccountId);
	
}
