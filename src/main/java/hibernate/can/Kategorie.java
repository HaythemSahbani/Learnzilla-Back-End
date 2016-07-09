package hibernate.can;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
@Table(name = "kategorie")
public class Kategorie {
	private static final Logger log = Logger.getLogger(Kategorie.class.getName());
	@Id
	@GeneratedValue
	@Column(name = "kategorieid")
	private int kategorieId;

	@Column(name = "beschreibung")
	private String beschreibung;

	public int getKategorieId() {
		return kategorieId;
	}

	public void setKategorieId(int kategorieId) {
		this.kategorieId = kategorieId;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public static HashMap<Integer,String> getAllKategorie()
	{
		Session session;
		session = DataManipulation.getConnection();
		try{
		Transaction t = session.beginTransaction();
		
		@SuppressWarnings("unchecked")
		List<Kategorie> list = session.createQuery("FROM Kategorie k").list();
	
		HashMap<Integer,String>  katmap = new HashMap<Integer,String>();
		
		for(int i = 0; i<list.size();i++)
		{
			katmap.put(list.get(i).getKategorieId(), list.get(i).getBeschreibung());
		}
		
		DataManipulation.closeConnection(session);
		return katmap;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "Kategorieliste konnte nicht erstellt werden");
			e.printStackTrace();
		}
		return null;
	}

}
