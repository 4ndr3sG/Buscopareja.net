<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.model.Usuarios"%>
<%@ page import="com.model.Fotos"%>
<%@ page import="com.model.comentarios"%>
<%@ page import="com.ies.baroja.Controller"%>
<%@ page import="java.util.LinkedList"%>
<%@ page import='java.io.InputStream' %> 
<%@ page import='java.io.OutputStream' %>

<%@ page import= "java.awt.image.BufferedImage"%>
<%@ page import= "java.io.ByteArrayInputStream" %>
<%@ page import= "java.io.File" %>
<%@ page import= "java.io.FileInputStream" %>
<%@ page import= "java.io.InputStream"%>

<%@ page import= "javax.imageio.ImageIO"%>
<%@ page import= "javax.swing.ImageIcon"%>
<%@ page import= "javax.swing.JOptionPane"%> 
<!DOCTYPE html>
<html lang="es" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="css/home.css">
</head>

<body>
  <div id="logo"  >
    <img src="images/logobuscopa.png" alt="logo" />
  </div>
  <div id="superior">
    <ul class="menu">
  		<li><a href="citasAsig.jsp" >Ver Citas</a></li>
      <li><a href="Hoteles.html" >Buscar hoteles</a></li>
      <li>  <a href="" >Tu perfil</a></li>
      <li><a href="index.html" >Cerrar sesión</a></li>
      <li class="slider"></li>
      </ul>
  </div>
        <div class="post" id="perfiles">
                
                    <span class="postInfo"><h1>Bienvenid@  
                      <h3><a href="#">Creado para conocer gente como tú</a></h3>
                      <h1>
                    <%
					HttpSession misesion = request.getSession();
					if(misesion.getAttribute("jugador")!= null){
					Usuarios jugador = (Usuarios) misesion.getAttribute("jugador");
					out.println(" " + jugador.getNombre_user() + "</h1><p>Encantados de tenerte de nuevo </p>");
					out.println("<h1>Eres de " + jugador.getCiudad_nac() + "</h1>");
					int z = 0;
					int r = 0;
					int y = 0;
					LinkedList<Usuarios> lista = Controller.getJugadores();
					for (int i = 0; i < lista.size(); i++) {
						int a =0;
						
						String b = "";
						if (i % 2 == 0) {
							out.println("<tr class='table-primary'>");
							a=1;
							b = "si";
						} else {
							out.println("<tr class='table-success'>");
							a=2;
							b="no";
						}
						if(lista.size()%2 != 0 && i == (lista.size()-1) ){
							a=0;
							b = "";
						}
						if (jugador.getUbicacion_id().equals(lista.get(i).getUbicacion_id())){
							z++;
						}
						if (!jugador.getSexo().equals(lista.get(i).getSexo().toUpperCase())){
							r++;
						}
					
						if(jugador.getGustos_id().equals(lista.get(i).getGustos_id())){
							
							y++;
						}
						
					}
					
					}
				else{
					response.sendRedirect("noconectado.jsp");
				}%>
                   
                    <a class="more" href="#">Escribe tu primer Post</a>
                    <span class="line"></span>
                    <a href="#"> #Share</a>
                    <span class="line"></span>
            
    <form action="ServletForm" method="post">
    <div>
             <% HttpSession misesion3 = request.getSession();
				if(misesion.getAttribute("jugador")!= null){
				Usuarios jugador = (Usuarios) misesion3.getAttribute("jugador");
				out.println("<input name=\"email3\" " +"type=\"hidden\" " + "value= "+ "\"" + jugador.getEmail() + "\""+ "</input>");
				out.println("<label type=\"text\" class=\"input-group-text\">Titulo</label> ");
				out.println("<input name=\"comentario2\" " +"type=\"text\" " + "class=\"form-control\" required></input> ");
				out.println("<label for=\"profile_pic\">Elige un archivo para subir</label> ");
				out.println("<input name=\"foto\" " +"type=\"file\" " + "accept=\".png, .jpg, .jpeg \" required> </input>");	
				}
				else{
					response.sendRedirect("noconectado.jsp");
				}
				%>
            </div>
        <button type="submit" class="btn btn-primary" >Submit</button></form>
  </div>
  
  <div id="postear"  >
       <h2 style="color: #396;">Estos son las últimas interacciones</h2>
    	
    <%	HttpSession misesion2 = request.getSession();
					
					if(misesion.getAttribute("jugador")!= null){
						String a = "";
						Usuarios jugador = (Usuarios) misesion2.getAttribute("jugador");
						LinkedList<Fotos> lista = Controller.getFotos();
						int m = 0;
						for (int i = 0; i < lista.size(); i++) {
							if (m>4){
								m = 1;
							}
							else{
								m ++;
							}
							a = lista.get(i).getFoto();
							 byte[] data = new byte[(int)a.length()];
							 try{
								  
								  BufferedImage img = null;

								  try{
								  img = ImageIO.read(new ByteArrayInputStream(data));
								  }
								  catch(Exception ex){
									  //out.println("<img src="+ "\"" +"data:image/jpeg;base64," +data+ "\">"+ "</img>");
								  }

								 ImageIcon icono = new ImageIcon(img);
								 JOptionPane.showMessageDialog(null, "Imagenes", "Imagen", JOptionPane.INFORMATION_MESSAGE, icono);
								 //out.println("<img src="+ "\"" + icono + "\""+ "</img>");

								  }catch(Exception ex){
									 // out.println("<img src="+ "\"" +"data:image/jpeg;base64," +data+ "\">"+ "</img>");
								  }
								out.println("<img src="+ "\"" +"images/captura" +m+ ".jpg"+"\""+" width='50%' height='50%'"+ "</img>");
								out.println("<h5>" + lista.get(i).getComentario() + "</h5>");
								out.println("<h3>" + lista.get(i).getEmail() + "</h3>");
								out.println("<hr>");							
							
				
						}
						
						 try{
							  byte[] data = new byte[(int)a.length()];
							  BufferedImage img = null;

							  try{
							  img = ImageIO.read(new ByteArrayInputStream(data));
							  }
							  catch(Exception ex){
								 // out.println("<img src="+ "\"" +"data:image/jpeg;base64," + "\""+ "</img>");
							  }

							 ImageIcon icono = new ImageIcon(img);
							// JOptionPane.showMessageDialog(null, "Imagenes", "Imagen", JOptionPane.INFORMATION_MESSAGE, icono);
							 //out.println("<img src="+ "\"" + icono + "\""+ "</img>");

							  }catch(Exception ex){
								 // out.println("<img src="+ "\"" +"data:image/jpeg;base64," + "\""+ "</img>");
							  }
						/**imaginamos que el algortimo de busqueda se basa en numeros aleatorios 
						para mostrar las imagenes se usa bucle for y rocorrer la lista de totos 
						y mostrar la mas acorde a los likes k se podria implementar en una tabla
						auxiliar si creas tabla liks asociada a la imagen de la base de datos 
						importante seguir en ese aspecto de algoritmo de busqueda por hora del telefono
						ya que es mas facil de implementar opcional los likes en base de datos relacionada con foto
						*/
					}
					else{
						response.sendRedirect("noconectado.jsp");
					}
					 
			
					%>
			
  </div><!--End Tab 4 -->
  <br>
