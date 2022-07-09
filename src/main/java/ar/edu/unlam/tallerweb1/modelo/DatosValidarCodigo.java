package ar.edu.unlam.tallerweb1.modelo;

public class DatosValidarCodigo {

    private String nombre;
    private String codigo;
    private Integer lockerId;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getLockerId() {
        return lockerId;
    }

    public void setLockerId(Integer lockerId) {
        this.lockerId = lockerId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
