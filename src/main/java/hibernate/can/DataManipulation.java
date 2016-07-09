package hibernate.can;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import logic.BusinessLogic;


public class DataManipulation {
	
	private static final Logger log = Logger.getLogger(DataManipulation.class.getName());

	//Hier wird die Connection ge�ffnet und eine Session zur�ckgegeben
	public static Session getConnection()
	{
		try{
		System.out.println("Connection wird hergestellt");
		Configuration cfg =new Configuration();
		cfg.configure("hibernate.learnzilla.cfg.xml"); //populates the data of the configuration file
		
		//creating session factory object
		@SuppressWarnings("deprecation")
		SessionFactory factory = cfg.buildSessionFactory();
		
		//creating session object
		Session session=factory.openSession();
		return session;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "Datenbank Connection konnte nicht hergestellt werden");
		}
		
				return null;
	}
	
	// Hier wird die Connection zur Datenbank geschlossen
	public static void closeConnection(Session session)
	{
		try{
			System.out.println("Connection wird geschlossen");
			session.close();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "Datenbank Connection konnte nicht geschlossen werden");
		}

	}
	
}
