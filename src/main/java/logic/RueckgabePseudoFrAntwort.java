package logic;

import java.util.List;

/**
 * Objekte dieser Klasse werden nach Beantwortung einer Frage von der Oberfläche
 * zurück an die Logik gegeben.
 * 
 * @author Michael
 *
 */
public class RueckgabePseudoFrAntwort {
	private int fragenId;
	private List<Integer> antwortenList;
	
	public int getFragenId() {
		return fragenId;
	}
	public void setFragenId(int fragenId) {
		this.fragenId = fragenId;
	}
	public List<Integer> getAntwortenList() {
		return antwortenList;
	}
	public void setAntwortenList(List<Integer> antwortenList) {
		this.antwortenList = antwortenList;
	}

	
}
