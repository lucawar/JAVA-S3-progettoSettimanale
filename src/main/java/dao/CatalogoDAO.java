package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Catalogo;

public class CatalogoDAO {

	private final EntityManager em;

	public CatalogoDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Catalogo s) {
		EntityTransaction t = em.getTransaction();
		t.begin();

		em.persist(s);

		t.commit();

		System.out.println("Evento salvato correttamente");
	}

	public Catalogo findById(long id) {
		Catalogo found = em.find(Catalogo.class, id);
		return found;
	}

	public void findByIdAndDelete(long id) {

		Catalogo found = em.find(Catalogo.class, id);
		if (found != null) {

			EntityTransaction t = em.getTransaction();

			t.begin();

			em.remove(found);

			t.commit();
			System.out.println("Evento eliminato correttamente");
		} else {
			System.out.println("Evento non trovato");
		}
	}

	public void refresh(long id) {
		Catalogo found = em.find(Catalogo.class, id);
		found.setTitolo("");

		System.out.println("PRE REFRESH");
		System.out.println(found);

		em.refresh(found);
		System.out.println("POST REFRESH");
		System.out.println(found);

	}
}
