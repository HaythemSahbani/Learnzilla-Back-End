package hibernate.can;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import models.IBenutzer;

@Entity
@Table(name = "benutzer")
public class Benutzer implements IBenutzer {

	@Id
	@GeneratedValue
	@Column(name = "benutzerid")
	private int benutzerid;

	@Column(name = "benutzername")
	private String benutzername;

	@Column(name = "passwort")
	private String passwort;

	@Column(name = "is_online")
	private boolean is_online;

	@Column(name = "fettigkeitsgrad")
	private int fettigkeitsgrad;

	@Column(name = "highscore")
	private int highscore;

	@Override
	public int getBenutzerid() {
		return benutzerid;
	}

	@Override
	public void setBenutzerid(int Benutzerid) {
		this.benutzerid = Benutzerid;
	}

	@Override
	public String getBenutzername() {
		return benutzername;
	}

	@Override
	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}

	@Override
	public String getPasswort() {
		return passwort;
	}

	@Override
	public void setPasswort(String Password) {
		this.passwort = Password;
	}

	/*
	@Override
	public boolean getIs_online() {
		
		Session session;
		session = DataManipulation.getConnection();
		Transaction t = session.beginTransaction();

		Benutzer currentUser = new Benutzer();

		// Neue HQL Abfrage
		Query query = session.createQuery("FROM Benutzer b WHERE b.benutzerid =:bid");
		query.setParameter("bid", this.getBenutzerid());
		
		// Die Ausgabe des Querys wird in eine Liste von Benutzern gespeichert
		// (falls mehrere zutreffen)
		List<Benutzer> list = session.createCriteria(Benutzer.class).list();
		currentUser = list.get(0);

		boolean is_online = currentUser.is_online;
		DataManipulation.closeConnection(session);

		return is_online;
	}
	*/

	@Override
	public void setIs_online(boolean is_online) {
		this.is_online = is_online;
	}

	@Override
	public int getFettigkeitsgrad() {
		Session session;
		session = DataManipulation.getConnection();
		Transaction t = session.beginTransaction();

		Benutzer currentUser = new Benutzer();

		// Neue HQL Abfrage
		Query query = session.createQuery("FROM Benutzer b WHERE b.benutzerid =:bid");
		query.setParameter("bid", this.getBenutzerid());
		// List results = query.list();
		// Die Ausgabe des Querys wird in eine Liste von Benutzern gespeichert
		// (falls mehrere zutreffen)
		List<Benutzer> list = session.createCriteria(Benutzer.class).list();
		currentUser = list.get(0);

		int fettigkeitsgrad = currentUser.fettigkeitsgrad;
		DataManipulation.closeConnection(session);

		return fettigkeitsgrad;
	}

	@Override
	public void setFettigkeitsgrad(int fettigkeitsgrad) {
		Session session;
		// Connection erstellen
		session = DataManipulation.getConnection();

		// Transaction erstellen
		Transaction t = session.beginTransaction();

		// neues Benutzerobjekt
		Benutzer currentUser = new Benutzer();

		// Neue HQL Abfrage
		Query query = session.createQuery("FROM Benutzer b WHERE b.benutzerid =:bnid");
		query.setParameter("bnid", this.getBenutzerid());
		
		// Die Ausgabe des Querys wird in eine Liste von Benutzern gespeichert
		// (falls mehrere zutreffen)
		List<Benutzer> list = session.createCriteria(Benutzer.class).list();

		// BenutzerObjekt in Ram speichern
		currentUser = list.get(0);

		// Neue HQL Abfrage um den User als Online zu markieren
		String hql2 = "UPDATE Benutzer b SET b.fettigkeitsgrad = :fgrad WHERE b.benutzerid = :bnid";
		Query query2 = session.createQuery(hql2);
		query2.setParameter("fgrad", currentUser.fettigkeitsgrad);
		query2.setParameter("bnid", currentUser.benutzerid);

		// Query ausf�hren
		int result2 = query2.executeUpdate();
		System.out.println(result2);

		// Commit auf der Datenbank ausf�hren
		t.commit();

		// Datenbank schlie�en
		DataManipulation.closeConnection(session);
	}

	@Override
	public int getHighscore() {
		Session session;
		session = DataManipulation.getConnection();
		Transaction t = session.beginTransaction();

		Benutzer currentUser = new Benutzer();

		// Neue HQL Abfrage
		Query query = session.createQuery("FROM Benutzer b WHERE b.benutzerid =:bid");
		query.setParameter("bid", this.getBenutzerid());
		// Die Ausgabe des Querys wird in eine Liste von Benutzern gespeichert
		// (falls mehrere zutreffen)
		List<Benutzer> list = session.createCriteria(Benutzer.class).list();
		currentUser = list.get(0);

		int highscore = currentUser.highscore;
		DataManipulation.closeConnection(session);

		return highscore;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hibernate.can.IBenutzer#setHighscore(int)
	 */
	@Override
	public void setHighscore(int highscore) {

		Session session;
		// Connection erstellen
		session = DataManipulation.getConnection();

		// Transaction erstellen
		Transaction t = session.beginTransaction();

		// neues Benutzerobjekt
		Benutzer currentUser = new Benutzer();

		// Neue HQL Abfrage
		Query query = session.createQuery("FROM Benutzer b WHERE b.benutzerid =:bnid");
		query.setParameter("bnid", this.getBenutzerid());
		
		// Die Ausgabe des Querys wird in eine Liste von Benutzern gespeichert
		// (falls mehrere zutreffen)
		List<Benutzer> list = session.createCriteria(Benutzer.class).list();

		// BenutzerObjekt in Ram speichern
		currentUser = list.get(0);

		// Neue HQL Abfrage um den User als Online zu markieren
		String hql2 = "UPDATE Benutzer b SET b.highscore = hscore WHERE b.benutzerid = :bnid";
		query.setParameter("hscore", highscore);
		Query query2 = session.createQuery(hql2);
		query2.setParameter("bnid", currentUser.benutzerid);

		// Query ausf�hren
		int result2 = query2.executeUpdate();
		System.out.println(result2);

		// Commit auf der Datenbank ausf�hren
		t.commit();

		// Datenbank schlie�en
		DataManipulation.closeConnection(session);
	}

	public static Benutzer login(String Benutzername, String passwort) {
		Session session;
		// Connection erstellen
		session = DataManipulation.getConnection();

		// Transaction erstellen
		Transaction t = session.beginTransaction();

		// neues Benutzerobjekt
		Benutzer currentUser = new Benutzer();

		// Neue HQL Abfrage
		//----------------!!!!!!!!!-------------------------------
		Query query = session.createQuery("FROM Benutzer b WHERE b.benutzername =:bname");
		query.setParameter("bname", Benutzername);
		// Die Ausgabe des Querys wird in eine Liste von Benutzern gespeichert
		// (falls mehrere zutreffen)
		List<Benutzer> list = session.createCriteria(Benutzer.class).list();

		// BenutzerObjekt in Ram speichern
		currentUser = list.get(0);
		System.out.println(list.get(0).getBenutzername().toString());

		// Neue HQL Abfrage um den User als Online zu markieren
		String hql2 = "UPDATE Benutzer b SET b.is_online = TRUE WHERE b.benutzerid = :bnid";
		Query query2 = session.createQuery(hql2);
		query2.setParameter("bnid", currentUser.benutzerid);

		// Query ausf�hren
		int result2 = query2.executeUpdate();
		System.out.println(result2);

		// Commit auf der Datenbank ausf�hren
		t.commit();

		// Datenbank schlie�en
		DataManipulation.closeConnection(session);
		// session.close();

		return currentUser;
	}

	public void logout() {
		Session session;
		// Connection erstellen
		session = DataManipulation.getConnection();

		// Transaction erstellen
		Transaction t = session.beginTransaction();

		// neues Benutzerobjekt
		Benutzer currentUser = new Benutzer();

		// Neue HQL Abfrage
		Query query = session.createQuery("FROM Benutzer b WHERE b.benutzerid =:bnid");
		query.setParameter("bnid", currentUser.getBenutzerid());
		// List results = query.list();
		// Die Ausgabe des Querys wird in eine Liste von Benutzern gespeichert
		// (falls mehrere zutreffen)
		List<Benutzer> list = session.createCriteria(Benutzer.class).list();

		// BenutzerObjekt in Ram speichern
		currentUser = list.get(0);

		// Neue HQL Abfrage um den User als Online zu markieren
		String hql2 = "UPDATE Benutzer b SET b.is_online = FALSE WHERE b.benutzerid = :bnid";
		Query query2 = session.createQuery(hql2);
		query2.setParameter("bnid", currentUser.benutzerid);

		// Query ausf�hren
		int result2 = query2.executeUpdate();
		System.out.println(result2);

		// Commit auf der Datenbank ausf�hren
		t.commit();

		// Datenbank schlie�en
		DataManipulation.closeConnection(session);
		// session.close();
	}

	public static Benutzer registration() {
		return null;
	}

	public static Benutzer getBenutzerObjekt(int benutzerID) {
		Session session;
		session = DataManipulation.getConnection();
		Transaction t = session.beginTransaction();

		Benutzer currentUser = new Benutzer();

		// Neue HQL Abfrage
		Query query = session.createQuery("FROM Benutzer b WHERE b.benutzerid =:bid");
		query.setParameter("bid", benutzerID);

		// Die Ausgabe des Querys wird in eine Liste von Benutzern gespeichert
		// (falls mehrere zutreffen)
		List<Benutzer> list = session.createCriteria(Benutzer.class).list();
		currentUser = list.get(0);

		DataManipulation.closeConnection(session);

		return currentUser;
	}

	public static List<Benutzer> getBenutzerListOnline() {
		Session session;
		session = DataManipulation.getConnection();
		Transaction t = session.beginTransaction();
		Query query = session.createQuery("FROM Benutzer b WHERE b.is_online = TRUE");
		List<Benutzer> benonlineListe = session.createCriteria(Benutzer.class).list();

		DataManipulation.closeConnection(session);

		return benonlineListe;
	}

}
