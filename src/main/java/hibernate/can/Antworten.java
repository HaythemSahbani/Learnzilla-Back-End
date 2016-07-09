package hibernate.can;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "antworten")
public class Antworten {

	@Id @GeneratedValue
	@Column(name = "antwortenid")
	private int antwortenid;
	
	@Column(name = "antwort")
	private String antwort;
	
	@Column(name= "is_correct")
	private boolean is_correct;
	
	public boolean getIs_correct() {
		return is_correct;
	}

	public void setIs_correct(boolean is_correct) {
		this.is_correct = is_correct;
	}

	public Antworten(){}
	
	public int getAntwortenid() {
		return antwortenid;
	}
	public void setAntwortenid(int antwortenid) {
		this.antwortenid = antwortenid;
	}
	public String getAntwort() {
		return antwort;
	}
	public void setAntwort(String antwort) {
		this.antwort = antwort;
	}
	
	
}
