package hibernate.can;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import logic.BusinessLogic;
import models.IBenutzer;

@Entity
@Table(name = "benutzer")
public class Benutzer implements IBenutzer {
	private static final Logger log = Logger.getLogger(Benutzer.class.getName());

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


	@Override
	public void setIs_online(boolean is_online) {
		this.is_online = is_online;
	}

	@Override
	public int getFettigkeitsgrad() {
		Session session;
		session = DataManipulation.getConnection();
		try{
		Transaction t = session.beginTransaction();

		Benutzer currentUser = new Benutzer();

		// Neue HQL Abfrage
		List<Benutzer> list = session.createQuery("FROM Benutzer b WHERE b.benutzerid =:bid")
				.setParameter("bid", this.getBenutzerid()).list();
		
		currentUser = list.get(0);

		int fettigkeitsgrad = currentUser.fettigkeitsgrad;
		DataManipulation.closeConnection(session);

		return fettigkeitsgrad;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "Fettigkeitsgrad konnte nicht ermittelt werden");
		}
		return 0;
	}

	@Override
	public void setFettigkeitsgrad(int fettigkeitsgrad) {
		Session session;
		// Connection erstellen
		session = DataManipulation.getConnection();

		try{
		// Transaction erstellen
		Transaction t = session.beginTransaction();

		// neues Benutzerobjekt
		Benutzer currentUser = new Benutzer();

		// Neue HQL Abfrage
		List<Benutzer> list = session.createQuery("FROM Benutzer b WHERE b.benutzerid =:bnid")
				.setParameter("bnid", this.getBenutzerid()).list();

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
		catch (Exception e)
		{
			log.log(Level.SEVERE, "Fettigkeitsrad konnte nicht gesetzt werden");
		}
		 
	}

	@Override
	public int getHighscore() {
		Session session;
		session = DataManipulation.getConnection();
		try{
		Transaction t = session.beginTransaction();

		Benutzer currentUser = new Benutzer();

		// Neue HQL Abfrage
		List<Benutzer> list = session.createQuery("FROM Benutzer b WHERE b.benutzerid =:bid")
				.setParameter("bid", this.getBenutzerid()).list();
		
		currentUser = list.get(0);

		int highscore = currentUser.highscore;
		DataManipulation.closeConnection(session);

		return highscore;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "Highscore konnte nicht gesetzt werden");
		}
		return 0;
	}


	@Override
	public void setHighscore(int highscore) {

		Session session;
		// Connection erstellen
		session = DataManipulation.getConnection();

		try{
		// Transaction erstellen
		Transaction t = session.beginTransaction();

		// neues Benutzerobjekt
		Benutzer currentUser = new Benutzer();

		// Neue HQL Abfrage
		List<Benutzer> list = session.createQuery("FROM Benutzer b WHERE b.benutzerid =:bnid")
				.setParameter("bnid", this.getBenutzerid()).list();		
		
		// BenutzerObjekt in Ram speichern
		currentUser = list.get(0);

		// Neue HQL Abfrage um den User als Online zu markieren
		Query query2 = session.createQuery("UPDATE Benutzer b SET b.highscore = hscore WHERE b.benutzerid = :bnid")
				.setParameter("hscore", highscore).setParameter("bnid", currentUser.benutzerid);

		// Query ausf�hren
		int result2 = query2.executeUpdate();
		System.out.println(result2);

		// Commit auf der Datenbank ausf�hren
		t.commit();

		// Datenbank schlie�en
		DataManipulation.closeConnection(session);
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "Highscore konnte nicht gesetzt werden");
		}
		
	}

	public static Benutzer login(String Benutzername, String passwort) {
		Session session;
		// Connection erstellen
		session = DataManipulation.getConnection();

		try{
		// Transaction erstellen
		Transaction t = session.beginTransaction();

		// neues Benutzerobjekt
		Benutzer currentUser = new Benutzer();

		session.clear();
		// Neue HQL Abfrage

		List<Benutzer> list = session.createQuery("FROM Benutzer b WHERE b.benutzername = :bname")
				.setString("bname", Benutzername).list();
				
		// BenutzerObjekt in Ram speichern
		currentUser = list.get(0);
		System.out.println(currentUser.benutzername.toString());

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
		catch (Exception e)
		{
			log.log(Level.SEVERE, "Benutzer konnte nicht eingeloggt werden");
		}
		return null;
	}

	public void logout() {
		Session session;
		// Connection erstellen
		session = DataManipulation.getConnection();

		try{
		// Transaction erstellen
		Transaction t = session.beginTransaction();

		// neues Benutzerobjekt
		Benutzer currentUser = new Benutzer();

		// Neue HQL Abfrage
		List<Benutzer> list = session.createQuery("FROM Benutzer b WHERE b.benutzerid =:bnid")
				.setParameter("bnid", this.benutzerid).list();

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
		catch (Exception e)
		{
			log.log(Level.SEVERE, "Benutzer konnte nicht ausgeloggt werden");
		}
		
	}

	public static Benutzer registration() {
		return null;
	}

	public static Benutzer getBenutzerObjekt(int benutzerID) {
		Session session;
		session = DataManipulation.getConnection();
		try{
		Transaction t = session.beginTransaction();

		Benutzer currentUser = new Benutzer();

		// Neue HQL Abfrage
		List<Benutzer> list = session.createQuery("FROM Benutzer b WHERE b.benutzerid =:bid")
				.setParameter("bid", benutzerID).list();
		currentUser = list.get(0);

		DataManipulation.closeConnection(session);

		return currentUser;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "Benutzerobjekt konnte nicht zurück gegeben werden");
		}
		return null;
	}

	public static List<Benutzer> getBenutzerListOnline() {
		Session session;
		session = DataManipulation.getConnection();
		try{
		Transaction t = session.beginTransaction();
		List<Benutzer> benonlineListe =session.createQuery("FROM Benutzer b WHERE b.is_online = TRUE").list();

		DataManipulation.closeConnection(session);

		return benonlineListe;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "Benutzerliste konnte nicht erstellt werden");
		}
		return null;
	}

}
