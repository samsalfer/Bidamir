package repositories;



import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.Query;

import domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	//seleccionamos el author desde un id específico
	@Query("select u from User u where u.userAccount.id  = ?1")
	User findByUserAccountId(int userAccountId);
}
