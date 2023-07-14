package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Prestito {

	@Id
	@GeneratedValue
	private long id;
	private Utente utente;
	private Libri elementoPrestato;
	private LocalDate inizioPrestito;
	private LocalDate dataRestituzionePrevista;
	private LocalDate dataRestituzioneEffettiva;

	public Prestito(Utente utente, Libri elementoPrestato, String inizioPrestito, String dataRestituzionePrevista,
			String dataRestituzioneEffettiva) {

		this.elementoPrestato = elementoPrestato;
		this.inizioPrestito = LocalDate.parse(inizioPrestito);
		this.dataRestituzionePrevista = LocalDate.parse(dataRestituzionePrevista);
		this.dataRestituzioneEffettiva = LocalDate.parse(dataRestituzioneEffettiva);
	}

	@Override
	public String toString() {
		return "Prestito [utente=" + utente + ", elementoPrestato=" + elementoPrestato + ", inizioPrestito="
				+ inizioPrestito + ", dataRestituzionePrevista=" + dataRestituzionePrevista
				+ ", dataRestituzioneEffettiva=" + dataRestituzioneEffettiva + "]";
	}

}
