package hibernate.can;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.Transaction;

import logic.BusinessLogic;

@Entity
@Table(name = "zitate")
public class Zitate {
	private static final Logger log = Logger.getLogger(Zitate.class.getName());
	@Id @GeneratedValue
	@Column(name = "zitatid")
	private int zitatid;
	
	@Column(name = "zitat")
	private String zitat;
	
	
	public int getZitateid() {
		return zitatid;
	}
	public void setZitateid(int zitatid) {
		this.zitatid = zitatid;
	}
	
	//DB Befehl einbauen
	public static String getZitate() {
		Session session;
		session = DataManipulation.getConnection();
		try{
		Transaction t = session.beginTransaction();

		Benutzer currentUser = new Benutzer();
		Zitate currentZitat = new Zitate();
		
		
		// Neue HQL Abfrage
		List<Zitate> list =session.createQuery("FROM Zitate").list();
		Random rand = new Random();
		int zahl = rand.nextInt(list.size())+1;
		//System.out.println("Random Zahl" + zahl);

		List<Zitate> list2 = session.createQuery("FROM Zitate z where z.zitatid =:zid")
				.setParameter("zid", zahl).list();
		
		currentZitat = list2.get(0);

		String zitat = currentZitat.zitat;
		DataManipulation.closeConnection(session);

		return zitat;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "Zitate konnte nicht geholt werden");
		}
		return null;
	}
	public void setZitate(String zitate) {
		this.zitat = zitate;
	}
	
}
