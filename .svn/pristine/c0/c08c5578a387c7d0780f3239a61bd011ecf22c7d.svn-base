package repositories;




import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.Query;

import domain.PublicSession;
import domain.Resultado;

public interface ResultadoRepository extends JpaRepository<Resultado, Integer>{

	//Seleccionamos el actor de un específico usserAccountId
	@Query("select r from Resultado r where r.path =?1")
	Resultado findByPath(String resultadoPath);
	
}