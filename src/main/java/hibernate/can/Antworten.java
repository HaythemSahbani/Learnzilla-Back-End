package hibernate.can;
import javax.persistence.*;

@Entity
@Table(name = "antworten")
public class Antworten {

	@Id @GeneratedValue
	@Column(name = "antwortenid")
	private int antwortenId;
	
	@Column(name = "antwort")
	private String antwort;
	
	public Antworten(){}
	
	public int getAntwortenid() {
		return antwortenId;
	}
	public void setAntwortenid(int antwortenid) {
		this.antwortenId = antwortenid;
	}
	public String getAntwort() {
		return antwort;
	}
	public void setAntwort(String antwort) {
		this.antwort = antwort;
	}
	
	
}
