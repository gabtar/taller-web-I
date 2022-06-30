package ar.edu.unlam.tallerweb1.repositorios;


public interface RepositorioProducto {

    boolean validacionDatos(String nombre, String codigo, int lockerId);
}
