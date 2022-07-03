package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioProductoImpl implements RepositorioProducto {
    @Override
    public boolean validacionDatos(String nombre, String codigo, int lockerId) {
        Locker locker = new Locker();
        Usuario usuario = new Usuario();
//        if(codigo == locker.getCodigo() && nombre == usuario.getEmail()) {
//            return true;
//        }

        return false;
    }
}
