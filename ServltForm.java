package com.ies.baroja;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Fotos;
import com.model.Muro;
import com.model.Usuarios;
import com.model.comentarios;

/**
 * Servlet implementation class ServletForm
 */
@WebServlet("/ServletForm")
public class ServletForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Error: la llamadas GET no estan permitidas").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			 
			 if (request.getParameter("buscaNombre") != null) {
					buscaJugador(request, response);
				}
			 else if ((request.getParameter("correoelectronico") != null) && (request.getParameter("buscaPass")!= null)) {
				 	loginUsuario(request, response);
				}
			 else if (request.getParameter("email") != null) {
					altaJugador(request, response);
				}
			 else if ((request.getParameter("comentario") != null)&&(request.getParameter("usuario_Id") != null)  ){
				 System.out.println("muro ");
				 altaComentario(request, response);
					}
			 else if ((request.getParameter("email2") != null)&&(request.getParameter("comentario") != null)  ){
				 System.out.println("muro ");
				 crearComentario(request, response);
					}
			 else if ((request.getParameter("email3") != null)&&(request.getParameter("comentario2") != null)&&(request.getParameter("foto") != null)  ){
				 System.out.println("foto ");
				 crearFotos(request, response);
					}
			 

		} catch (Exception ex) {
			System.out.println("Error en servlet");
			ex.printStackTrace();
			mostrarError(response,"Error en el serlet");
		}
	}

	private static void loginUsuario(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//HttpSession sesion = request.getSession();
		//String usu, pass;
		/**1- regogida de datos de la pagina*/
		String sNombre = request.getParameter("correoelectronico");
		/**1- regogida de datos de la pagina*/
		String spwsd = request.getParameter("buscaPass");
		/**2- buscar jugadores en la BBDD */
		Usuarios jugador = Controller.getemail(sNombre);
		/**2- buscar jugadores en la BBDD */
		Usuarios jugador2 = Controller.getPassw(spwsd);
		/*String sEmail = request.getParameter("emailConf");
		String sPwd = request.getParameter("pwd");*/
		// deberaamos buscar el usuario en la base de datos, pero 
		//ponemos un ejemplo en el mismo cadigo
		System.out.println((jugador != null) && jugador.getEmail().equals(sNombre));
		//System.out.println((jugador2) +" "+ jugador2.getEmail());
		//" "+jugador2.getEmail().equals(spwsd)
		System.out.println((jugador2 != null) && jugador2.getContrasena().equals(spwsd));
		if((sNombre.equals("admin@admin"))) {
			System.out.println(jugador +" admin "+jugador2);
			if((spwsd.equals("admin"))) {
				//System.out.println(jugador +" admin "+jugador2);
				HttpSession sesion = request.getSession();
				sesion.setAttribute("jugador", jugador);
				response.sendRedirect("loginregis.jsp");
			}
		}
		if(((jugador != null) && (jugador.getEmail().equals(sNombre)))){
			if(((jugador2 != null) && (jugador2.getContrasena().equals(spwsd))) ) {
				
				//System.out.println(jugador +" "+jugador2);
				HttpSession sesion = request.getSession();
				sesion.setAttribute("jugador", jugador);
				response.sendRedirect("Home.jsp");
			
			
			}
			else {
				//mostrarError(response, "Jugador "+sNombre+" no encontrado");
				response.sendRedirect("index.html");
			}
			
		}
		else {
			//mostrarError(response, "Jugador "+sNombre+" no encontrado");
			response.sendRedirect("index.html");
		}
		/*if (sEmail.equals("micorreo@gmail.com") && sPwd.equals("password") 
					&& sesion.getAttribute("email") == null) {
			// si coincide email y password y ademas no hay sesian iniciada
			sesion.setAttribute("email", sEmail);
			// redirijo a pagina con informacian de login exitoso
			response.sendRedirect("loginExito.jsp");
		} else {
			// lagica para login invalido
			mostrarError(response,"El usuario "+sEmail+"no sirve");
		}*/
	}
	
