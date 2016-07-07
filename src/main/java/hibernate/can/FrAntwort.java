package hibernate.can;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import models.IFrAntwort;

public class FrAntwort implements IFrAntwort {
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

	public static FrAntwort getFrAntwort(int benutzerId, int kategorie){
		
		//Datenbank nach Benutzer durchsuchen und schwierigkeitsgrad zurück geben
		
		Session session;
		// Connection erstellen
		session = DataManipulation.getConnection();
		
		//Transaction erstellen
		Transaction t= session.beginTransaction();
		
		//neue Objekte
		Benutzer currentUser = new Benutzer();
		Fragen currentFrage = new Fragen();
		
		//Neue HQL Abfrage
		Query query = session.createQuery("FROM Benutzer b WHERE b.benutzerid =:bnid");
		query.setParameter("bnid", benutzerId);
		//List results = query.list();
		//Die Ausgabe des Querys wird in eine Liste von Benutzern gespeichert (falls mehrere zutreffen)
		List<Benutzer> list = session.createCriteria(Benutzer.class).list();
		
		//BenutzerObjekt in Ram speichern
		currentUser = list.get(0);
		
		int sg = currentUser.getFettigkeitsgrad();
		
		//Datenbank Fragentabelle nach Frage durchsuchen die Kategorie + SG enthält return fragenid
		
		// EInbauen ob Frage schon beantwortet ---------!!!!!!--------------
		Query query2 = session.createQuery("FROM Fragen f where f.fk_kategorie =:fkid AND f.schwierigkeit =:fsg");
		query2.setParameter("fkid", kategorie);
		query2.setParameter("fsg", sg);
		
		List<Fragen> flist = session.createCriteria(Fragen.class).list();
		currentFrage = flist.get(0);
	
		int fid= currentFrage.getFragenid();

		//Datenbank Mapping suchen nach fragenid, return alle Antwortenid
		Query query3 = session.createQuery("FROM FragenAntwortenMapping fam where fam.fk_frage =:famId");
		query3.setParameter("famId",fid);
		List<FragenAntwortenMapping> famList = session.createCriteria(FragenAntwortenMapping.class).list();
		
		List<Antworten> alist = new ArrayList<Antworten>();
		
		for(int i = 0; i < famList.size(); i++)
		{
			Query query4 = session.createQuery("FROM Antworten a where a.antwortenid =:aid");
			query4.setParameter("aid", famList.get(i).getFk_antwort());
			List<Antworten> salist = new ArrayList<Antworten>();
			salist = session.createCriteria(Antworten.class).list();
			alist.add(salist.get(0));
	
	
		}
		
		
		//Frantwort objekt erstellen
		FrAntwort currentFrantwort = new FrAntwort();
		
		//setAntwortenListe (liste mit Antwort Objekten)
		currentFrantwort.setAntwortenList(alist);
		
		//setFrage anhand daten aus der Datenbank
		currentFrantwort.setFrage(currentFrage);
		
		//return Frantwort
		DataManipulation.closeConnection(session);
		return currentFrantwort;
		
	}
}
