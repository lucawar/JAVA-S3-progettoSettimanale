package entities;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "libri")
@Getter
@Setter
@NoArgsConstructor

@DiscriminatorValue("Libro")
public class Libri extends Catalogo {

	private String autore;
	private String genere;

	public Libri(String isbn, String titolo, LocalDate annoPubblicazione, int numeroPagine, String autore,
			String genere, Prestito prestito) {
		super(isbn, titolo, annoPubblicazione, numeroPagine);
		this.autore = autore;
		this.genere = genere;
	}

	@Override
	public String toString() {
		return "Libri [autore=" + autore + ", genere=" + genere + "]";
	}

}