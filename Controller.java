package com.ies.baroja;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.model.Fotos;
import com.model.Muro;
import com.model.Usuarios;
import com.model.comentarios;
import com.bbdd.ConexionBBDD;

public class Controller {
	private static String sConsultaJugadores = "SELECT email,pais_nac,nombre_user,ciudad_nac,sexo,orientacion,contrasena,ubicacion_id,gustos_id FROM usuario;";

	public static LinkedList<Usuarios> getJugadores() {
//Objeto con la lista de jugadores
		LinkedList<Usuarios> listaJugadores = new LinkedList<Usuarios>();
// Primero conectamos a la BBDD
		ConexionBBDD miConexion = new ConexionBBDD();
		try {
			miConexion.conectar();
// Lanzamos la consulta
			ResultSet rsResultado = miConexion.ejecutarConsulta(sConsultaJugadores);
			if (rsResultado != null) {
// Si hay resultado recuperamos los datos (como un FETCHde un CURSOR)

				while (rsResultado.next()) {
					// Creamos un objeto jugador por cada fila de la tabla(cada jugador)

					Usuarios jugador = new Usuarios(rsResultado.getString("email"),rsResultado.getString("pais_nac"),rsResultado.getString("nombre_user"),
							rsResultado.getString("ciudad_nac"), rsResultado.getString("sexo"),
							 rsResultado.getString("orientacion"), rsResultado.getString("contrasena"),
							 String.valueOf(rsResultado.getInt("ubicacion_id")),String.valueOf(rsResultado.getInt("gustos_id")));
					// Lo insertamos en la lista
					listaJugadores.add(jugador);
				}
			} else {
				System.out.println("La consulta no devuelve resultados");
			}
			System.out.println("Número de jugadores=" + listaJugadores.size());
		} catch (SQLException sqlex) {
			System.out.println("Error: " + sqlex.getMessage());
			sqlex.printStackTrace();
		} finally {
			miConexion.desconectar();
		}
		return listaJugadores;
	}
	
public static Usuarios getJugador(String sNombreJugador) {
		
	String sConsultaBusq = "SELECT email,pais_nac,nombre_user,ciudad_nac,sexo,orientacion,contrasena,ubicacion_id,gustos_id FROM usuario where Nombre_user = '"+sNombreJugador+"';";
	//String sConsultaBusq2 = "SELECT email,pais_nac,nombre_user,ciudad_nac,sexo,orientacion,contrasena,ubicacion_id,gustos_id FROM usuario where Nombre_user = '"+sContrasenaJugador+"';";
	
		Usuarios jugador = null;
		ConexionBBDD miConexion = new ConexionBBDD();
		try {
		/**1- conectar a la base de datos */
			miConexion.conectar();
		/**2- lanzar la consulta*/
			ResultSet rsResultado = miConexion.ejecutarConsulta(sConsultaBusq);
		/**3-recuperar los datos*/
			if (rsResultado != null) {
				// Si hay resultado recuperamos los datos (como un FETCHde un CURSOR)

								while (rsResultado.next()) {
									// Creamos un objeto jugador por cada fila de la tabla(cada jugador)

									 jugador = new Usuarios(rsResultado.getString("email"),rsResultado.getString("pais_nac"),rsResultado.getString("nombre_user"),
												rsResultado.getString("ciudad_nac"), rsResultado.getString("sexo"),
												 rsResultado.getString("orientacion"), rsResultado.getString("contrasena"),
												 String.valueOf(rsResultado.getInt("ubicacion_id")),String.valueOf(rsResultado.getInt("gustos_id")));
									// Lo insertamos en la lista
								
								}
							} else {
								System.out.println("La consulta no devuelve resultados");
							}
		/**4-cerrar conexion*/
		}catch (SQLException sqlex) {
			System.out.println("Error: " + sqlex.getMessage());
			sqlex.printStackTrace();
		} finally {
			miConexion.desconectar();
		}
		return jugador;
	}
	
public static Usuarios getPassw(String sContrasenaJugador) {
	
	//String sConsultaBusq = "SELECT email,pais_nac,nombre_user,ciudad_nac,sexo,orientacion,contrasena,ubicacion_id,gustos_id FROM usuario where Nombre_user = '"+sNombreJugador+"';";
	String sConsultaBusq2 = "SELECT email,pais_nac,nombre_user,ciudad_nac,sexo,orientacion,contrasena,ubicacion_id,gustos_id FROM usuario where contrasena = '"+sContrasenaJugador+"';";
	
		Usuarios jugador = null;
		ConexionBBDD miConexion = new ConexionBBDD();
		try {
		/**1- conectar a la base de datos */
			miConexion.conectar();
		/**2- lanzar la consulta*/
			ResultSet rsResultado = miConexion.ejecutarConsulta(sConsultaBusq2);
		/**3-recuperar los datos*/
			if (rsResultado != null) {
				// Si hay resultado recuperamos los datos (como un FETCHde un CURSOR)

								while (rsResultado.next()) {
									// Creamos un objeto jugador por cada fila de la tabla(cada jugador)

									 jugador = new Usuarios(rsResultado.getString("email"),rsResultado.getString("pais_nac"),rsResultado.getString("nombre_user"),
												rsResultado.getString("ciudad_nac"), rsResultado.getString("sexo"),
												 rsResultado.getString("orientacion"), rsResultado.getString("contrasena"),
												 String.valueOf(rsResultado.getInt("ubicacion_id")),String.valueOf(rsResultado.getInt("gustos_id")));
									// Lo insertamos en la lista
								
								}
							} else {
								System.out.println("La consulta no devuelve resultados");
							}
		/**4-cerrar conexion*/
		}catch (SQLException sqlex) {
			System.out.println("Error: " + sqlex.getMessage());
			sqlex.printStackTrace();
		} finally {
			miConexion.desconectar();
		}
		return jugador;
	}
public static Usuarios getemail(String sContrasenaJugador) {
	
	//String sConsultaBusq = "SELECT email,pais_nac,nombre_user,ciudad_nac,sexo,orientacion,contrasena,ubicacion_id,gustos_id FROM usuario where Nombre_user = '"+sNombreJugador+"';";
	String sConsultaBusq2 = "SELECT email,pais_nac,nombre_user,ciudad_nac,sexo,orientacion,contrasena,ubicacion_id,gustos_id FROM usuario where email = '"+sContrasenaJugador+"';";
	
		Usuarios jugador = null;
		ConexionBBDD miConexion = new ConexionBBDD();
		try {
		/**1- conectar a la base de datos */
			miConexion.conectar();
		/**2- lanzar la consulta*/
			ResultSet rsResultado = miConexion.ejecutarConsulta(sConsultaBusq2);
		/**3-recuperar los datos*/
			if (rsResultado != null) {
				// Si hay resultado recuperamos los datos (como un FETCHde un CURSOR)

								while (rsResultado.next()) {
									// Creamos un objeto jugador por cada fila de la tabla(cada jugador)

									 jugador = new Usuarios(rsResultado.getString("email"),rsResultado.getString("pais_nac"),rsResultado.getString("nombre_user"),
												rsResultado.getString("ciudad_nac"), rsResultado.getString("sexo"),
												 rsResultado.getString("orientacion"), rsResultado.getString("contrasena"),
												 String.valueOf(rsResultado.getInt("ubicacion_id")),String.valueOf(rsResultado.getInt("gustos_id")));
									// Lo insertamos en la lista
								
								}
							} else {
								System.out.println("La consulta no devuelve resultados");
							}
		/**4-cerrar conexion*/
		}catch (SQLException sqlex) {
			System.out.println("Error: " + sqlex.getMessage());
			sqlex.printStackTrace();
		} finally {
			miConexion.desconectar();
		}
		return jugador;
	}
public static boolean insertarMuro(Muro comentario) {
	boolean bRes = true;
	// Primero conectamos a la BBDD
	ConexionBBDD miConexion = new ConexionBBDD();
	//System.out.println(miConexion + " insertamuro");
	try {
		miConexion.conectar();
		int iRes = miConexion.insertarMuro(comentario);
		System.out.println("Resultado insertar el jugador="+iRes);
	} catch (SQLException sqlex) {
		System.out.println("datos "+comentario);
		System.out.println("Error: " + sqlex.getMessage());
		sqlex.printStackTrace();
	} finally {
		miConexion.desconectar();
	}
	return bRes;
}

public static LinkedList<Muro>getMuro(){
	String sConsultaMuro = "SELECT id,comentario,usuario_id FROM muro ;";

	//Objeto con la lista de jugadores
			LinkedList<Muro> listaComentarios = new LinkedList<Muro>();
	// Primero conectamos a la BBDD
			ConexionBBDD miConexion = new ConexionBBDD();
			try {
				miConexion.conectar();
	// Lanzamos la consulta
				ResultSet rsResultado = miConexion.ejecutarConsulta(sConsultaMuro);
				if (rsResultado != null) {
	// Si hay resultado recuperamos los datos (como un FETCHde un CURSOR)

					while (rsResultado.next()) {
						// Creamos un objeto jugador por cada fila de la tabla(cada jugador)

						Muro comentario = new Muro(rsResultado.getString("comentario"),
								 String.valueOf(rsResultado.getInt("usuario_Id")));
						// Lo insertamos en la lista
						listaComentarios.add(comentario);
					}
				} else {
					System.out.println("La consulta no devuelve resultados");
				}
				System.out.println("Número de jugadores=" + listaComentarios.size());
			} catch (SQLException sqlex) {
				System.out.println("Error: " + sqlex.getMessage());
				sqlex.printStackTrace();
			} finally {
				miConexion.desconectar();
			}
			return listaComentarios;
		}
	
