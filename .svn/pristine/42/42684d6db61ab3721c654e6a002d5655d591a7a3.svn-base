package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Estudio;
import domain.Resumen;

public interface ResumenRepository extends JpaRepository<Resumen, Integer>{

	@Query("select r from Resumen r where r.user.userAccount.id=?1")
	Collection<Resumen> findResumenByUser(int userAccountId);
	
	
}