package mx.edu.utez.sgeu.model;

public class TipoEvento {

    private int idTipoEvento;
    private String nombre;
    private String descripcion;
    private String estado;

    public TipoEvento() {
    }

    public TipoEvento(int idTipoEvento, String nombre) {
        this.idTipoEvento = idTipoEvento;
        this.nombre = nombre;
    }

    public int getIdTipoEvento() {
        return idTipoEvento;
    }

    public void setIdTipoEvento(int idTipoEvento) {
        this.idTipoEvento = idTipoEvento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}