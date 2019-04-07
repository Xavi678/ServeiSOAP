package api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Productes")
public class Producte {
	
	private int id;
	private String nom;
	private int disponibilitat;
	private String descripcio;
	private int preu;
	private String propietari;
	private String data;
	private int venuts;
	private String dataFi;

	
	public Producte(int id, String nom, int disponibilitat, String descripcio, int preu, String propietari, String data,
			int venuts, String dataFi) {
		super();
		this.id = id;
		this.nom = nom;
		this.disponibilitat = disponibilitat;
		this.descripcio = descripcio;
		this.preu = preu;
		this.propietari = propietari;
		this.data = data;
		this.venuts = venuts;
		this.setDataFi(dataFi);
	}



	public Producte() {
		
	}
	
	
	
	public Producte(int id, String nom, int disponibilitat, String descripcio, int preu, String propietari,
			String data) {
		super();
		this.id = id;
		this.nom = nom;
		this.disponibilitat = disponibilitat;
		this.descripcio = descripcio;
		this.preu = preu;
		this.propietari = propietari;
		this.data = data;
	}
	
	



	public Producte(int id, String nom, int disponibilitat, String descripcio, int preu, String propietari, String data,
			int venuts) {
		super();
		this.id = id;
		this.nom = nom;
		this.disponibilitat = disponibilitat;
		this.descripcio = descripcio;
		this.preu = preu;
		this.propietari = propietari;
		this.data = data;
		this.venuts = venuts;
	}
	
	public Producte( String nom, int disponibilitat, String descripcio, int preu, String propietari, String data,
			int venuts,String dataFi) {
		super();
	
		this.nom = nom;
		this.disponibilitat = disponibilitat;
		this.descripcio = descripcio;
		this.preu = preu;
		this.propietari = propietari;
		this.data = data;
		this.venuts = venuts;
		this.dataFi=dataFi;
	}
	


	@XmlElement(name = "data")
	public String getData() {
		return data;
	}



	public void setData(String data) {
		this.data = data;
	}



	public Producte(String nom, int disponibilitat, String descripcio, int preu) {
		super();
		
		this.nom = nom;
		this.disponibilitat = disponibilitat;
		this.descripcio = descripcio;
		this.preu = preu;
	}
	
	
	
	
	public Producte( String nom, int disponibilitat, String descripcio, int preu, String propietari) {
		super();
		
		this.nom = nom;
		this.disponibilitat = disponibilitat;
		this.descripcio = descripcio;
		this.preu = preu;
		this.setPropietari(propietari);
	}
	
	public Producte(int id, String nom, int disponibilitat, String descripcio, int preu, String propietari) {
		super();
		this.id=id;
		this.nom = nom;
		this.disponibilitat = disponibilitat;
		this.descripcio = descripcio;
		this.preu = preu;
		this.setPropietari(propietari);
	}


	
	
	
	



	public Producte(int id, String nom, int disponibilitat, String descripcio, int preu) {
		super();
		this.id = id;
		this.nom = nom;
		this.disponibilitat = disponibilitat;
		this.descripcio = descripcio;
		this.preu = preu;
	}


	






	@XmlElement(name = "nom")
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	@XmlElement(name = "disponibilitat")
	public int getDisponibilitat() {
		return disponibilitat;
	}
	public void setDisponibilitat(int disponibilitat) {
		this.disponibilitat = disponibilitat;
	}
	@XmlElement(name = "descripcio")
	public String getDescripcio() {
		return descripcio;
	}
	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}
	@XmlElement(name = "preu")
	public int getPreu() {
		return preu;
	}
	public void setPreu(int preu) {
		this.preu = preu;
	}

	@XmlElement(name = "propietari")
	public String getPropietari() {
		return propietari;
	}


	public void setPropietari(String propietari) {
		this.propietari = propietari;
	}

	@XmlElement(name = "id")
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	@XmlElement(name = "venuts")
	public int getVenuts() {
		return venuts;
	}



	public void setVenuts(int venuts) {
		this.venuts = venuts;
	}


	@XmlElement(name = "dataFi")
	public String getDataFi() {
		return dataFi;
	}



	public void setDataFi(String dataFi) {
		this.dataFi = dataFi;
	}



	
	
	

}
