package dao;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entities.Catalogo;

public class CatalogoDAO {

	private final EntityManager em;

	public CatalogoDAO(EntityManager em) {
		this.em = em;
	}

	// METODO SALVA CATALOGO
	public void save(Catalogo s) {
		EntityTransaction t = em.getTransaction();
		t.begin();

		em.persist(s);

		t.commit();

		System.out.println("Catalogo salvato correttamente");
	}

	// METODO CERCA CATALOGO PER ISBN
	public Catalogo findByIsbn(String isbn) {
		Catalogo found = em.find(Catalogo.class, isbn);
		return found;
	}

	// METODO CANCELLA CATALOGO PER ISBN
	public void findByIsbnAndDelete(String isbn) {

		Catalogo found = em.find(Catalogo.class, isbn);
		if (found != null) {

			EntityTransaction t = em.getTransaction();

			t.begin();

			em.remove(found);

			t.commit();
			System.out.println("Catalogo eliminato correttamente");
		} else {
			System.out.println("Catalogo non trovato");
		}
	}

	// METODO CERCA CATALOGO PER ANNO DI PUBBLICAZIONE
	public Set<Catalogo> findByAnnoPubblicazione(LocalDate annoPubblicazione) {
		TypedQuery<Catalogo> query = em
				.createQuery("SELECT c FROM Catalogo c WHERE c.annoPubblicazione = :annoPubblicazione", Catalogo.class);
		query.setParameter("annoPubblicazione", annoPubblicazione);
		return new HashSet<>(query.getResultList());
	}

	// METODO CERCA PER AUTORE
	public Set<Catalogo> findByAutore(String autore) {
		TypedQuery<Catalogo> query = em.createQuery("SELECT l FROM Libri l WHERE l.autore = :autore", Catalogo.class);
		query.setParameter("autore", autore);
		return new HashSet<>(query.getResultList());
	}

	// METODO CERCA CATALOGO PER TITOLO
	public Set<Catalogo> findByTitolo(String titolo) {
		TypedQuery<Catalogo> query = em
				.createQuery("SELECT c FROM Catalogo c WHERE LOWER(c.titolo) LIKE LOWER(:titolo)", Catalogo.class);
		query.setParameter("titolo", "%" + titolo + "%");
		return new HashSet<>(query.getResultList());
	}
}
