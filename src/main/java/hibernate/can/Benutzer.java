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

@Entity
@Table(name = "benutzer")
public class Benutzer {

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
	
	
	public int getBenutzerid() {
		return benutzerid;
	}
	public void setBenutzerid(int benutzerid) {
		this.benutzerid = benutzerid;
	}
	public String getBenutzername() {
		return benutzername;
	}
	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}
	public String getPasswort() {
		return passwort;
	}
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	public boolean getIs_online() {
		return is_online;
	}
	public void setIs_online(boolean is_online) {
		this.is_online = is_online;
	}
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
	public void setFettigkeitsgrad(int fettigkeitsgrad) {
		this.fettigkeitsgrad = fettigkeitsgrad;
	}
	public int getHighscore() {
		return highscore;
	}
	public void setHighscore(int highscore) {
		this.highscore = highscore;
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
	
	
}
