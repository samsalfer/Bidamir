package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class User extends Actor {

	//Atributes------------------------------
	

	
	
	// Relaciones -------------------------------------------

	private Collection<Estudio>  estudios;
	private Collection<Resumen>  resumenes;


	
	@Valid
	@OneToMany(mappedBy = "user")
	public Collection<Estudio> getEstudios() {
		return estudios;
	}

	public void setEstudios(Collection<Estudio> estudios) {
		this.estudios = estudios;
	}
	

	@Valid
	@OneToMany(mappedBy = "user")
	public Collection<Resumen> getResumenes() {
		return resumenes;
	}

	public void setResumenes(Collection<Resumen> resumenes) {
		this.resumenes = resumenes;
	}
	
}
