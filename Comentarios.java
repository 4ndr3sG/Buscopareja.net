package com.model;

public class comentarios {
	private String comentario;
	private String email;
	/*
	 * comentarios de la base de datos
	 * 
	 */
	
	public comentarios(String comentario, String email) {
		super();
		this.comentario = comentario;
		this.email = email;
		/*
		 * comentarios 
		 * y el email
		 */
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
	 * @return String de email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/*
	 * la clase constructora de los comentarios
	 * para almacenar en la base de datos
	 */
	public String toString() {
		StringBuffer sbResultado = new StringBuffer();
		sbResultado.append(comentario);
		sbResultado.append(", ");
		sbResultado.append(email);
		sbResultado.append("; \n");
		
		return sbResultado.toString();
		
	}
	

}
