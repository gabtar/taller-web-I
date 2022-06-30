package ar.edu.unlam.tallerweb1.servicios;

public interface ServicioProducto {

    Boolean validacionDatos(String nombre, String codigo, int lockerId);
}
