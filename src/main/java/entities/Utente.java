package entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "utente")
@Getter
@Setter
@NoArgsConstructor
public class Utente {

	@Id
	@GeneratedValue
	private long id;
	private String nome;
	private String cognome;
	private LocalDate dataNascita;
	private int numeroTessera;

	@OneToMany(mappedBy = "utente")
	private Set<Prestito> prestito;

	public Utente(String nome, String cognome, String dataNascita, int numeroTessera) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = LocalDate.parse(dataNascita);
		this.numeroTessera = numeroTessera;
	}

	@Override
	public String toString() {
		return "Utente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", dataNascita=" + dataNascita
				+ ", numeroTessera=" + numeroTessera + "]";
	}

}
