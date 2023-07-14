package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Utente;

public class UtenteDAO {

	private final EntityManager em;

	public UtenteDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Utente s) {
		EntityTransaction t = em.getTransaction();
		t.begin();

		em.persist(s);

		t.commit();

		System.out.println("Utente salvato correttamente");
	}

	public Utente findById(long id) {
		Utente found = em.find(Utente.class, id);
		return found;
	}

	public void findByIdAndDelete(long id) {

		Utente found = em.find(Utente.class, id);
		if (found != null) {

			EntityTransaction t = em.getTransaction();

			t.begin();

			em.remove(found);

			t.commit();
			System.out.println("Utente eliminato correttamente");
		} else {
			System.out.println("Utente non trovato");
		}
	}

	public void refresh(long id) {
		Utente found = em.find(Utente.class, id);
		found.setId(id);

		System.out.println("PRE REFRESH");
		System.out.println(found);

		em.refresh(found);
		System.out.println("POST REFRESH");
		System.out.println(found);

	}
}