<div id="tab3" class="d-grid gap-3">
    <form action="ServletForm" method="post">
    <div class="mx-auto" style="width:500px" >
             <% HttpSession misesion4 = request.getSession();
				if(misesion.getAttribute("jugador")!= null){
				Usuarios jugador = (Usuarios) misesion3.getAttribute("jugador");
				out.println("<input name=\"email2\" " +"type=\"hidden\" " + "value= "+ "\"" + jugador.getEmail() + "\""+ "</input>");
				
				}
				else{
					response.sendRedirect("noconectado.jsp");
				}
				%>
			<label for="comentario" class="form-label">¿ Tienes algún comentario ?</label>
              <textarea name="comentario" id="comentario" cols="30" rows="3" class="form-control" placeholder="Este comentario será visto por todos los usuarios" required></textarea>
        		<button type="submit" class="btn btn-primary" >Submit</button></form>
            </div>
            <div id="postear"  >
       			<h2 style="color: #396;">Estos son los últimos Comentarios</h2>
       			 <%	HttpSession misesion5 = request.getSession();
					
					if(misesion.getAttribute("jugador")!= null){
						String a = "";
						Usuarios jugador = (Usuarios) misesion2.getAttribute("jugador");
						LinkedList<comentarios> lista = Controller.getcomentarios();
						for (int i = 0; i < lista.size(); i++) {
							a = lista.get(i).getComentario();
								out.println("<h3>" + lista.get(i).getComentario() + "</h3>");
								out.println("<td>" + lista.get(i).getEmail() + "</td>");
								out.println("<hr>");	
							
				
						}
					}
					else{
						response.sendRedirect("noconectado.jsp");
					}
					 
			
					%>
  			</div>
  <div id="perfiles">

   <div class="card" style="width:300px">
          <img class="card-img-top tamaño" src="images/captura1.jpg" alt="Card image">
          <div class="card-body">
          <%	
				LinkedList<Usuarios> lista = Controller.getJugadores();
				HttpSession sesion = request.getSession();
				int z = 0;
				int r = 0;
				int y = 0;
				if(misesion.getAttribute("jugador")!= null){
				Usuarios jugador = (Usuarios) misesion.getAttribute("jugador");
				for (int i = 0; i < lista.size(); i++) {
					
					if (jugador.getUbicacion_id().equals(lista.get(i).getUbicacion_id())){
						z++;
					}
					if (!jugador.getSexo().equals(lista.get(i).getSexo().toUpperCase())){
						r++;
						if(jugador.getGustos_id().equals(lista.get(i).getGustos_id())){
							y++;
					
						}
					}
					
				}
				}
				else{
					response.sendRedirect("noconectado.jsp");
				}
				out.println("<span><h1>Hay " + y + " Personas con tus mismos gustos</h1></span>");
				
				%>
               <a href="Citacreada.html" class="btn btn-primary">Crear cita</a>
              <a href="chat.html" class="btn btn-primary">Chatear</a>
          </div>
      </div>

      <div class="card" style="width:300px">
          <img class="card-img-top tamaño" src="images/captura2.jpg" alt="Card image">
          <div class="card-body">
           <%	
				LinkedList<Usuarios> lista2 = Controller.getJugadores();
				HttpSession sesion2 = request.getSession();
				int z2 = 0;
				int r2 = 0;
				int y2 = 0;
				if(misesion.getAttribute("jugador")!= null){
				Usuarios jugador = (Usuarios) misesion.getAttribute("jugador");
				for (int i = 0; i < lista.size(); i++) {
					
					if (jugador.getUbicacion_id().equals(lista.get(i).getUbicacion_id())){
						z2++;
					}
					if (!jugador.getSexo().equals(lista.get(i).getSexo().toUpperCase())){
						r2++;
						if(jugador.getGustos_id().equals(lista.get(i).getGustos_id())){
							
							y2++;
							
						}
					}
				}
				}
				else{
					response.sendRedirect("noconectado.jsp");
				}
				
				out.println("<span><h1>Hay " + r2 + " Personas del genero que buscas</h1></span>");
				
				%>
               <a href="Citacreada.html" class="btn btn-primary">Crear cita</a>
              <a href="chat.html" class="btn btn-primary">Chatear</a>
          </div>
      </div>
      <div class="card" style="width:300px">
          <img class="card-img-top tamaño" src="images/captura3.jpg" alt="Card image">
          <div class="card-body">
           <%	
				LinkedList<Usuarios> lista3 = Controller.getJugadores();
				HttpSession sesion3 = request.getSession();
				int z3 = 0;
				int r3 = 0;
				int y3 = 0;
				if(misesion.getAttribute("jugador")!= null){
				Usuarios jugador = (Usuarios) misesion.getAttribute("jugador");
				for (int i = 0; i < lista.size(); i++) {
					
					if (jugador.getUbicacion_id().equals(lista.get(i).getUbicacion_id())){
						z3++;
					}
					if (!jugador.getSexo().equals(lista.get(i).getSexo().toUpperCase())){
						r3++;
						if(jugador.getGustos_id().equals(lista.get(i).getGustos_id())){
							
							y3++;
						}
					}
				}
				}
				else{
					response.sendRedirect("noconectado.jsp");
				}
				out.println("<span><h1>Hay " + z3 + " Personas en tu ubicacion</h1></span>");
				
				%>
              <a href="Citacreada.html" class="btn btn-primary">Crear cita</a>
              <a href="chat.html" class="btn btn-primary">Chatear</a>
          </div>
      </div>

  </div>
</body>
</html>
