package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import ENUM.Periodicità;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "riviste")
@Getter
@Setter
@NoArgsConstructor
public class Riviste extends Catalogo {

	@Enumerated(EnumType.STRING)
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
