package api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;


public class GestorBd {
	
	private String hostname;
	private String database;
	private String port;
	private String userLogin;
	private String userPasswd;
	private String temps;
	private Connection conn;
	
	public GestorBd(){
		super();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException ex) {
			System.out.println("Error: unable to load driver class!");
		}
		this.temps="?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false";
	}
	
	public GestorBd(String hostname, String database, String port, String user, String passwd) {
		this(hostname,database,user,passwd);
		this.port=port;
	}


	public GestorBd(String hostname, String database, String user, String passwd) {
		this();
		this.hostname = hostname;
		this.database = database;
		this.userLogin = user;
		this.userPasswd = passwd;
		//this.temps="?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false";
		
	}
	

	public String getHostname() {
		return hostname;
	}


	public void setHostname(String hostname) {
		this.hostname = hostname;
	}


	public String getDatabase() {
		return database;
	}


	public void setDatabase(String database) {
		this.database = database;
	}


	public String getPort() {
		return port;
	}


	public void setPort(String port) {
		this.port = port;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getUserPasswd() {
		return userPasswd;
	}

	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}
	
	public void crearTaulasSiNoCreada() {
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.database+this.temps,this.userLogin,this.userPasswd)){
			
			String sql = "CREATE TABLE IF NOT EXISTS Usuaris (\n" + 
					"    nom VARCHAR(100),\n" + 
					"password VARCHAR(100),\n" +
					"    login VARCHAR(20), "
					+ "PRIMARY KEY(login) )\n";
			
			try(PreparedStatement pstmt = conn.prepareStatement(sql)){
				
				pstmt.executeUpdate();
				
			}catch(SQLException pstmte){
				pstmte.printStackTrace();
			}
		
		}catch(SQLException conne){
			conne.printStackTrace();
		}
	}
	
	public void eliminarTaula() {
		
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.database+this.temps,this.userLogin,this.userPasswd)){
			
			String sql = "DROP TABLE IF EXISTS Usuaris";
			
			try(PreparedStatement pstmt = conn.prepareStatement(sql)){
				
				pstmt.executeUpdate();
				
			}catch(SQLException pstmte){
				pstmte.printStackTrace();
			}
		
		}catch(SQLException conne){
			conne.printStackTrace();
		}
	}
	
	public Collection<Usuari> obtenirUsuaris(){
		
		
		ArrayList<Usuari> usuaris = new ArrayList<Usuari>();
		
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.database+this.temps,this.userLogin,this.userPasswd)){
			
			String sql = "SELECT * FROM "+database+".Usuaris;";
			try(PreparedStatement usersFound = conn.prepareStatement(sql)){
				
				
				try(ResultSet rs = usersFound.executeQuery()){
					
					while(rs.next()){
						Usuari usuari = new Usuari(rs.getString("login"),rs.getString("nom"));
						usuaris.add(usuari);
					}
					
				}catch(SQLException rse){
					rse.printStackTrace();
				}
			}catch(SQLException stmte){
				stmte.printStackTrace();
			}

		} catch (SQLException conne) {
			conne.printStackTrace();
		}
		return usuaris;
		
	}
	
	
	public void crearUsuaris() {
		int retorn = 0;
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.database+this.temps,this.userLogin,this.userPasswd)){

			String sql = "INSERT INTO "+database+".Usuaris VALUES(?,?,?);";
			try(PreparedStatement insertedUser = conn.prepareStatement(sql)){
				
				for(int i = 0;i<10;i++) {
					insertedUser.setString(1,"Nom_"+ i);
					insertedUser.setString(2,"Passwd_"+i);
					insertedUser.setString(3, "Login_"+ i);
					insertedUser.addBatch();
				}
				
				insertedUser.executeBatch();
			
			}catch(SQLException stmte){
				stmte.printStackTrace();
			}

		} catch (SQLException conne) {
			conne.printStackTrace();
		}
		
	}
	
	public Usuari obtenirUser(Usuari user) throws SQLException {
		
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.database+this.temps,this.userLogin,this.userPasswd);
		
		String sql="select * from usuaris where login=?";
		
		PreparedStatement selectUser=conn.prepareStatement(sql);
		
		selectUser.setString(1, user.login);
		
		ResultSet rs = selectUser.executeQuery();
		
		Usuari userFound = null;
		
		while(rs.next()) {
			userFound = new Usuari(rs.getString("login"),rs.getString("password"));
			

				
		/*
				String sqlInserir="insert into Usuaris Values(?,?)";
				PreparedStatement insertUser=conn.prepareStatement(sqlInserir);
				insertUser.setString(1, user.login);
				insertUser.setString(2, user.password);
				insertUser.executeBatch();
				
				return null;
				
				*/
			
		
		}
		
		return userFound;
		
		
	}

	public void crearUsuari(Usuari usuari) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.database+this.temps,this.userLogin,this.userPasswd);
		
		String sqlInserir="insert into usuaris (login,password )  Values(?,?)";
		PreparedStatement insertUser=conn.prepareStatement(sqlInserir);
		insertUser.setString(1, usuari.login);
		insertUser.setString(2, usuari.password);
		insertUser.executeUpdate();
	}

	public ArrayList<Producte> obtenirProductesfi() throws SQLException {
		
		ArrayList<Producte> productes= new ArrayList<Producte>();
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.database+this.temps,this.userLogin,this.userPasswd);
		
		String sql ="Select * from Productes   where (  NOW() <= STR_TO_DATE(dataFi,'%d %m %Y'))  ";
		
		PreparedStatement selectP= conn.prepareStatement(sql);
	
		ResultSet rs=selectP.executeQuery();
		while(rs.next()) {
			
			
			productes.add(new Producte(rs.getInt("id"),rs.getString("nom"),rs.getInt("disponibilitat"),rs.getString("descripcio"),rs.getInt("preu"),rs.getString("propietari"),rs.getString("data"),rs.getInt("venuts"),rs.getString("dataFi")));
			
		}
		
		return productes;
	}
	
	
	public ArrayList<Producte> obtenirProductes() throws SQLException {
		
		ArrayList<Producte> productes= new ArrayList<Producte>();
		
		
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.database+this.temps,this.userLogin,this.userPasswd);
		
		String sql ="Select * from Productes ";
		
		PreparedStatement selectP= conn.prepareStatement(sql);
		
		ResultSet rs=selectP.executeQuery();
		while(rs.next()) {
			
			
			productes.add(new Producte(rs.getInt("id"),rs.getString("nom"),rs.getInt("disponibilitat"),rs.getString("descripcio"),rs.getInt("preu"),rs.getString("propietari"),rs.getString("data"),rs.getInt("venuts"),rs.getString("dataFi")));
			
		}
		
		return productes;
	}

	public boolean inserirProducte(Producte producte) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.database+this.temps,this.userLogin,this.userPasswd);
		String sqlInserir="insert into productes(nom,disponibilitat,descripcio,preu,propietari,data,venuts,dataFi) Values(?,?,?,?,?,?,?,?)";
		PreparedStatement insert=conn.prepareStatement(sqlInserir);
		insert.setString(1, producte.getNom());
		insert.setInt(2, producte.getDisponibilitat());
		insert.setString(3, producte.getDescripcio());
		insert.setInt(4, producte.getPreu());
		insert.setString(5, producte.getPropietari());
		insert.setString(6, producte.getData());
		insert.setInt(7, 0);
		insert.setString(8, producte.getDataFi());
		int count=insert.executeUpdate();
		
		if(count>0) {
			return true;
		}else {
			return false;
		}
	}

	public void crearCarro(Usuari usuari) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.database+this.temps,this.userLogin,this.userPasswd);
		String sql="CREATE TABLE carro_"+usuari.getLogin()+"(\n" + 
				"id int not null primary key auto_increment,\n" + 
				"nom varchar(100) not null,\n" + 
				"disponibilitat int not null,\n" + 
				"descripcio varchar(100) not null,\n" + 
				"preu int not null,\n" + 
				"propietari varchar(100) not null,\n" + 
				"\n" + 
				"foreign key(propietari) references usuaris(login)\n" + 
				" on update cascade\n" + 
				" on delete cascade)ENGINE=INNODB;";
		
		PreparedStatement prst=conn.prepareStatement(sql);
		prst.executeUpdate();
	}

	public Producte obtenirProductealCarro(Integer id, String login) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.database+this.temps,this.userLogin,this.userPasswd);
		String sql="Select * from Carro where id=? and propietari=?";
		
		PreparedStatement trobar=conn.prepareStatement(sql);
		trobar.setInt(1, id);
		trobar.setString(2, login);
		ResultSet rs=trobar.executeQuery();
		Producte product=null;
		while(rs.next()) {
			product=new Producte(rs.getInt("id"),rs.getString("nom"),rs.getInt("disponibilitat"),rs.getString("descripcio"),rs.getInt("preu"),rs.getString("propietari"));
		}
		
		return product;
		
	}

	public void insertCarro(Producte producte, String login) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.database+this.temps,this.userLogin,this.userPasswd);
		String sqlInserir="insert into carro(id,nom,disponibilitat,descripcio,preu,propietari) Values(?,?,?,?,?,?)";
		PreparedStatement insert=conn.prepareStatement(sqlInserir);
		insert.setInt(1, producte.getId());
		insert.setString(2, producte.getNom());
		insert.setInt(3, 1);
		insert.setString(4, producte.getDescripcio());
		insert.setInt(5, producte.getPreu());
		insert.setString(6, login);
		insert.executeUpdate();
		
	}

	public void actualitzar(Producte producte, String login) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.database+this.temps,this.userLogin,this.userPasswd);
		String sqlInserir="Update carro set disponibilitat=disponibilitat+1 where id=? and propietari=?";
		PreparedStatement insert=conn.prepareStatement(sqlInserir);
		insert.setInt(1, producte.getId());
		insert.setString(2, login);
		
		insert.executeUpdate();
		
	}

	public ArrayList<Producte> obtenirCarroperUsuari(Usuari user) throws SQLException {
		ArrayList<Producte> carro=new ArrayList<Producte>();
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.database+this.temps,this.userLogin,this.userPasswd);
		String sql="Select * from Carro where propietari=?";
		
		PreparedStatement select=conn.prepareStatement(sql);
		select.setString(1, user.getLogin());
		
		ResultSet rs=select.executeQuery();
		
		while(rs.next()) {
			carro.add(new Producte(rs.getInt("id"),rs.getString("nom"),rs.getInt("disponibilitat"),rs.getString("descripcio"),rs.getInt("preu")));
		}
		
		return carro;
		
		
	}
	
	public ArrayList<Producte> obtenirCarroperUsuariId(Usuari user) throws SQLException {
		ArrayList<Producte> carro=new ArrayList<Producte>();
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.database+this.temps,this.userLogin,this.userPasswd);
		String sql="Select * from Carro where propietari=?";
		
		PreparedStatement select=conn.prepareStatement(sql);
		select.setString(1, user.getLogin());
		
		ResultSet rs=select.executeQuery();
		
		while(rs.next()) {
			carro.add(new Producte(rs.getInt("id"),rs.getString("nom"),rs.getInt("disponibilitat"),rs.getString("descripcio"),rs.getInt("preu"),rs.getString("propietari")));
		}
		
		return carro;
		
		
	}

	public void Comprar(Usuari user) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.database+this.temps,this.userLogin,this.userPasswd);
		String sqltrigger="SET @TRIGGER_ENABLED=1;";
		PreparedStatement pr=conn.prepareStatement(sqltrigger);
		pr.executeUpdate();
		String sql="delete  from Carro where propietari=?";
		
		PreparedStatement delete=conn.prepareStatement(sql);
		delete.setString(1, user.getLogin());
		delete.executeUpdate();
		
	
	}

	public void eliminar(Integer id,String login) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.database+this.temps,this.userLogin,this.userPasswd);
		String sqltrigger="SET @TRIGGER_ENABLED=0;";
		PreparedStatement pr=conn.prepareStatement(sqltrigger);
		pr.executeUpdate();
		String sql="delete from Carro where id=? and propietari=?";
		
		PreparedStatement delete=conn.prepareStatement(sql);
		delete.setInt(1, id);
		delete.setString(2, login);
		delete.executeUpdate();
		
	}

	public Collection<Producte> obtenirProductesFiltrats(int maxim, int minim) throws SQLException {
		Collection<Producte> productes= new ArrayList<Producte>();
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.database+this.temps,this.userLogin,this.userPasswd);
		String sql="select * from productes where preu between ? and ? order by preu ASC";
		
		PreparedStatement select=conn.prepareStatement(sql);
		
		select.setInt(1,minim);
		select.setInt(2,maxim);
		ResultSet rs=select.executeQuery();
		
		while(rs.next()) {
			productes.add(new Producte(rs.getInt("id"),rs.getString("nom"),rs.getInt("disponibilitat"),rs.getString("descripcio"),rs.getInt("preu"),rs.getString("propietari"),rs.getString("data")));
		}
		return productes;
	}

	public Collection<Producte> obtenirProductesPerData(String minDate, String maxDate) throws SQLException {
		
		Collection<Producte> productes= new ArrayList<Producte>();
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.database+this.temps,this.userLogin,this.userPasswd);
		String sql="select * from productes where data between STR_TO_DATE(?,'%d %m %Y') and STR_TO_DATE(?,'%d %m %Y') ";
		
		PreparedStatement select=conn.prepareStatement(sql);
		
		select.setString(1,minDate);
		select.setString(2,maxDate);
		ResultSet rs=select.executeQuery();
		
		while(rs.next()) {
			productes.add(new Producte(rs.getInt("id"),rs.getString("nom"),rs.getInt("disponibilitat"),rs.getString("descripcio"),rs.getInt("preu"),rs.getString("propietari"),rs.getString("data")));
		}
		return productes;
	}

	public void canviar(String password, String nom, String cognom, String correu,String login) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.database+this.temps,this.userLogin,this.userPasswd);
		String sql="update usuaris set password=?,nom=?,cognom=?,correu=? where login=?";
		PreparedStatement update=conn.prepareStatement(sql);
		update.setString(1, password);
		update.setString(2, nom);
		update.setString(3, cognom);
		update.setString(4, correu);
		update.setString(5, login);
		
		update.executeUpdate();
	}

	public Producte obtenirProducteperId(Integer id) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.database+this.temps,this.userLogin,this.userPasswd);
		String sql="Select * from productes where id=?";
		
		PreparedStatement trobar=conn.prepareStatement(sql);
		trobar.setInt(1, id);
		
		ResultSet rs=trobar.executeQuery();
		Producte product=null;
		while(rs.next()) {
			product=new Producte(rs.getInt("id"),rs.getString("nom"),rs.getInt("disponibilitat"),rs.getString("descripcio"),rs.getInt("preu"),rs.getString("propietari"));
		}
		
		return product;
	}

	public Collection<Producte> filtratgeNom(String nomF) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.database+this.temps,this.userLogin,this.userPasswd);
		ArrayList<Producte> productes=new ArrayList<Producte>();
		String sql="Select * from productes where nom=?";
		
		PreparedStatement prs=conn.prepareStatement(sql);
		prs.setString(1, nomF);
		ResultSet rs=prs.executeQuery();
		while(rs.next()) {
			productes.add(new Producte(rs.getInt("id"),rs.getString("nom"),rs.getInt("disponibilitat"),rs.getString("descripcio"),rs.getInt("preu"),rs.getString("propietari"),rs.getString("data")));
		}
		return productes;
	}

	public String autenticar(String nom, String passwd) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.database+this.temps,this.userLogin,this.userPasswd);
		//ArrayList<Producte> productes=new ArrayList<Producte>();
		String sql="Select * from usuaris where login=?";
		
		PreparedStatement prs=conn.prepareStatement(sql);
		prs.setString(1, nom);
		ResultSet rs=prs.executeQuery();
		
		Usuari user=null;
		while(rs.next()) {
			//productes.add(new Producte(rs.getInt("id"),rs.getString("nom"),rs.getInt("disponibilitat"),rs.getString("descripcio"),rs.getInt("preu"),rs.getString("propietari"),rs.getString("data")));
			user= new Usuari(rs.getString("login"),rs.getString("password"));
			
			
	
		}
		
		if(user.validat(new Usuari(nom,passwd))) {
			String token=null;
			token= UUID.randomUUID().toString();
			
			String consulta="Update usuaris set token=? where login=? ";
			
			PreparedStatement ps=conn.prepareStatement(consulta);
			ps.setString(1, token);
			ps.setString(2, nom);
			
			ps.executeUpdate();
			
			return token;
			
		}else {
			return null;
		}

		
	}

	public Producte obtenirProducte( String product) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.database+this.temps,this.userLogin,this.userPasswd);
		//ArrayList<Producte> productes=new ArrayList<Producte>();
		String sql="Select * from productes where id=? ";
		
		PreparedStatement prs=conn.prepareStatement(sql);
		prs.setString(1, product);
		ResultSet rs=prs.executeQuery();
		Producte p=null;
		while(rs.next()) {
		p=new Producte(rs.getString( "nom"), rs.getInt( "disponibilitat"), rs.getString( "descripcio"), rs.getInt("preu"), rs.getString("propietari"), rs.getString( "data"), rs.getInt("venuts"), rs.getString("dataFi"));
		}
		
		return p;
	}

	public Boolean getToken(String user) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.database+this.temps,this.userLogin,this.userPasswd);
		//ArrayList<Producte> productes=new ArrayList<Producte>();
		String sql="Select * from usuaris where token=? ";
		
		PreparedStatement prs=conn.prepareStatement(sql);
		prs.setString(1, user);
		ResultSet rs=prs.executeQuery();
		Usuari p=null;
		while(rs.next()) {
		//p=new Producte(rs.getString( "nom"), rs.getInt( "disponibilitat"), rs.getString( "descripcio"), rs.getInt("preu"), rs.getString("propietari"), rs.getString( "data"), rs.getInt("venuts"), rs.getString("dataFi"));
		p= new Usuari(rs.getString("login"),rs.getString("password"));
		
		}
		
		if(p!=null) {
			return true;
		}else {
			return false;
		}
		
		
	}
	
	

}