package hibernate.can;

import models.IBenutzer;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		
		IBenutzer benutzer = Benutzer.login("horst","horst");
		
		System.out.println("Der Benutzer hat einen Fettigkeitsgrad von " + benutzer.getFettigkeitsgrad());

		//System.out.println("Der Benutzer hat einen Highscore von " + benutzer.getHighscore());
		benutzer.setFettigkeitsgrad(10);
		System.out.println("Der Benutzer hat einen Fettigkeitsgrad von " + benutzer.getFettigkeitsgrad());

		
	}

}
