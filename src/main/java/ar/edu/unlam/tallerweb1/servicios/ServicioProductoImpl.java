package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioLocalidad;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioLocker;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProducto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ServicioProductoImpl implements ServicioProducto {

    //private RepositorioProducto repositorioProducto;
    private RepositorioLocker repositorioLocker;
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioProductoImpl(RepositorioLocker repositorioLocker, RepositorioUsuario repositorioUsuario) {
        super();
        this.repositorioLocker = repositorioLocker;
        this.repositorioUsuario = repositorioUsuario;
    }
    @Override
    public Boolean validacionDatos(String nombre, String codigo, int lockerId) {
        return null;
    }
}
