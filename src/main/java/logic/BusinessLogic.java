package logic;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import hibernate.can.Benutzer;
import hibernate.can.FrAntwort;
import models.IBenutzer;
import models.IFrAntwort;

public class BusinessLogic {
	
	private static final Logger log = Logger.getLogger(BusinessLogic.class.getName());
	
	//Liefert objekt mit der frage und den antworten
	public static IFrAntwort getQuestionFromDB(int benutzerId, int kategorie) {
		
		
		IFrAntwort IFrAntwort = FrAntwort.getFrAntwort(benutzerId, kategorie);
		
		return IFrAntwort;
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
	
	//logge user aus
	public static boolean logoutUser(int benutzerId){
		Benutzer benutzer = Benutzer.getBenutzerObjekt(benutzerId);
		try {
			benutzer.logout();
			return true;
		} catch (Exception e) {
			log.log(Level.SEVERE, "Logout fehlgeschlagen!");
		}
		return false;
	}
	
	//liefert eine liste mit den usern, welche gerade online sind
	public static List<IBenutzer> getUsersOnline(){
		return null;
	}
	
	//liefert ein Benutzer-Objekt zu einer id zurueck
	public static IBenutzer getBenutzerInfos(int benutzerId){
		Benutzer benutzer = Benutzer.getBenutzerObjekt(benutzerId);
		return benutzer;
	}
	
	public static String getZitat(){
		//TODO
		return "ich bin ein Zitat";
	}

}
