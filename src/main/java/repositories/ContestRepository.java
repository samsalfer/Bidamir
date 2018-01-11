package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Contest;
import domain.Essay;

public interface ContestRepository extends JpaRepository<Contest, Integer> {

	// Encuentra todos los contest de un organizador
	@Query("select b.contests from Organiser b where b.id = ?1")
	Collection<Contest> findAllContestByOrganiserId(int organiserId);

	//DASHBOARD
	
	//Todos los contest ordenados por el numero de essays
	@Query("select c from Contest c order by c.essays.size desc")
	Collection<Contest> findAllContestOrderByEssays();
	
	//Media de contest organizados por organizador
	@Query("select avg(o.contests.size) from Organiser o")
	Double avgContestForOrganiser();
	
	//Lista de contest con fecha retenida el mes pasado
	@Query("select c from Contest c where c.holdingDate>=?1 and c.holdingDate<=?2")
	Collection<Contest> findContestWithHoldingDate(Date date1, Date date2);
	
	//Seleccionamos los essays de un contest
	@Query("select p.essays from Contest p where p.id = ?1")
	Collection<Essay> findEssayByContest(int contestId);
	
	//Seleccionamos los essays de un contest
	@Query("select e from Essay e where e.contest.id=?1 and e.publicSession = null")
	Collection<Essay> findEssayWithoutPublicSessionByContest(int contestId);
	
	
	
	//Dashboard
	
	//The list of contests with their public sessions, in descending order of number
	//of essays that have been submitted.
	@Query("select e.contest, count(e) from Essay e where e.isPublished=true group by e.contest.id order by count(e) desc")
	Collection<Object[]> selectContestWithPublicSessions();
	
}
