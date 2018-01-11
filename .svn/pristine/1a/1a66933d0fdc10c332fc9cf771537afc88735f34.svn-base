package repositories;



import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Essay;
import domain.Estudio;

public interface EstudioRepository extends JpaRepository<Estudio, Integer>{

	//Lista de estudios que tiene un usuario
	@Query("select e from Estudio e where e.user.userAccount.id=?1 and e.finalizado=true")
	Collection<Estudio> findEstudioByUser(int userAccountId);
	
	//Estudio por titulo
	@Query("select e from Estudio e where e.title=?1")
	Estudio findEstudioByTitle(String title);
	
}