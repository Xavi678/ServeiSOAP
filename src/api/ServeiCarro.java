package api;

import java.util.Collection;
import java.util.List;

public class ServeiCarro {

	GestorBd db = new GestorBd(Constant.dbserver, Constant.database, Constant.user, Constant.password);

	/*
	 * @Path("/JSON/{f}")
	 * 
	 * @GET
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public Response
	 * salutacio(@PathParam("f") String nom) throws JSONException {
	 * 
	 * JSONObject jsonObject = new JSONObject();
	 * 
	 * jsonObject.put("salutacio", "Hola señor "+nom);
	 * 
	 * 
	 * String result = "" + jsonObject; return
	 * Response.status(200).entity(result).build();
	 * 
	 * }
	 */

	/*
	 * @Path("/getTasques")
	 * 
	 * @GET
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public List<Tasca> obtenirTasques()
	 * throws JSONException {
	 * 
	 * 
	 * 
	 * JSONObject jsonObject = new JSONObject();
	 * 
	 * jsonObject.put("salutacio", "Hola señor ");
	 * 
	 * 
	 * String result = "" + jsonObject; return tasques
	 * 
	 * }
	 */

	public String autenticar(String nom, String passwd) throws Exception {

		// System.out.println();

		String token = db.autenticar(nom, passwd);

		return token;

	}

	public Producte obtenirProducte(String user, String product) throws Exception {

		// String token=db.autenticar(nom,passwd);

		if (db.getToken(user) == false) {
			return null;
		}

		Producte p = db.obtenirProducte(product);

		return p;

	}

	public Producte[] obtenirProductes(String user) throws Exception {

		// String token=db.autenticar(nom,passwd);

		if (db.getToken(user) == false) {
			return null;
		}

		List<Producte> p = db.obtenirProductes();
		
		return p.toArray(new Producte[p.size()]);

	}

	public Boolean afegirProducte( String user, Producte producte) throws Exception {

		// String token=db.autenticar(nom,passwd);

		if (db.getToken(user) == false) {
			return false;
		}

		// Producte[] ptmp=producte.split(",");

		System.out.println(producte);

		List<Producte> p = db.obtenirProductes();
		
		Boolean tornat=db.inserirProducte(producte);

	
		return tornat;

	}


	public Producte[] otenirProductesperData( String user,  String inici, String fi) throws Exception {
		

			// String token=db.autenticar(nom,passwd);

			// LocalDateTime datetime = LocalDateTime.parse(inici,
			// DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));

			/*
			 * DateFormat format= new SimpleDateFormat("yyyy-MM-dd"); Date
			 * datai=format.parse(inici); Date dataf=format.parse(fi);
			 */

			inici = inici.replace("-", " ");
			fi = fi.replace("-", " ");
			if (db.getToken(user) == false) {
				return null;
			}

			// Producte[] ptmp=producte.split(",");

			Collection<Producte> p = db.obtenirProductesPerData(inici, fi);

			
			//return (Producte[]) p.toArray();
			
			return p.toArray(new Producte[p.size()]);
		
	}

	/*
	 * @Path("/data/{fi}")
	 * 
	 * @GET
	 * 
	 * @Produces( MediaType.APPLICATION_JSON ) public Response
	 * otenirProductesperData(@PathParam("fi") Date fi ) { try {
	 * 
	 * 
	 * //String token=db.autenticar(nom,passwd);
	 * 
	 * 
	 * // if(db.getToken(user)==false) { // return
	 * Response.status(Response.Status.UNAUTHORIZED).build(); // }
	 * 
	 * //Producte[] ptmp=producte.split(",");
	 * 
	 * 
	 * 
	 * List<Producte> p=db.obtenirProductes();
	 * 
	 * GenericEntity<List<Producte>> genericEntity = new
	 * GenericEntity<List<Producte>>(p){}; return Response.ok(genericEntity,
	 * MediaType.APPLICATION_JSON).build(); } catch (Exception e) { return
	 * Response.status(Response.Status.BAD_REQUEST).build(); } }
	 */

	/*
	 * @Path("/Tasques")
	 * 
	 * @GET
	 * 
	 * @Produces( MediaType.APPLICATION_JSON ) public Response obtenirTasques() {
	 * try { List<Tasca> tasques= new ArrayList<Tasca>();
	 * 
	 * tasques.add(new Tasca("Fer els deures")); tasques.add(new
	 * Tasca("Fer el llit")); tasques.add(new Tasca("Dormir"));
	 * GenericEntity<List<Tasca>> genericEntity = new
	 * GenericEntity<List<Tasca>>(tasques){}; return Response.ok(genericEntity,
	 * MediaType.APPLICATION_JSON).build(); } catch (Exception e) { return
	 * Response.status(Response.Status.BAD_REQUEST).build(); } }
	 * 
	 * @Path("/TXT/{f}")
	 * 
	 * @GET
	 * 
	 * @Produces(MediaType.TEXT_PLAIN) public Response salutacioTXT(@PathParam("f")
	 * String nom) throws JSONException {
	 * 
	 * JSONObject jsonObject = new JSONObject();
	 * 
	 * jsonObject.put("salutacio", "Hola señor "+nom);
	 * 
	 * 
	 * String result = "" + jsonObject; String result="hola "+nom; return
	 * Response.status(200).entity(result).build();
	 * 
	 * }
	 * 
	 * 
	 * @Path("/XML/{f}")
	 * 
	 * @GET
	 * 
	 * @Produces(MediaType.APPLICATION_XML) public Response
	 * salutacioXML(@PathParam("f") String nom) throws JSONException {
	 * 
	 * JSONObject jsonObject = new JSONObject();
	 * 
	 * jsonObject.put("salutacio", "Hola señor "+nom);
	 * 
	 * 
	 * String result = "" + jsonObject; String
	 * result="<Persona> <nom>"+nom+"</nom> </Persona>"; return
	 * Response.status(200).entity(result).build();
	 * 
	 * }
	 * 
	 * @Path("/HTML/{f}")
	 * 
	 * @GET
	 * 
	 * @Produces(MediaType.TEXT_HTML) public Response salutacioHTML(@PathParam("f")
	 * String nom) throws JSONException {
	 * 
	 * JSONObject jsonObject = new JSONObject();
	 * 
	 * jsonObject.put("salutacio", "Hola señor "+nom);
	 * 
	 * 
	 * String result = "" + jsonObject; String
	 * result="<!DOCTYPE> <html> <body> <div>"+nom+"</div><body></html>"; return
	 * Response.status(200).entity(result).build();
	 * 
	 * }
	 */

}
