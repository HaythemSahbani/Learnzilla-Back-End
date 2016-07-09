package logic;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.can.Benutzer;
import hibernate.can.DataManipulation;
import hibernate.can.FrAntwort;
import hibernate.can.Kategorie;
import hibernate.can.Zitate;
import models.IBenutzer;
import models.IFrAntwort;

public class BusinessLogic {
	
	private static final Logger log = Logger.getLogger(BusinessLogic.class.getName());
	
	//Liefert objekt mit der frage und den antworten
	public static IFrAntwort getQuestionFromDB(int benutzerId, int kategorie) {
		try {
			IFrAntwort IFrAntwort = FrAntwort.getFrAntwort(benutzerId, kategorie);
			return IFrAntwort;
		} catch (Exception e) {
			return null;
		}
	}
	
	//pruefe ob antwort richtig ist, gebe entsprechend geaendertes userobjekt mit
	public static IBenutzer setQuestionAnsweredCorrectForUser(){
		return null;
	}
	
	//pruefen ob user existiert, falls nicht, neu erstellen
	public static IBenutzer loginUser(String username, String password) {
		
		IBenutzer benutzer;
		try {
			// hammer verschluesselung
	        byte[] authBytes = password.getBytes(StandardCharsets.UTF_8);
	        password = Base64.getEncoder().encodeToString(authBytes);
	        
			benutzer = Benutzer.login(username, password);
		} catch (Exception e) {
			log.log(Level.SEVERE, "Login fehlgeschlagen!");
			benutzer = null;
		}
		
		return benutzer;
	}
	
	// logge user aus
	public static boolean logoutUser(int benutzerId) {
		Benutzer benutzer = Benutzer.getBenutzerObjekt(benutzerId);
		try {
			benutzer.logout();
			return true;
		} catch (Exception e) {
			log.log(Level.SEVERE, "Logout fehlgeschlagen!");
			e.printStackTrace();
		}
		return false;
	}
	
	//liefert eine liste mit den usern, welche gerade online sind
	public static List<Benutzer> getUsersOnline(){
		try {
			return Benutzer.getBenutzerListOnline();
		} catch (Exception e) {
			log.log(Level.SEVERE, "getUsersOnline fehlgeschlagen!");
			e.printStackTrace();
			return null;
		}
	}
	
	//liefert ein Benutzer-Objekt zu einer id zurueck
	public static IBenutzer getBenutzerInfos(int benutzerId){
		try {
			Benutzer benutzer = Benutzer.getBenutzerObjekt(benutzerId);
			return benutzer;
		} catch (Exception e) {
			log.log(Level.SEVERE, "getBenutzerInfos fehlgeschlagen!");
			e.printStackTrace();
			return null;
		}
	}
	
	//liefert ein Zitat
	public static String getZitat(){
		try {
			return Zitate.getZitate();
		} catch (Exception e) {
			log.log(Level.SEVERE, "getZitate fehlgeschlagen!");
			e.printStackTrace();
			return null;
		}
	}
	
	//liefert alle vorhandenen Kategorien
	public static HashMap<Integer,String> getAllKategorie(){
		try {
			
//			// ----- mock -----
//			List<String> mockKategorien = new ArrayList<String>();
//			mockKategorien.add("mockKategorie1");
//			mockKategorien.add("mockKategorie2");
//			mockKategorien.add("mockKategorie3");
//			mockKategorien.add("mockKategorie4");
//			mockKategorien.add("mockKategorie5");
//			return mockKategorien; // TODO return anpassen 
//			// ----- mock -----
			
			return Kategorie.getAllKategorie();
		} catch (Exception e) {
			log.log(Level.SEVERE, "getAllKategorie fehlgeschlagen!");
			e.printStackTrace();
			return null;
		}
	}
	
	// validiert eine Antwort //TODO try catch!
	public static boolean validateAnswer(RueckgabePseudoFrAntwort rueckgabePseudoFrAntwort){
		int fragenId = rueckgabePseudoFrAntwort.getFragenId();
		List<Integer> gewaehlteAntId = rueckgabePseudoFrAntwort.getAntwortenList();
		
		
		List <Integer> correctAnswersId = getCorrectAnswers(fragenId);
		List <Integer> tempList = new ArrayList<Integer>();
		
		for (Integer temp2 : correctAnswersId){
            if(gewaehlteAntId.contains(temp2)){
            	tempList.add(temp2);
            }
		}
            
            if (tempList.size() == gewaehlteAntId.size()){
            	return true;
            }
		
		return false;
	}
	
	//holt die korrekten Antworten //TODO try catch!
	public static List<Integer> getCorrectAnswers(int fragenid)
	{
		Session session;
		// Connection erstellen
		session = DataManipulation.getConnection();

		// Transaction erstellen
		Transaction t = session.beginTransaction();

		//List<Integer> rightAnswers = new ArrayList<Integer>();
		
		List<Integer> list = session.createQuery("SELECT fam.fk_antwort FROM FragenAntwortenMapping fam WHERE fam.fk_frage =:fid "
				+ "AND fam.is_correct = 1")
				.setParameter("fid", fragenid).list();
		return list;
	}

}
