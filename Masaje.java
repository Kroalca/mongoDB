package login;

import org.bson.Document;

public class Masaje {

    private String codmasaje;
    private String fecha;
    private String dnicliente;
    private String dnimasajista;

    public Masaje(String codmasaje, String fecha, String dnicliente, String dnimasajista){
        this.codmasaje = codmasaje;
        this.fecha = fecha;
        this.dnicliente = dnicliente;
        this.dnimasajista = dnimasajista;
    }

    public Masaje(Document masaje){
        this.codmasaje = masaje.getString("_id");
        this.fecha = masaje.getString("fecha");
        this.dnicliente = masaje.getString("cliente");
        this.dnimasajista = masaje.getString("masajista");
    }

    public Document TMasaje(){
        Document masaje = new Document();
        masaje.append("_id", this.codmasaje);
        masaje.append("fecha", this.fecha);
        masaje.append("cliente", this.dnicliente);
        masaje.append("masajista", this.dnimasajista);
        return masaje;
    }

	public String getCodmasaje() {
		return codmasaje;
	}

	public String getFecha() {
		return fecha;
	}

	public String getDnicliente() {
		return dnicliente;
	}

	public String getDnimasajista() {
		return dnimasajista;
	}
}
