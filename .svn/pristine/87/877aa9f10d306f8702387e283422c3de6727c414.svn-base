package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer>{
	
	//seleccionamos el author desde un id específico
	@Query("select i from Author i where i.userAccount.id  = ?1")
	Author findByUserAccountId(int userAccountId);
	
	//Dashboard
	
	// Author con mas essays
	@Query("select a from Author a where a.essays.size = (select max(a2.essays.size) from Author a2)")
	Collection<Author> findAuthorWithMoreEssay();
	
	//Author con mas essays publicados
	@Query("select e.author from Essay e where e.isPublished=true group by e.author having count(e.author)>= ALL(select count(e1.author) from Essay e1 where e1.isPublished=true group by e1.author)")
	Collection<Author> findAuthorWithMorePublishedEssay();
	
	
	//Author con menos essays publicados
	@Query("select e.author from Essay e where e.isPublished=true group by e.author having count(e.author)<= ALL(select count(e1.author) from Essay e1 where e1.isPublished=true group by e1.author)")
	Collection<Author> findAuthorWithLessPublishedEssay();
}
