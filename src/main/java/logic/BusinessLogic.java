package logic;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import hibernate.can.Benutzer;
import models.IBenutzer;
import rest.LearnzillaRestController;

public class BusinessLogic {
	
	private static final Logger log = Logger.getLogger(BusinessLogic.class.getName());
	
	//Liefert objekt mit der frage und den antworten
	//public static FrAntwortObject getQuestionFromDB(UserObject userObject, int schwierigkeitsgrad, int kategorie, int fragentyp);
	
	//pruefe ob antwort richtig ist, gebe entsprechend geaendertes userobjekt mit
	public static IBenutzer checkCorrectAnswer(){
		return null;
	}
	
	//pruefen ob user existiert, falls nicht, neu erstellen
	public static Benutzer loginUser(String username, String password) {
		
		Benutzer benutzer;
		try {
			benutzer = Benutzer.login(username, password);
		} catch (Exception e) {
			log.log(Level.SEVERE, "Login fehlgeschlagen!");;
			benutzer = null;
		}
		
		return benutzer;
	}
	
	//logge user aus
	public static boolean logoutUser(int benutzerId){
		return false;
	}
	
	//liefert eine liste mit den usern, welche gerade online sind
	public static List<Benutzer> getUsersOnline(){
		return null;
	}
	
	//liefert ein Benutzer-Objekt zu einer id zurueck
	public static Benutzer getBenutzerInfos(int benutzerId){
		return null;
	}

}
