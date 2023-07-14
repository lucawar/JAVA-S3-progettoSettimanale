package app;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import ENUM.Periodicità;
import dao.CatalogoDAO;
import dao.PrestitoDAO;
import dao.UtenteDAO;
import entities.Catalogo;
import entities.Libri;
import entities.Prestito;
import entities.Riviste;
import entities.Utente;
import util.JpaUtil;

public class MainArchivio {
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {

		EntityManager em = emf.createEntityManager();
		System.out.println("DATABASE COLLEGATO CON SUCCESSO!!!!");

		CatalogoDAO catalogoDAO = new CatalogoDAO(em);
		PrestitoDAO prestitoDAO = new PrestitoDAO(em);
		UtenteDAO utenteDAO = new UtenteDAO(em);

		Set<Catalogo> catalogo = new HashSet();

		// CREO UTENTE
		Utente utente1 = new Utente("Luca", "Guerra", "1992-11-05", 5111);
		Utente utente2 = new Utente("Mario", "Brega", "1960-09-05", 6222);

		// SALVO UTENTE
		utenteDAO.save(utente1);
		utenteDAO.save(utente2);

		// CREO LIBRI
		Libri libro1 = new Libri("2111", "Il Signore Degli Anelli", LocalDate.of(1970, 05, 11), 500, "J.R.R. Tolkien",
				"Fantasy", null);
		Libri libro2 = new Libri("3222", "Harry Potter", LocalDate.of(1979, 11, 01), 300, "J.K.Rowling", "Fantasy",
				null);

		// CREO RIVISTE
		Riviste rivista1 = new Riviste("6678901234", "Focus", LocalDate.of(1970, 05, 11), 500, Periodicità.MENSILE);
		Riviste rivista2 = new Riviste("7321098765", "Time", LocalDate.of(1979, 11, 01), 40, Periodicità.SETTIMANALE);

		// AGGIUNGO A CATALOGO
		catalogo.add(libro1);
		catalogo.add(libro2);
		catalogo.add(rivista1);
		catalogo.add(rivista2);

		// SALVO CATALOGO
		for (Catalogo cat : catalogo) {
			catalogoDAO.save(cat);
		}

		// CREO PRESTITO
		Prestito prestito1 = new Prestito(utente1, libro1, "2023-07-14", "2023-07-14", "2023-08-14");
		Prestito prestito2 = new Prestito(utente1, libro1, "2023-07-14", "2023-09-14", "2023-10-14");

		// SALVO PRESTITO

		// catalogoDAO.save(rivista1);
		// prestito1.setElementoPrestato(rivista1);
		// prestitoDAO.save(prestito1);

		// catalogoDAO.save(rivista2);
		// prestito2.setElementoPrestato(libro1);
		// prestitoDAO.save(prestito2);

		utente1.getPrestito().add(prestito1);
		utenteDAO.save(utente1);

		utente2.getPrestito().add(prestito2);
		utenteDAO.save(utente2);

		// METODI CatalogoDAO

		// CERCO CATALOGO
		Catalogo catalogoByIsbn = catalogoDAO.findByIsbn("2111");
		System.out.println("Catalogo trovato per ISBN: " + catalogoByIsbn);

		// CANCELLA CATOLOGO
		catalogoDAO.findByIsbnAndDelete("3222");

		// RICERCA PER ANNO DI PUBBLICAZIONE
		Set<Catalogo> catalogoByAnnoPubblicazione = catalogoDAO.findByAnnoPubblicazione(LocalDate.of(1970, 05, 11));
		System.out.println("Catalogo trovato per anno di pubblicazione: " + catalogoByAnnoPubblicazione);

		// CERCA PER AUTORE
		Set<Catalogo> libriByAutore = catalogoDAO.findByAutore("J.R.R. Tolkien");
		System.out.println("Catalogo trovatO per autore: " + libriByAutore);

		// CERCA CATALOGO PER TITOLO
		Set<Catalogo> catalogoByTitolo = catalogoDAO.findByTitolo("Il Signore Degli Anelli");
		System.out.println("Catalogo trovato per titolo: " + catalogoByTitolo);

		// METODI UtenteDAO

		// CERCA UTENTI PER ID
		Utente utenteById = utenteDAO.findById(1);
		System.out.println("Utente trovato per ID: " + utenteById);

		// METODI prestitoDAO

		// RICERCA PER NUMERO TESSERA
		Set<Prestito> prestitiByNumeroTessera = prestitoDAO.findPrestitiByNumeroTessera(5111);
		System.out.println("Prestiti trovati per numero tessera: " + prestitiByNumeroTessera);

		// RICERCA PRESTITO SCADUTO
		Set<Prestito> prestitiScaduti = prestitoDAO.findPrestitiScaduti();
		System.out.println("Prestiti scaduti: " + prestitiScaduti);

		// CHIUSURA
		em.close();
		emf.close();
	}
}