private static void buscaJugador (HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		/**1- regogida de datos de la pagina*/
		String sNombre = request.getParameter("buscaNombre");
		/**2- buscar jugadores en la BBDD */
		Usuarios jugador = Controller.getJugador(sNombre);
		
		if(jugador != null) {
			HttpSession sesion = request.getSession();
			sesion.setAttribute("jugador", jugador);
			response.sendRedirect("listadoJugador.jsp");
		}
		
		else {
			mostrarError(response, "Jugador "+sNombre+" no encontrado");
		}
	}

	private static void altaJugador(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/** 1- recogida de datos */
		Usuarios jugador = new Usuarios(request.getParameter("email"), request.getParameter("pais_nac"),
				request.getParameter("nombre_user"), request.getParameter("ciudad_nac"), request.getParameter("sexo"),
				request.getParameter("orientacion"),request.getParameter("contrasena"),request.getParameter("ubicacion_id"),
				request.getParameter("gustos_id"));

		/** 2- Insertar jugador en la base de datos */
		boolean bRes = Controller.insertarJugador(jugador);

		/** 3- Mostrar resultado por pantalla */
		if (bRes) {
			HttpSession sesion = request.getSession();
			sesion.setAttribute("jugador", jugador);
			response.sendRedirect("Home.jsp");
			/*response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<HTML>\n" + "<HEAD><TITLE>Pagina de JAGD</TITLE>" + "  <meta charset=\"utf-8\">\r\n"
					+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
					+ "  <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n"
					+ "  <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\"></script>\r\n"
					+ "</HEAD>\n" + "<BODY>\n" + "<div class=\"container mt-3\">\n"
					+ "<h2 class=\"text-success\">Jugador insertado correctamente<h2>\n"
					+ "<ul class=\"list-group\"> \n" + "  <LI class=\"list-group-item\">email: " + jugador.getEmail()
					+ "</li>\n" + "  <LI class=\"list-group-item\">pais_naci: " + jugador.getPais_nac() + "</li>\n"
					+ "  <LI class=\"list-group-item\">nombre: " + jugador.getNombre_user() + "</li>\n"
					+ "  <LI class=\"list-group-item\">ciudad_nac: " + jugador.getCiudad_nac() + "</li>\n"
					+ "  <LI class=\"list-group-item\">sexo: " + jugador.getSexo() + "</li>\n"
					+ "  <LI class=\"list-group-item\">contrasena: " + jugador.getOrientacion() + "</li>\n"
					+ "  <LI class=\"list-group-item\">orientacion: " + jugador.getContrasena() + "</li>\n"
					+ "  <LI class=\"list-group-item\">ubicacion: " + jugador.getUbicacion_id() + "</li>\n"
					+ "  <LI class=\"list-group-item\">gustos: " + jugador.getGustos_id() + "</li>\n"
					+ "</UL>\n" + "</div></BODY></HTML>");
			out.close();*/

		} else {
			// Mostramos que se ha producido un error
			mostrarError(response, "Error al dar de alta el jugador");

		}
	}
	private static void altaComentario(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/** 1- recogida de datos */
		Muro comentario = new Muro(request.getParameter("comentario"), request.getParameter("usuario_Id"));

		/** 2- Insertar jugador en la base de datos */
		boolean bRes = Controller.insertarMuro(comentario);

		/** 3- Mostrar resultado por pantalla */
		if (bRes) {
			HttpSession sesion = request.getSession();
			sesion.setAttribute("jugador", comentario);
			response.sendRedirect("loginregis.jsp");

		} else {
			// Mostramos que se ha producido un error
			mostrarError(response, "Error al dar de alta comentario");
		}
	}
	private static void crearComentario(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/** 1- recogida de datos */
		comentarios comentario = new comentarios(request.getParameter("email2"), request.getParameter("comentario"));
		System.out.println(request.getParameter("email2") +" y "+ request.getParameter("comentario"));
		/** 2- Insertar jugador en la base de datos */
		boolean bRes = Controller.insertarcomentario(comentario);

		/** 3- Mostrar resultado por pantalla */
		if (bRes) {
			HttpSession sesion = request.getSession();
			sesion.setAttribute("jugador", comentario);
			response.sendRedirect("errorInesperado.jsp");

		} else {
			// Mostramos que se ha producido un error
			mostrarError(response, "Error al dar de alta comentarios");
		}
	}
	private static void crearFotos(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/** 1- recogida de datos */
		Fotos comentario = new Fotos(request.getParameter("email3"), request.getParameter("comentario2"),request.getParameter("foto"));
		System.out.println(request.getParameter("email3") +" y "+ request.getParameter("comentario2") +" y "+ request.getParameter("foto") );
		/** 2- Insertar jugador en la base de datos */
		boolean bRes = Controller.insertarFoto(comentario);

		/** 3- Mostrar resultado por pantalla */
		if (bRes) {
			HttpSession sesion = request.getSession();
			sesion.setAttribute("jugador", comentario);
			response.sendRedirect("errorInesperado.jsp");

		} else {
			// Mostramos que se ha producido un error
			mostrarError(response, "Error al dar de alta comentarios");
		}
	}
	

	private static void mostrarError(HttpServletResponse response, String sMensaje) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML>\n" + "<HEAD><TITLE>BuscoPareja</TITLE>" + "  <meta charset=\"utf-8\">\r\n"
				+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
				+ "  <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n"
				+ "  <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\"></script>\r\n"
				+ "</HEAD>\n" + "<BODY>\n" + "<div class=\"container mt-3\">\n"
				+ "<h1 class=\"text-danger\">Error interno<h1>\n"
				+ "<h2 class=\"text-danger\">"+sMensaje+"<h2>\n"
				+ "<img src=\"img/logobuscopa.png\" class=\"rounded\" alt=\"error interno\">" + "</div></BODY></HTML>"
				+"<div class=\"mb-3 mt-3\">\r\n"
				+"<form action=\"index.html\" method=\"POST\" >\r\n"
				+ " <button type=\"submit\" class=\"btn btn-primary\">regresar</button>\r\n"
				+ "</form>"
				);
		out.close();
	}

}

