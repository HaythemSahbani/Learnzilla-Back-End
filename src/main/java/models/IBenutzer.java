package models;

public interface IBenutzer {

	int getBenutzerid();

	void setBenutzerid(int Benutzerid);

	String getBenutzername();

	void setBenutzername(String Benutzername);

	String getPasswort();

	void setPasswort(String Password);

	//boolean getIs_online();

	void setIs_online(boolean is_online);

	int getFettigkeitsgrad();

	void setFettigkeitsgrad(int fettigkeitsgrad);

	int getHighscore();

	void setHighscore(int highscore);
	


}