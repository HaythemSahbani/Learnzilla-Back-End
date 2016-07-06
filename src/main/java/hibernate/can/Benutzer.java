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

	@Id @GeneratedValue
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
	
	
	/* (non-Javadoc)
	 * @see hibernate.can.IBenutzer#getBenutzerid()
	 */
	@Override
	public int getBenutzerid() {
		return benutzerid;
	}
	/* (non-Javadoc)
	 * @see hibernate.can.IBenutzer#setBenutzerid(int)
	 */
	@Override
	public void setBenutzerid(int benutzerid) {
		this.benutzerid = benutzerid;
	}
	/* (non-Javadoc)
	 * @see hibernate.can.IBenutzer#getBenutzername()
	 */
	@Override
	public String getBenutzername() {
		return benutzername;
	}
	/* (non-Javadoc)
	 * @see hibernate.can.IBenutzer#setBenutzername(java.lang.String)
	 */
	@Override
	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}
	/* (non-Javadoc)
	 * @see hibernate.can.IBenutzer#getPasswort()
	 */
	@Override
	public String getPasswort() {
		return passwort;
	}
	/* (non-Javadoc)
	 * @see hibernate.can.IBenutzer#setPasswort(java.lang.String)
	 */
	@Override
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	/* (non-Javadoc)
	 * @see hibernate.can.IBenutzer#getIs_online()
	 */
	@Override
	public boolean getIs_online() throws Exception {
		Session session;
		session = DataManipulation.getConnection();
		Transaction t= session.beginTransaction();

		Benutzer currentUser = new Benutzer();

		//Neue HQL Abfrage
		Query query = session.createQuery("FROM Benutzer b WHERE b.benutzerid =:bid");
		query.setParameter("bid", this.getBenutzerid());
		//List results = query.list();
		//Die Ausgabe des Querys wird in eine Liste von Benutzern gespeichert (falls mehrere zutreffen)
		List<Benutzer> list = session.createCriteria(Benutzer.class).list();
		currentUser = list.get(0);

		boolean is_online = currentUser.is_online;
		DataManipulation.closeConnection(session);

		return is_online;
	}
	/* (non-Javadoc)
	 * @see hibernate.can.IBenutzer#setIs_online(boolean)
	 */
	@Override
	public void setIs_online(boolean is_online) {
		this.is_online = is_online;
	}
	/* (non-Javadoc)
	 * @see hibernate.can.IBenutzer#getFettigkeitsgrad()
	 */
	@Override
	public int getFettigkeitsgrad() throws Exception {
		Session session;
		session = DataManipulation.getConnection();
		Transaction t= session.beginTransaction();
		
		Benutzer currentUser = new Benutzer();

		//Neue HQL Abfrage
				Query query = session.createQuery("FROM Benutzer b WHERE b.benutzername =:bid");
					query.setParameter("bid", this.getBenutzerid());
				//List results = query.list();
				//Die Ausgabe des Querys wird in eine Liste von Benutzern gespeichert (falls mehrere zutreffen)
				List<Benutzer> list = session.createCriteria(Benutzer.class).list();
				currentUser = list.get(0);
				
		int fettigkeitsgrad = currentUser.fettigkeitsgrad; 
		DataManipulation.closeConnection(session);
		
		return fettigkeitsgrad;
	}
	/* (non-Javadoc)
	 * @see hibernate.can.IBenutzer#setFettigkeitsgrad(int)
	 */
	@Override
	public void setFettigkeitsgrad(int fettigkeitsgrad) throws Exception {
		Session session;
		// Connection erstellen
		session = DataManipulation.getConnection();

		//Transaction erstellen
		Transaction t= session.beginTransaction();

		//neues Benutzerobjekt
		Benutzer currentUser = new Benutzer();

		//Neue HQL Abfrage
		Query query = session.createQuery("FROM Benutzer b WHERE b.benutzerid =:bnid");
		query.setParameter("bnid", this.getBenutzerid());
		//List results = query.list();
		//Die Ausgabe des Querys wird in eine Liste von Benutzern gespeichert (falls mehrere zutreffen)
		List<Benutzer> list = session.createCriteria(Benutzer.class).list();

		//BenutzerObjekt in Ram speichern
		currentUser = list.get(0);

		//Neue HQL Abfrage um den User als Online zu markieren
		String hql2 = "UPDATE Benutzer b SET b.fettigkeitsgrad = :fgrad WHERE b.benutzerid = :bnid";
		Query query2 = session.createQuery(hql2);
		query2.setParameter("fgrad", currentUser.fettigkeitsgrad);
		query2.setParameter("bnid", currentUser.benutzerid);

		//Query ausf�hren
		int result2 = query2.executeUpdate();
		System.out.println(result2);

		//Commit auf der Datenbank ausf�hren
		t.commit();

		//Datenbank schlie�en
		DataManipulation.closeConnection(session);
	}
	/* (non-Javadoc)
	 * @see hibernate.can.IBenutzer#getHighscore()
	 */
	@Override
	public int getHighscore() throws Exception {
		Session session;
		session = DataManipulation.getConnection();
		Transaction t= session.beginTransaction();

		Benutzer currentUser = new Benutzer();

		//Neue HQL Abfrage
		Query query = session.createQuery("FROM Benutzer b WHERE b.benutzerid =:bid");
		query.setParameter("bid", this.getBenutzerid());
		//List results = query.list();
		//Die Ausgabe des Querys wird in eine Liste von Benutzern gespeichert (falls mehrere zutreffen)
		List<Benutzer> list = session.createCriteria(Benutzer.class).list();
		currentUser = list.get(0);

		int highscore = currentUser.highscore;
		DataManipulation.closeConnection(session);

		return highscore;
	}
	/* (non-Javadoc)
	 * @see hibernate.can.IBenutzer#setHighscore(int)
	 */
	@Override
	public void setHighscore(int highscore) throws Exception{

		Session session;
		// Connection erstellen
		session = DataManipulation.getConnection();

		//Transaction erstellen
		Transaction t= session.beginTransaction();

		//neues Benutzerobjekt
		Benutzer currentUser = new Benutzer();

		//Neue HQL Abfrage
		Query query = session.createQuery("FROM Benutzer b WHERE b.benutzerid =:bnid");
		query.setParameter("bnid", this.getBenutzerid());
		//List results = query.list();
		//Die Ausgabe des Querys wird in eine Liste von Benutzern gespeichert (falls mehrere zutreffen)
		List<Benutzer> list = session.createCriteria(Benutzer.class).list();

		//BenutzerObjekt in Ram speichern
		currentUser = list.get(0);

		//Neue HQL Abfrage um den User als Online zu markieren
		String hql2 = "UPDATE Benutzer b SET b.highscore = hscore WHERE b.benutzerid = :bnid";
		query.setParameter("hscore", highscore);
		Query query2 = session.createQuery(hql2);
		query2.setParameter("bnid", currentUser.benutzerid);

		//Query ausf�hren
		int result2 = query2.executeUpdate();
		System.out.println(result2);

		//Commit auf der Datenbank ausf�hren
		t.commit();

		//Datenbank schlie�en
		DataManipulation.closeConnection(session);
	}
	
	public static Benutzer login(String Benutzername, String passwort) throws Exception
	{
		Session session;
		// Connection erstellen
		session = DataManipulation.getConnection();
		
		//Transaction erstellen
		Transaction t= session.beginTransaction();
		
		//neues Benutzerobjekt
		Benutzer currentUser = new Benutzer();
		
		//Neue HQL Abfrage
		Query query = session.createQuery("FROM Benutzer b WHERE b.benutzername =Benutzername");
		//List results = query.list();
		//Die Ausgabe des Querys wird in eine Liste von Benutzern gespeichert (falls mehrere zutreffen)
		List<Benutzer> list = session.createCriteria(Benutzer.class).list();
		
		//BenutzerObjekt in Ram speichern
		currentUser = list.get(0);
		
		//Neue HQL Abfrage um den User als Online zu markieren
		String hql2 = "UPDATE Benutzer b SET b.is_online = TRUE WHERE b.benutzerid = :bnid";
		Query query2 = session.createQuery(hql2);
		query2.setParameter("bnid", currentUser.benutzerid);
		
		//Query ausf�hren
		int result2 = query2.executeUpdate();
		System.out.println(result2);
		
		//Commit auf der Datenbank ausf�hren
		t.commit();
		
		//Datenbank schlie�en
		DataManipulation.closeConnection(session);
		//session.close();
		
		return currentUser;
	}
	public static void logout() throws Exception
	{
		Session session;
		// Connection erstellen
		session = DataManipulation.getConnection();

		//Transaction erstellen
		Transaction t= session.beginTransaction();

		//neues Benutzerobjekt
		Benutzer currentUser = new Benutzer();

		//Neue HQL Abfrage
		Query query = session.createQuery("FROM Benutzer b WHERE b.benutzerid =:bnid");
		query.setParameter("bnid", currentUser.getBenutzerid());
		//List results = query.list();
		//Die Ausgabe des Querys wird in eine Liste von Benutzern gespeichert (falls mehrere zutreffen)
		List<Benutzer> list = session.createCriteria(Benutzer.class).list();

		//BenutzerObjekt in Ram speichern
		currentUser = list.get(0);

		//Neue HQL Abfrage um den User als Online zu markieren
		String hql2 = "UPDATE Benutzer b SET b.is_online = FALSE WHERE b.benutzerid = :bnid";
		Query query2 = session.createQuery(hql2);
		query2.setParameter("bnid", currentUser.benutzerid);

		//Query ausf�hren
		int result2 = query2.executeUpdate();
		System.out.println(result2);

		//Commit auf der Datenbank ausf�hren
		t.commit();

		//Datenbank schlie�en
		DataManipulation.closeConnection(session);
		//session.close();
	}
	
}
