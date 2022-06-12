package ar.edu.unlam.tallerweb1.modelo;

public class DatosModificarTextoLocker {
    private String textoModificado;
    private Locker locker;

    public String getTexto() {
        return textoModificado;
    }

    public void setTexto(String texto) {
        this.textoModificado = texto;
    }

    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }
}
