package hibernate.can;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class DataManipulation {
	

	//Hier wird die Connection geöffnet und eine Session zurückgegeben
	public static Session getConnection() throws Exception
	{
		System.out.println("Connection wird hergestellt");
		Configuration cfg =new Configuration();
		cfg.configure("hibernate.learnzilla.cfg.xml"); //populates the data of the configuration file
		
		//creating session factory object
		SessionFactory factory = cfg.buildSessionFactory();
		
		//creating session object
		Session session=factory.openSession();
		
		return session;		
	}
	
	// Hier wird die Connection zur Datenbank geschlossen
	public static void closeConnection(Session session)
	{
		System.out.println("Connection wird geschlossen");
		session.close();
	}
	
}
