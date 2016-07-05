package rest;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hibernate.can.Benutzer;
import logic.BusinessLogic;

@RestController
public class LearnzillaRestController {

	private static final Logger log = Logger.getLogger(LearnzillaRestController.class.getName());

	/**
	 * Diese Methode loggt einen Benutzer ein und gibt ihn als Benutzer-Objekt zurueck.
	 * 
	 * @param username
	 * @param password
	 * @return Benutzer or null 
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Benutzer> login(@RequestParam(value = "user") String username,
			@RequestParam(value = "password") String password) {

		log.info("login mit user: [" + username + "] and password [" + password + "]"); 
		
		Benutzer benutzer = BusinessLogic.loginUser(username, password);

		return new ResponseEntity<Benutzer>(benutzer, HttpStatus.OK);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Boolean> logout(@RequestParam(value = "benutzerId") int benutzerId) {

		log.info("logout mit user: [" + benutzerId + "]");

		BusinessLogic.logoutUser(benutzerId);

		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@RequestMapping(value = "/userinfos", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Benutzer> getBenutzerInfos(@RequestParam(value = "benutzerId") int benutzerId) {

		log.info("get userinfos mit userId: [" + benutzerId + "]");

		Benutzer benutzer = BusinessLogic.getBenutzerInfos(benutzerId);

		return new ResponseEntity<Benutzer>(benutzer, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/usersonline", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Benutzer>> getOnlineBenutzer() {

		log.info("getOnlineBenutzer...");

		List<Benutzer> benutzerOnlineList = BusinessLogic.getUsersOnline();

		return new ResponseEntity<List<Benutzer>>(benutzerOnlineList, HttpStatus.OK);
	}

	@RequestMapping(value = "/question", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> getQuestion(@RequestParam(value = "userId") String userId) {

		log.info("get question mit userId: [" + userId + "]");

		//TODO do it!

		return new ResponseEntity<String>("Hello World", HttpStatus.OK);
	}

	@RequestMapping(value = "/answer/{userid}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Boolean> checkAnswer(@RequestParam(value = "frageId") String frageId,
			@RequestParam(value = "antwortId") String antwortId) {

		log.info("check frageId: [" + frageId + "], antwortId: [" + antwortId + "]");

		// TODO merke dass user die frage beantwortet hat, und gebe zurück ob falsch/richtig!
		
		

		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}

}