package com.model;

public class Fotos {
	/*
	 * @clase constructora para fotos
	 * con comentario email y foto introducida
	 */
	private String comentario;
	private String email;
	private String foto;

	
	public Fotos(String comentario, String email,String foto) {
		super();
		this.comentario = comentario;
		this.email = email;
		this.foto = foto;
	}
	
	public String getComentario() {
		return comentario;
	}
	/*
	 * @return String de comentarios
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public String getEmail() {
		return email;
	}
	/*
	 * @return String de Email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFoto() {
		return foto;
	}
	/*
	 * @return String de foto
	 */
	public void setFoto(String foto) {
		this.foto = foto;
	}
	/*
	 * clase final para añadir fotos a la base de datos
	 * con el email como clave foranea de la base de datos
	 * 
	 */
	public String toString() {
		StringBuffer sbResultado = new StringBuffer();
		sbResultado.append(comentario);
		sbResultado.append(", ");
		sbResultado.append(email);
		sbResultado.append(", ");
		sbResultado.append(foto);
		sbResultado.append("; \n");
		
		return sbResultado.toString();
		
	}
}
