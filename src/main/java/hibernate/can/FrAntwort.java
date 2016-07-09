package hibernate.can;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;

import models.IFrAntwort;

/**
 * 
 * Ein FrAntwort-Objekt enthält ein Set aus Frage und Antwortmöglichkeiten.
 *
 */
public class FrAntwort implements IFrAntwort {
	private static final Logger log = Logger.getLogger(FrAntwort.class.getName());
	private Fragen frage;
	private List<Antworten> antwortenList;

	@Override
	public Fragen getFrage() {
		return frage;
	}

	@Override
	public void setFrage(Fragen frage) {
		this.frage = frage;
	}

	@Override
	public List<Antworten> getAntwortenList() {
		return antwortenList;
	}

	@Override
	public void setAntwortenList(List<Antworten> antwortenList) {
		this.antwortenList = antwortenList;
	}

	public static FrAntwort getFrAntwort(int benutzerId, int kategorie) {

		// Datenbank nach Benutzer durchsuchen und schwierigkeitsgrad zurück
		// geben

		Session session;
		// Connection erstellen
		session = DataManipulation.getConnection();

		// Transaction erstellen
		try {
			session.beginTransaction();

			// neue Objekte
			Benutzer currentUser = new Benutzer();
			Fragen currentFrage = new Fragen();

			// Neue HQL Abfrage
			@SuppressWarnings("unchecked")
			List<Benutzer> list = session.createQuery("FROM Benutzer b WHERE b.benutzerid =:bnid")
					.setParameter("bnid", benutzerId).list();

			// BenutzerObjekt in Ram speichern
			currentUser = list.get(0);

			int sg = currentUser.getFettigkeitsgrad();

			// Datenbank Fragentabelle nach Frage durchsuchen die Kategorie + SG
			// enthält return fragenid

			// EInbauen ob Frage schon beantwortet ---------!!!!!!--------------

			@SuppressWarnings("unchecked")
			List<Fragen> flist = session
					.createQuery("FROM Fragen f where f.fk_kategorie =:fkid AND f.schwierigkeit =:fsg")
					.setParameter("fkid", kategorie).setParameter("fsg", sg).list();

			// Random Zahl für die Frage

			int zahl;
			if (flist.size() > 1) {
				Random rand = new Random();
				zahl = rand.nextInt(flist.size()) + 1;
			} else
				zahl = 0;

			currentFrage = flist.get(zahl);

			int fid = currentFrage.getFragenid();

			@SuppressWarnings("unchecked")
			List<Antworten> famList = session
					.createQuery("FROM Antworten b where b.antwortenid in " + "(SELECT a.antwortenid "
							+ "FROM FragenAntwortenMapping m, Antworten a "
							+ "WHERE m.fk_frage = :famId and a.antwortenid = m.fk_antwort)")
					.setParameter("famId", fid).list();

			List<Antworten> alist = new ArrayList<Antworten>();
			for (int i = 0; i < famList.size(); i++) {
				Antworten a = new Antworten();
				a.setAntwort(famList.get(i).getAntwort());
				a.setAntwortenid(famList.get(i).getAntwortenid());
				a.setIs_correct(famList.get(i).getIs_correct());
				alist.add(i, a);
			}

			// Frantwort objekt erstellen
			FrAntwort currentFrantwort = new FrAntwort();

			// setAntwortenListe (liste mit Antwort Objekten)
			currentFrantwort.setAntwortenList(alist);

			// setFrage anhand daten aus der Datenbank
			currentFrantwort.setFrage(currentFrage);

			// return Frantwort
			DataManipulation.closeConnection(session);
			return currentFrantwort;
		} catch (IndexOutOfBoundsException e) {
			log.log(Level.SEVERE, "Für diese Kategorie gibt es aktuell keine offenen Fragen");
		}

		return null;
	}
}
