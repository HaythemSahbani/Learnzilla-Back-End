package models;

import java.util.List;

import hibernate.can.Antworten;
import hibernate.can.Fragen;

public interface IFrAntwort {

	Fragen getFrage();

	void setFrage(Fragen frage);

	List<Antworten> getAntwortenList();

	void setAntwortenList(List<Antworten> antwortenList);

}