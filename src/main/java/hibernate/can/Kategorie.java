package hibernate.can;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "kategorie")
public class Kategorie {

	@Id @GeneratedValue
	@Column(name = "kategorieid")
	private int kategorieId;
	
	@Column(name = "beschreibung")
	private String beschreibung;
	
	public int getKategorieId() {
		return kategorieId;
	}
	public void setKategorieId(int kategorieId) {
		this.kategorieId = kategorieId;
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	
}
