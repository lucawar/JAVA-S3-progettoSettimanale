package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "libri")
@Getter
@Setter
@NoArgsConstructor
public class Libri extends Catalogo {

	private String autore;
	private String genere;
	@ManyToOne
	private Prestito prestito;

	public Libri(String titolo, LocalDate annoPubblicazione, int numeroPagine, String autore, String genere,
			Prestito prestito) {

		super(titolo, annoPubblicazione, numeroPagine);
		this.autore = autore;
		this.genere = genere;
		this.prestito = prestito;
	}

	@Override
	public String toString() {
		return "Libri [autore=" + autore + ", genere=" + genere + ", prestito=" + prestito + "]";
	}

}