package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import util.JpaUtil;

public class MainArchivio {
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {

		EntityManager em = emf.createEntityManager();
		System.out.println("DATABASE COLLEGATO CON SUCCESSO!!!!");
	}

}
