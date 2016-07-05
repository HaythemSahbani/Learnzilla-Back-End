package hibernate.can;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fragen")
public class Fragen {

	@Id @GeneratedValue
	@Column(name = "fragenid")
	private int fragenId;
	
	@Column(name = "frage")
	private String frage;
	
	@Column(name = "fk_kategorie")
	private int fk_kategorie;
	
	@Column(name = "schwierigkeit")
	private int schwierigkeit;
	
	
	public int getFragenid() {
		return fragenId;
	}
	public void setFragenid(int fragenid) {
		this.fragenId = fragenid;
	}
	public String getFrage() {
		return frage;
	}
	public void setFrage(String frage) {
		this.frage = frage;
	}
	public int getFk_kategorie() {
		return fk_kategorie;
	}
	public void setFk_kategorie(int fk_kategorie) {
		this.fk_kategorie = fk_kategorie;
	}
	public int getSchwierigkeit() {
		return schwierigkeit;
	}
	public void setSchwierigkeit(int schwierigkeit) {
		this.schwierigkeit = schwierigkeit;
	}
	
}