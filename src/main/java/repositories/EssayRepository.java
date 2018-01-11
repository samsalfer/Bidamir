package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Essay;

public interface EssayRepository extends JpaRepository<Essay, Integer>{

	// Lista los essay que han sido publicados.
	@Query("select e from Essay e where e.isPublished=true")
	Collection<Essay> findPublishedEssay();
	
	// Lista los essay que existen en mis contest
	@Query("select c.essays from Organiser o join o.contests c where o.id=?1")
	Collection<Essay> findOrganiserEssay(int organiserId);
	
	//Lista de essay que un autor tiene en un concurso
	@Query("select e from Essay e where e.author.userAccount.id=?1 and e.contest.id=?2")
	Collection<Essay> findAuthorEssayByContest(int userAccountId, int contestId);
	
	//Lista de titulos de essay de un determinado contest (para que no coincida titulo)
	@Query("select e.title from Essay e where e.contest.id=?1")
	Collection<String> findEssayTitleByContest(int contestId);
	
	
	//Dashboard
	
	//Media de essays por author
	@Query("select avg(a.essays.size) from Author a")
	Double avgEssayForAuthor();
	
	// Lista los essay que han sido publicados de un contest
	@Query("select e from Essay e where e.isPublished=true and e.contest.id = ?1")
	Collection<Essay> findPublishedEssayWithContest(int contestId);
	
	
}