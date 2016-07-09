package hibernate.can;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Benutzer benutzer = Benutzer.login("kathi","kathi");
		
		System.out.println("Der Benutzer hat einen Fettigkeitsgrad von " + benutzer.getFettigkeitsgrad());

		System.out.println("Der Benutzer hat einen Highscore von " + benutzer.getHighscore());
		//benutzer.setFettigkeitsgrad(10);
		System.out.println("Der Benutzer hat einen Fettigkeitsgrad von " + benutzer.getFettigkeitsgrad());
		
		FrAntwort fr = FrAntwort.getFrAntwort(3, 0);

		benutzer.logout();
		Zitate z = new Zitate();
		System.out.println("zitat:" + z.getZitate());
		System.out.println("fr:" + fr.getFrage().getFrage());
		System.out.println("antworten:" + fr.getAntwortenList().get(0).getAntwort());
		System.out.println("antworten:" + fr.getAntwortenList().get(1).getAntwort());
		
	}
}
