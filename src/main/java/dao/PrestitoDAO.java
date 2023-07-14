package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Prestito;

public class PrestitoDAO {

	private final EntityManager em;

	public PrestitoDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Prestito s) {
		EntityTransaction t = em.getTransaction();
		t.begin();

		em.persist(s);

		t.commit();

		System.out.println("Prestito salvata correttamente");
	}

	public Prestito findById(long id) {
		Prestito found = em.find(Prestito.class, id);
		return found;
	}

	public void findByIdAndDelete(long id) {

		Prestito found = em.find(Prestito.class, id);
		if (found != null) {

			EntityTransaction t = em.getTransaction();

			t.begin();

			em.remove(found);

			t.commit();
			System.out.println("Prestito eliminato correttamente");
		} else {
			System.out.println("Prestito non trovato");
		}
	}

	public void refresh(long id) {
		Prestito found = em.find(Prestito.class, id);
		found.setId(id);

		System.out.println("PRE REFRESH");
		System.out.println(found);

		em.refresh(found);
		System.out.println("POST REFRESH");
		System.out.println(found);

	}
}
