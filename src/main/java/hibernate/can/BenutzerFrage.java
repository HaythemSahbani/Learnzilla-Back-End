package hibernate.can;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "benutzer_frage")
public class BenutzerFrage {

	@Id @GeneratedValue
	@Column(name = "benfragid")
	private int benfrageId;
	
	@Column(name = "fk_benutzer")
	private int fk_benutzer;
	
	@Column(name = "fk_frage")
	private int fk_frage;
	
	@Column(name = "is_done")
	private boolean is_done;
	
	
	public int getBenfrageId() {
		return benfrageId;
	}
	public void setBenfrageId(int benfrageId) {
		this.benfrageId = benfrageId;
	}
	public int getFk_benutzer() {
		return fk_benutzer;
	}
	public void setFk_benutzer(int fk_benutzer) {
		this.fk_benutzer = fk_benutzer;
	}
	public int getFk_frage() {
		return fk_frage;
	}
	public void setFk_frage(int fk_frage) {
		this.fk_frage = fk_frage;
	}
	public boolean getIs_done() {
		return is_done;
	}
	public void setIs_done(boolean is_done) {
		this.is_done = is_done;
	}
	
}
