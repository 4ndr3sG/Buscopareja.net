package com.bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.model.Fotos;
import com.model.Muro;
import com.model.Usuarios;
import com.model.comentarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Clase que centraliza los métodos de acceso a BBDD
 * 
 * @author AFGR
 * @since 29/05/2021
 */
/*Clase para conectar la base de datos a un servidor java servlet*/
public class ConexionBBDD {

	Connection conexion; 
	int port = 3306;
	String host= "localhost";
	String db  = "buscopareja";
	String user="root";
	String password="";

	String url = String.format("jdbc:mysql://%s:%d/%s?useSSL=false", host, port, db);

	public void conectar() throws SQLException {
		conexion =  DriverManager.getConnection(url, user, password);
	}	
	 

	public void desconectar() {
		try {
			conexion.close();
		} catch (SQLException sqlex) {
			System.out.println("Error: " + sqlex.getMessage());
		}
	}

	public ResultSet ejecutarConsulta(String sentencia) {
		ResultSet rsResultado = null;
		try {
			System.out.println("Ejecutando: " + sentencia);
			PreparedStatement prepStatement = conexion.prepareStatement(sentencia);
			rsResultado = prepStatement.executeQuery();
		} catch (SQLException sqlex) {
			System.out.println("Error: " + sqlex.getMessage());
		}
		return rsResultado;
	}
	
	public int insertarVehiculo(String sentencia, String sMatricula, String sMarca, String sModelo) {
		int iRes=0;
		try {
			System.out.println("Ejecutando: " + sentencia);
			PreparedStatement prepStatement = conexion.prepareStatement(sentencia);
			prepStatement.setString(1,sMatricula);
			prepStatement.setString(2,sMarca);
			prepStatement.setString(3,sModelo);
			iRes =  prepStatement.executeUpdate();
		} catch (SQLException sqlex) {
			System.out.println("Error: " + sqlex.getMessage());
		}
		return iRes;
	}
	public int insertar(Usuarios jugador) {
		int iRes = 0;
		String sInsert ="insert into usuario(Email, Pais_nac, Nombre_user, Ciudad_nac, Sexo, Orientacion,contrasena,ubicacion_id, gustos_id) values (?,?,?,?,?,?,?,?,?)";
		
		try {
			System.out.println("Ejecutando "+sInsert);
			System.out.println("datos "+jugador);
			PreparedStatement ps = conexion.prepareStatement(sInsert);
			ps.setString(1,jugador.getEmail());
			ps.setString(2,jugador.getPais_nac());
			ps.setString(3,jugador.getNombre_user());
			ps.setString(4,jugador.getCiudad_nac());
			ps.setString(5,jugador.getSexo());
			ps.setString(6,jugador.getContrasena());
			ps.setString(7,jugador.getOrientacion());
			ps.setInt(8, Integer.parseInt(jugador.getUbicacion_id()));
			ps.setInt(9, Integer.parseInt(jugador.getGustos_id()));
			
			
			iRes = ps.executeUpdate();
			
		}
		catch (SQLException sqlex) {
			System.out.println("Error: " + sqlex.getMessage());
			sqlex.printStackTrace();
		}
		return iRes;
	}
	public int insertarMuro(Muro comentario) {
		int iRes = 0;
		String sInsert ="insert into muro (comentario,Usuario_id) values (?,?)";
		
		try {
			System.out.println("Ejecutando "+sInsert);
			//System.out.println("datos "+comentario);
			PreparedStatement ps = conexion.prepareStatement(sInsert);
			ps.setString(1,comentario.getComentario());
			ps.setInt(2, Integer.parseInt(comentario.getUsuarioId()));
	
			
			iRes = ps.executeUpdate();
			
		}
		catch (SQLException sqlex) {
			//System.out.println("datos "+comentario);
			System.out.println("Error: " + sqlex.getMessage());
			sqlex.printStackTrace();
		}
		return iRes;
	}
	public int insertarcomentario(comentarios comentario) {
		int iRes = 0;
		String sInsert ="insert into comentarios (email,comentario) values (?,?)";
		
		try {
			System.out.println("Ejecutando "+sInsert);
			
			PreparedStatement ps = conexion.prepareStatement(sInsert);
			ps.setString(1,comentario.getComentario());
			ps.setString(2,comentario.getEmail());
			System.out.println(comentario.getComentario()+comentario.getEmail());
			
			iRes = ps.executeUpdate();
			
		}
		catch (SQLException sqlex) {
			//System.out.println("datos "+comentario);
			System.out.println("Error: " + sqlex.getMessage());
			sqlex.printStackTrace();
		}
		return iRes;
	}
	public int insertarFoto(Fotos comentario) {
		int iRes = 0;
		String sInsert ="insert into fotos (email,comentario,foto) values (?,?,?)";
		//File archivofoto = new File(comentario.getFoto());
		
		try {
			System.out.println("Ejecutando "+sInsert);
			File archivofoto = new File(comentario.getFoto());
			FileInputStream convertir_imagen = new FileInputStream (archivofoto);
			
			PreparedStatement ps = conexion.prepareStatement(sInsert);
			ps.setString(1,comentario.getComentario());
			ps.setString(2,comentario.getEmail());
			ps.setBlob(3, convertir_imagen, archivofoto.length());
			System.out.println(comentario.getComentario()+comentario.getEmail()+comentario.getFoto());
			
			iRes = ps.executeUpdate();
			
		}
		catch (SQLException sqlex) {
			//System.out.println("datos "+comentario);
			System.out.println("Error: " + sqlex.getMessage());
			sqlex.printStackTrace();
		
		}catch (FileNotFoundException ex) {
	    
        JOptionPane.showMessageDialog(null, "Error al intentar almacenar la información (Imagen):\n"
                                     + ex, "Error en la operación", JOptionPane.ERROR_MESSAGE);
    
    }
		return iRes;
	}
	

}
