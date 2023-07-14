package dao;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entities.Prestito;

public class PrestitoDAO {

	private final EntityManager em;

	public PrestitoDAO(EntityManager em) {
		this.em = em;
	}

	// METODO SALVA
	public void save(Prestito s) {
		EntityTransaction t = em.getTransaction();
		t.begin();

		em.persist(s);

		t.commit();

		System.out.println("Prestito salvata correttamente");
	}

	// METODO CERCA PRESTITO PER NUMERO TESSERA
	public Set<Prestito> findPrestitiByNumeroTessera(int numeroTessera) {
		TypedQuery<Prestito> query = em.createQuery(
				"SELECT p FROM Prestito p JOIN p.utente u WHERE u.numeroTessera = :numeroTessera", Prestito.class);
		query.setParameter("numeroTessera", numeroTessera);
		return new HashSet<>(query.getResultList());
	}

	// METODO CERCA PRESTITI SCADUTI
	public Set<Prestito> findPrestitiScaduti() {
		LocalDate today = LocalDate.now();
		TypedQuery<Prestito> query = em.createQuery(
				"SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista < :today AND p.dataRestituzioneEffettiva IS NULL",
				Prestito.class);
		query.setParameter("today", today);
		return new HashSet<>(query.getResultList());
	}
}
