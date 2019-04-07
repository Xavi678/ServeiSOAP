package api;

public class Usuari {

	protected String login;
	protected String password;
	protected String password2;
	protected String nom;
	protected String cognom;
	protected String correu;
	protected String token;
	
	
	
	
	public Usuari(String login,String password,String token) {
		this.login=login;
		this.password=password;
		this.token=token;
		
	}
	
	
	public Usuari(String login, String password, String nom, String cognom, String correu) {
		super();
		this.login = login;
		this.password = password;
		this.nom = nom;
		this.cognom = cognom;
		this.correu = correu;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCognom() {
		return cognom;
	}

	public void setCognom(String cognom) {
		this.cognom = cognom;
	}

	public String getCorreu() {
		return correu;
	}

	public void setCorreu(String correu) {
		this.correu = correu;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public Usuari() {}
	
	
	
	public Usuari(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean validat(Usuari usuari) {
		return (usuari!=null && login.equals(usuari.login) && password.equals(usuari.password));
	}
	
	
}
