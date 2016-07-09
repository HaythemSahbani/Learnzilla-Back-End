package rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import logic.RueckgabePseudoFrAntwort;
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

	/**
	 * Diese Methode loggt einen Benutzer aus.
	 * 
	 * @param benutzerId
	 * @return ob es funktioniert hat
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Boolean> logout(@RequestParam(value = "benutzerId") int benutzerId) {
		log.info("logout mit user: [" + benutzerId + "]");

		if (BusinessLogic.logoutUser(benutzerId) == true) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_MODIFIED);
		}
	}

	/**
	 * holt alle Informationen über einen Benutzer
	 * 
	 * @param benutzerId
	 * @return
	 */
	@RequestMapping(value = "/userinfos", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<IBenutzer> getBenutzerInfos(@RequestParam(value = "benutzerId") int benutzerId) {
		log.info("get userinfos mit userId: [" + benutzerId + "]");
		IBenutzer iBenutzer = BusinessLogic.getBenutzerInfos(benutzerId);

		return new ResponseEntity<IBenutzer>(iBenutzer, HttpStatus.OK);
	}

	/**
	 * Holt eine Liste mit allen Benutzern, die online sind.
	 * 
	 * @return Liste mit allen Benutzern, die online sind
	 */
	@RequestMapping(value = "/usersonline", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Benutzer>> getOnlineBenutzer() {
		log.info("getOnlineBenutzer...");
		List<Benutzer> benutzerOnlineList = BusinessLogic.getUsersOnline();

		return new ResponseEntity<List<Benutzer>>(benutzerOnlineList, HttpStatus.OK);
	}

	/**
	 * holt eine Frage mit den dazugehoerigen moeglichen Antworten
	 * 
	 * @param benutzerId
	 * @param kategorie
	 * @return FrAntwort Object
	 */
	@RequestMapping(value = "/question", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<IFrAntwort> getQuestion(@RequestParam(value = "benutzerId") int benutzerId,
			@RequestParam(value = "kategorie") int kategorie) {
		log.info("get question mit userId: [" + benutzerId + "]");
		IFrAntwort iFrAntwort = BusinessLogic.getQuestionFromDB(benutzerId, kategorie);

		return new ResponseEntity<IFrAntwort>(iFrAntwort, HttpStatus.OK);
	}

	/**
	 * holt ein Zitat
	 * 
	 * @return ein Zitat
	 */
	@RequestMapping(value = "/zitat", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> getZitat() {
		log.info("get zitat");
		String zitat = BusinessLogic.getZitat();

		return new ResponseEntity<String>(zitat, HttpStatus.OK);
	}

//	/**
//	 * Diese Methode bekommt ein Objekt mit Informationen ueber eine vom
//	 * Benutzer beantwortete Frage
//	 * 
//	 * @return nichts
//	 */
//	@RequestMapping(value = "/antwort", method = RequestMethod.POST)
//	@ResponseBody
//	public void verarbeiteAntwort(@RequestBody RueckgabePseudoFrAntwort rueckgabePseudoFrAntwort) {
//
//		log.info("verarbeiteAntwort mit fragenId [" + rueckgabePseudoFrAntwort.getFragenId() + "] " + rueckgabePseudoFrAntwort.toString());
//		BusinessLogic.validateAnswer(rueckgabePseudoFrAntwort);
//	}
	
	/**
	 * Diese Methode bekommt ein Objekt mit Informationen ueber eine vom
	 * Benutzer beantwortete Frage
	 * @return 
	 * 
	 * @return nichts
	 */
	@RequestMapping(value = "/antwort", method = RequestMethod.GET)
	public ResponseEntity<AnswerReturn> verarbeiteAntwort(@RequestParam(value = "questionId") int questionId,
			@RequestParam(value = "answerId1") int answerId1, @RequestParam(value = "answerId2") int answerId2,
			@RequestParam(value = "answerId3") int answerId3, @RequestParam(value = "answerId4") int answerId4) {

		log.info("verarbeiteAntwort mit questionId [" + questionId + "] und [" + answerId1 + "] und [" +  answerId2 + "] und [" + answerId3 + "] und [" + answerId4 + "]");

		List<Integer> antwortenList = new ArrayList<Integer>();
		if (answerId1 != -1) antwortenList.add(answerId1);
		if (answerId2 != -1) antwortenList.add(answerId2);
		if (answerId3 != -1) antwortenList.add(answerId3);
		if (answerId4 != -1) antwortenList.add(answerId4);

		RueckgabePseudoFrAntwort rueckgabePseudoFrAntwort = new RueckgabePseudoFrAntwort();
		rueckgabePseudoFrAntwort.setFragenId(questionId);
		rueckgabePseudoFrAntwort.setAntwortenList(antwortenList);
		
		Boolean answerValidated = BusinessLogic.validateAnswer(rueckgabePseudoFrAntwort);
		log.info("verarbeiteAntwort validated: [" + answerValidated + "] ");
		
		AnswerReturn answerReturn = new AnswerReturn();
		answerReturn.setAnswerTrue(answerValidated);
		
		return new ResponseEntity<AnswerReturn>(answerReturn, HttpStatus.OK);
	}

	/**
	 * Gibt alle vorhandenen Kategorien zurueck
	 * 
	 * @return List emit vorhandenen Kategorien
	 */
	@RequestMapping(value = "/kategorien", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<HashMap<Integer, String>> getCategories() {
		log.info("get kategories");
		HashMap<Integer, String> kategorien = BusinessLogic.getAllKategorie();

		return new ResponseEntity<HashMap<Integer, String>>(kategorien, HttpStatus.OK);
	}

}