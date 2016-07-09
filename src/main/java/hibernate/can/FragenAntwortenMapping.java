package hibernate.can;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.Transaction;

@Entity
@Table(name = "fragenantwortenmapping")
public class FragenAntwortenMapping {

	@Id @GeneratedValue
	@Column(name = "fk_frage")
	private int fk_frage;
	
	@Column(name = "fk_antwort")
	private int fk_antwort;
	
	@Column(name = "is_correct")
	private boolean is_correct;

	public boolean getIs_correct() {
		return is_correct;
	}
	public void setIs_correct(boolean is_correct) {
		this.is_correct = is_correct;
	}
	public int getFk_frage() {
		return fk_frage;
	}
	public void setFk_frage(int fk_frage) {
		this.fk_frage = fk_frage;
	}
	public int getFk_antwort() {
		return fk_antwort;
	}
	public void setFk_antwort(int fk_antwort) {
		this.fk_antwort = fk_antwort;
	}
	
	//GetCorrectAnswersfromFrageAntwortenMapping
}

