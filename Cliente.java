package login;

import org.bson.Document;

public class Cliente {

    private String dni;
    private String nombre;
    private String apellido;
    private String fecha;
    private String tlf;
    private String pass;

    public Cliente(String dni, String nombre, String apellido, String tlf, String fecha, String pass){
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tlf = tlf;
        this.fecha = fecha;
        this.pass = pass;
    }

    public Cliente(Document cliente){
        this.dni = cliente.getString("_id");
        this.nombre = cliente.getString("nombre");
        this.apellido = cliente.getString("apellido");
		this.tlf = cliente.getString("tlf");
		//this.fecha = cliente.getString("fecha");
        this.pass = cliente.getString("contraseña");
    }

    public Document TCliente(){
        Document cliente = new Document();
        cliente.append("_id", this.dni);
        cliente.append("nombre", this.nombre);
        cliente.append("apellido", this.apellido);
        cliente.append("tlf", this.tlf);
        cliente.append("fecha", this.fecha);
        cliente.append("contraseña", this.pass);
        return cliente;
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

	public String getTlf() {
		return tlf;
	}

	public String getFecha() {
		return fecha;
	}

	public String getPass() {
		return pass;
	}
}
