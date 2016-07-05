package hibernate.can;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zitate")
public class Zitate {
	
	@Id @GeneratedValue
	@Column(name = "zitatid")
	private int zitateId;
	
	@Column(name = "zitat")
	private String zitate;
	
	
	public int getZitateId() {
		return zitateId;
	}
	public void setZitateId(int zitateId) {
		this.zitateId = zitateId;
	}
	public String getZitate() {
		return zitate;
	}
	public void setZitate(String zitate) {
		this.zitate = zitate;
	}
	
}
