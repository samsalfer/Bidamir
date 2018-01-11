package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Organiser;

public interface OrganiserRepository extends JpaRepository<Organiser, Integer>{
	
	//seleccionamos el Organiser desde un id específico
	@Query("select i from Organiser i where i.userAccount.id  = ?1")
	Organiser findByUserAccountId(int userAccountId);
	
	//seleccionamos los organiser que son presidentes 
	@Query("select o from Organiser o where o.publicSessions is not empty")
	Collection<Organiser> findPresidents();

	//seleccionamos los organiser que son presidentes 
	@Query("select o from Organiser o join o.contests c where c.id = ?1")
	Collection<Organiser> findAllOrganisersToDelete(int contestId);
	
	
}