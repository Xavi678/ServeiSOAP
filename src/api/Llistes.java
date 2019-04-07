package api;

import java.util.Collection;
import java.util.List;

public class Llistes {

	private List<Producte> llista;
	private Collection<Producte> colleccio;
	
	public Llistes(List<Producte> llista, Collection<Producte> colleccio) {
		super();
		this.llista = llista;
		this.colleccio = colleccio;
	}

	public Llistes(List<Producte> p) {
		this.llista=p;
	}
	
	public Llistes( Collection<Producte> colleccio) {
		super();
		
		this.colleccio = colleccio;
	}

	public List<Producte> getLlista() {
		return llista;
	}

	public void setLlista(List<Producte> llista) {
		this.llista = llista;
	}

	public Collection<Producte> getColleccio() {
		return colleccio;
	}

	public void setColleccio(Collection<Producte> colleccio) {
		this.colleccio = colleccio;
	}
	
	
}
