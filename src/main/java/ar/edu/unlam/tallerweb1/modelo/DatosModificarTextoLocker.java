package ar.edu.unlam.tallerweb1.modelo;

public class DatosModificarTextoLocker {
    private String textoModificado;
    private Locker locker;
    private int id;

    public String getTextoModificado() {
        return textoModificado;
    }

    public void setTextoModificado(String texto) {
        this.textoModificado = texto;
    }

    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
