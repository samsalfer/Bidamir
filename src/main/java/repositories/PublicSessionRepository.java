package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Essay;
import domain.PublicSession;

public interface PublicSessionRepository extends JpaRepository<PublicSession, Integer> {
	
		//Seleccionamos el actor de un específico usserAccountId
		@Query("select p from PublicSession p where p.chairman.userAccount.id =?1")
		PublicSession findByUserAccountId(int userAccountId);
		
		//Seleccionamos las publicsession de un organizador
		@Query("select p from PublicSession p where p.chairman.id =?1")
		Collection<PublicSession> findByChairman(int organiserId);
		
		
		//Seleccionamos los essays de un publicSession
		@Query("select p.essays from PublicSession p where p.id=?1")
		Collection<Essay> findEssaysByPublicSession(int publicSessionId);
		
		//Dashboard
		
		
		//Seleccionamos las publicsession que tienen organizador.
		@Query("select p from PublicSession p where p.chairman != null order by p.capacity desc")
		Collection<PublicSession> findSessionWithChairmanOrder();
		
		//Seleccionamos los publicSessions que tiene un contest.
		@Query("select p from PublicSession p where p.contest.id=?1")
		Collection<PublicSession> findSessionByContest(int contestId);

		
}
