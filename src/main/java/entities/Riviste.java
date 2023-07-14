package entities;

import java.time.LocalDate;

import javax.persistence.Entity;

import ENUM.Periodicità;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Riviste extends Catalogo {

	private Periodicità periodo;

	public Riviste(String titolo, LocalDate annoPubblicazione, int numeroPagine, Periodicità periodo) {
		super(titolo, annoPubblicazione, numeroPagine);
		this.periodo = periodo;
	}

	@Override
	public String toString() {
		return "Riviste [periodo=" + periodo + "]";
	}

}
