package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Sentencia;

public interface SentenciaRepository extends JpaRepository<Sentencia, Integer>{

	
	
}