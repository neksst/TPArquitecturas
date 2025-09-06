package org.practico.entities;

public class Cliente {
	
	private int id;
	private String Nombre;
	private String Email;
	
	public Cliente() {}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", Nombre=" + Nombre + ", Email=" + Email + "]";
	}
}
