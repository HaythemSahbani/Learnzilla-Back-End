package rest;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import logic.BusinessLogic;
import models.IBenutzer;
import models.IFrAntwort;

@RestController
public class LearnzillaRestController {

	private static final Logger log = Logger.getLogger(LearnzillaRestController.class.getName());

	/**
	 * Diese Methode loggt einen Benutzer ein und gibt ihn als Benutzer-Objekt
	 * zurueck.
	 * 
	 * @param username
	 * @param password
	 * @return Benutzer or null
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<IBenutzer> login(@RequestParam(value = "user") String username,
			@RequestParam(value = "password") String password) {

		log.info("login mit user: [" + username + "] and password [" + password + "]");

		IBenutzer benutzer = BusinessLogic.loginUser(username, password);

		return new ResponseEntity<IBenutzer>(benutzer, HttpStatus.OK);
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
	public ResponseEntity<IBenutzer> getBenutzerInfos(@RequestParam(value = "benutzerId") int benutzerId) {

		log.info("get userinfos mit userId: [" + benutzerId + "]");

		IBenutzer iBenutzer = BusinessLogic.getBenutzerInfos(benutzerId);

		return new ResponseEntity<IBenutzer>(iBenutzer, HttpStatus.OK);
	}

	@RequestMapping(value = "/usersonline", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<IBenutzer>> getOnlineBenutzer() {

		log.info("getOnlineBenutzer...");

		List<IBenutzer> benutzerOnlineList = BusinessLogic.getUsersOnline();

		return new ResponseEntity<List<IBenutzer>>(benutzerOnlineList, HttpStatus.OK);
	}

	@RequestMapping(value = "/question", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<IFrAntwort> getQuestion(@RequestParam(value = "benutzerId") int benutzerId,
			@RequestParam(value = "kategorie") int kategorie) {

		log.info("get question mit userId: [" + benutzerId + "]");

		IFrAntwort iFrAntwort = BusinessLogic.getQuestionFromDB(benutzerId, kategorie);

		return new ResponseEntity<IFrAntwort>(iFrAntwort, HttpStatus.OK);
	}

	@RequestMapping(value = "/answer/{userid}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Boolean> checkAnswer(@RequestParam(value = "frageId") String frageId,
			@RequestParam(value = "antwortId") String antwortId) {

		log.info("check frageId: [" + frageId + "], antwortId: [" + antwortId + "]");

		// TODO merke dass user die frage beantwortet hat, und gebe zurück ob
		// falsch/richtig!

		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}

	@RequestMapping(value = "/zitat", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> getZitat() {

		log.info("get zitat");

		String zitat = BusinessLogic.getZitat();

		return new ResponseEntity<String>(zitat, HttpStatus.OK);
	}

}