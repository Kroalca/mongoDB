package login;

import org.bson.Document;


public class Masajista {
	
	private String dni;
	private String nombre;
	private String apellido;
	private String fecha;
	private String telefono;
	private String contraseña;
	
	public Masajista(String dni,String nombre,String apellido,String telefono,String fecha,String contraseña) {
		this.dni=dni;
		this.nombre=nombre;
		this.apellido=apellido;
		this.fecha=fecha;
		this.telefono=telefono;
		this.contraseña=contraseña;
	}
	
	public Masajista(Document masajista) {
		this.dni = masajista.getString("_id");
		this.nombre = masajista.getString("nombre");
		this.apellido = masajista.getString("apellido");
		this.telefono = masajista.getString("tlf");
		this.fecha = masajista.getString("fecha");
		this.contraseña = masajista.getString("pass");

	}
	
	public Document TMasajista() {
		
		Document masajista = new Document();
		
		masajista.append("_id", this.dni);
		masajista.append("nombre", this.nombre);
		masajista.append("apellido", this.apellido);
		masajista.append("tlf", this.telefono);
		masajista.append("fecha", this.fecha);
		masajista.append("pass", this.contraseña);
		
		return masajista;

		
	}

	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getFecha() {
		return fecha;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getContraseña() {
		return contraseña;
	}

}
