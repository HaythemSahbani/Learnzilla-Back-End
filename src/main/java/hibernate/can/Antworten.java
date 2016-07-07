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
