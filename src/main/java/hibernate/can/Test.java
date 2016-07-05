package hibernate.can;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		
		Benutzer benutzer = Benutzer.login("horst","horst");
		
		System.out.println("Der Benutzer hat einen Fettigkeitsgrad von " + benutzer.getFettigkeitsgrad());
	}

}