	public static boolean insertarJugador(Usuarios jugador) {
		boolean bRes = true;
		// Primero conectamos a la BBDD
		ConexionBBDD miConexion = new ConexionBBDD();
		try {
			miConexion.conectar();
			int iRes = miConexion.insertar(jugador);
			System.out.println("Resultado insertar el jugador="+iRes);
		} catch (SQLException sqlex) {
			System.out.println("Error: " + sqlex.getMessage());
			sqlex.printStackTrace();
		} finally {
			miConexion.desconectar();
		}
		return bRes;
	}
	public static boolean insertarcomentario(comentarios comentario) {
		boolean bRes = true;
		// Primero conectamos a la BBDD
		ConexionBBDD miConexion = new ConexionBBDD();
		System.out.println(miConexion + " insertacomentario ");
		try {
			miConexion.conectar();
			int iRes = miConexion.insertarcomentario(comentario);
			System.out.println("Resultado insertar el jugador="+iRes);
		} catch (SQLException sqlex) {
			System.out.println("datos "+comentario);
			System.out.println("Error: " + sqlex.getMessage());
			sqlex.printStackTrace();
		} finally {
			miConexion.desconectar();
		}
		return bRes;
	}
	public static LinkedList<comentarios>getcomentarios(){
		String sConsultacomentarios = "SELECT email,comentario FROM comentarios ;";

		//Objeto con la lista de jugadores
		LinkedList<comentarios> listaComentario = new LinkedList<comentarios>();
		// Primero conectamos a la BBDD
				ConexionBBDD miConexion = new ConexionBBDD();
				try {
					miConexion.conectar();
		// Lanzamos la consulta
					ResultSet rsResultado = miConexion.ejecutarConsulta(sConsultacomentarios);
					if (rsResultado != null) {
		// Si hay resultado recuperamos los datos (como un FETCHde un CURSOR)

						while (rsResultado.next()) {
							// Creamos un objeto jugador por cada fila de la tabla(cada jugador)

							comentarios comentario = new comentarios(rsResultado.getString("email"),
									rsResultado.getString("comentario"));
							// Lo insertamos en la lista
							listaComentario.add(comentario);
						}
					} else {
						System.out.println("La consulta no devuelve resultados");
					}
					System.out.println("Número de jugadores=" + listaComentario.size());
				} catch (SQLException sqlex) {
					System.out.println("Error: " + sqlex.getMessage());
					sqlex.printStackTrace();
				} finally {
					miConexion.desconectar();
				}
				return listaComentario;
			}
	public static boolean insertarFoto(Fotos comentario) {
		boolean bRes = true;
		// Primero conectamos a la BBDD
		ConexionBBDD miConexion = new ConexionBBDD();
		System.out.println(miConexion + " insertafoto ");
		try {
			miConexion.conectar();
			int iRes = miConexion.insertarFoto(comentario);
			System.out.println("Resultado insertar el jugador="+iRes);
		} catch (SQLException sqlex) {
			System.out.println("datos "+comentario);
			System.out.println("Error: " + sqlex.getMessage());
			sqlex.printStackTrace();
		} finally {
			miConexion.desconectar();
		}
		return bRes;
	}
	public static LinkedList<Fotos>getFotos(){
		String sConsultafotos = "SELECT email,comentario,foto FROM fotos;";

		//Objeto con la lista de jugadores
		LinkedList<Fotos> listaComentario = new LinkedList<Fotos>();
		// Primero conectamos a la BBDD
				ConexionBBDD miConexion = new ConexionBBDD();
				try {
					miConexion.conectar();
		// Lanzamos la consulta
					ResultSet rsResultado = miConexion.ejecutarConsulta(sConsultafotos);
					if (rsResultado != null) {
		// Si hay resultado recuperamos los datos (como un FETCHde un CURSOR)

						while (rsResultado.next()) {
							// Creamos un objeto jugador por cada fila de la tabla(cada jugador)

							Fotos comentario = new Fotos(rsResultado.getString("email"),
									rsResultado.getString("comentario"),
									rsResultado.getString("foto"));
							// Lo insertamos en la lista
							listaComentario.add(comentario);
						}
					} else {
						System.out.println("La consulta no devuelve resultados");
					}
					System.out.println("Número de jugadores=" + listaComentario.size());
				} catch (SQLException sqlex) {
					System.out.println("Error: " + sqlex.getMessage());
					sqlex.printStackTrace();
				} finally {
					miConexion.desconectar();
				}
				return listaComentario;
			}
}
