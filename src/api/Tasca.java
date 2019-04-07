package api;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name = "tasca")
public class Tasca implements Serializable {
	String nom;
	
	public Tasca(){
		
	}

	public Tasca(String nom) {
		super();
		this.nom = nom;
	}
	@XmlElement(name = "nom")
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	

}
