package entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "prestito")
@Getter
@Setter
@NoArgsConstructor
public class Prestito {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	@JoinColumn(name = "utente_id", referencedColumnName = "id", nullable = false)
	private Utente utente;

	@ManyToOne
	@JoinColumn(name = "catalogo_id", referencedColumnName = "isbn", nullable = false)
	private Catalogo elementoPrestato;
	private LocalDate inizioPrestito;
	private LocalDate dataRestituzionePrevista;
	private LocalDate dataRestituzioneEffettiva;
	@OneToMany
	private Set<Catalogo> libriPrestito;

	public Prestito(Utente utente, Catalogo elementoPrestato, String inizioPrestito, String dataRestituzionePrevista,
			String dataRestituzioneEffettiva) {

		this.elementoPrestato = elementoPrestato;
		this.inizioPrestito = LocalDate.parse(inizioPrestito);
		this.dataRestituzionePrevista = LocalDate.parse(dataRestituzionePrevista);
		this.dataRestituzionePrevista = this.inizioPrestito.plusDays(30);
	}

	@Override
	public String toString() {
		return "Prestito [utente=" + utente + ", elementoPrestato=" + elementoPrestato + ", inizioPrestito="
				+ inizioPrestito + ", dataRestituzionePrevista=" + dataRestituzionePrevista
				+ ", dataRestituzioneEffettiva=" + dataRestituzioneEffettiva + "]";
	}

}
