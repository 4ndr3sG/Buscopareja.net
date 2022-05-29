package com.model;

public class Usuarios {
	/*
	 * clase constructora de los usuarios de la base de datos
	 * inserta en la base de datos los usuarios en base a los parametros
	 * de la base de datos buscopareja
	 */
	private String email;
	private String pais_nac;
	private String nombre_user;
	private String ciudad_nac;
	private String sexo;
	private String orientacion;
	private String contrasena;
	private String ubicacion_id;
	private String gustos_id;
	
	

	
	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getPais_nac() {
		return pais_nac;
	}




	public void setPais_nac(String pais_nac) {
		this.pais_nac = pais_nac;
	}




	public String getNombre_user() {
		return nombre_user;
	}




	public void setNombre_user(String nombre_user) {
		this.nombre_user = nombre_user;
	}




	public String getCiudad_nac() {
		return ciudad_nac;
	}




	public void setCiudad_nac(String ciudad_nac) {
		this.ciudad_nac = ciudad_nac;
	}




	public String getSexo() {
		return sexo;
	}




	public void setSexo(String sexo) {
		this.sexo = sexo;
	}




	public String getOrientacion() {
		return orientacion;
	}




	public void setOrientacion(String orientacion) {
		this.orientacion = orientacion;
	}




	public String getContrasena() {
		return contrasena;
	}




	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}




	public String getUbicacion_id() {
		return ubicacion_id;
	}




	public void setUbicacion_id(String ubicacion_id) {
		this.ubicacion_id = ubicacion_id;
	}




	public String getGustos_id() {
		return gustos_id;
	}




	public void setGustos_id(String gustos_id) {
		this.gustos_id = gustos_id;
	}
	

public Usuarios(String email, String pais_nac, String nombre_user, String ciudad_nac, String sexo,
			String orientacion, String contrasena, String ubicacion_id, String gustos_id) {
		super();
		this.email = email;
		this.pais_nac = pais_nac;
		this.nombre_user = nombre_user;
		this.ciudad_nac = ciudad_nac;
		this.sexo = sexo;
		this.orientacion = orientacion;
		this.contrasena = contrasena;
		this.ubicacion_id = ubicacion_id;
		this.gustos_id = gustos_id;
	}



	public String toString() {
		StringBuffer sbResultado = new StringBuffer();
		sbResultado.append(email);
		sbResultado.append(", ");
		sbResultado.append(pais_nac);
		sbResultado.append(", ");
		sbResultado.append(nombre_user);
		sbResultado.append(", ");
		sbResultado.append(ciudad_nac);
		sbResultado.append(", ");
		sbResultado.append(sexo);
		sbResultado.append(", ");
		sbResultado.append(orientacion);
		sbResultado.append(", ");
		sbResultado.append(contrasena);
		sbResultado.append(", ");
		sbResultado.append(ubicacion_id);
		sbResultado.append(", ");
		sbResultado.append(gustos_id);
		sbResultado.append("; \n");
		
		return sbResultado.toString();
		
	}
}
