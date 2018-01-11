package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Integer>{
	
	//seleccionamos el pilgrim desde un id específico
	@Query("select c from Administrator c where c.userAccount.id  = ?1")
	Administrator findByUserAccountId(int userAccountId);
	
	
	
}
