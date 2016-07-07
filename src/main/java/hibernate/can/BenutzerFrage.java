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
@Table(name = "benutzer_frage")
public class BenutzerFrage {

	@Id @GeneratedValue
	@Column(name = "benfragid")
	private int benfrageId;
	
	@Column(name = "fk_benutzer")
	private int fk_benutzer;
	
	@Column(name = "fk_frage")
	private int fk_frage;
	
	@Column(name = "is_done")
	private boolean is_done;
	
	
	public int getBenfrageId() {
		return benfrageId;
	}
	public void setBenfrageId(int benfrageId) {
		this.benfrageId = benfrageId;
	}
	public int getFk_benutzer() {
		return fk_benutzer;
	}
	public void setFk_benutzer(int fk_benutzer) {
		this.fk_benutzer = fk_benutzer;
	}
	public int getFk_frage() {
		return fk_frage;
	}
	public void setFk_frage(int fk_frage) {
		this.fk_frage = fk_frage;
	}
	public boolean getIs_done() {
		return is_done;
	}
	
	//Wenn eine Frage richtig beantwortet wurde
	public void setIs_done(int benutzerid) {
		Session session;
		// Connection erstellen
		session = DataManipulation.getConnection();

		//Transaction erstellen
		Transaction t= session.beginTransaction();

		//Neue HQL Abfrage um den User als Online zu markieren
		String hql2 = "UPDATE Benutzer b SET b.is_done = TRUE WHERE b.benutzerid = :bnid";
		Query query2 = session.createQuery(hql2);
		query2.setParameter("bnid", benutzerid);

		//DB Abfrage ausführen
		query2.executeUpdate();

		//Commit auf der Datenbank ausf�hren
		t.commit();

		//Datenbank schlie�en
		DataManipulation.closeConnection(session);
	}
	
}
