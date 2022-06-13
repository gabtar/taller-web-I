package ar.edu.unlam.tallerweb1.persistencia;


import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Sucursal;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSucursal;
import org.hibernate.Session;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class RepositorioSucursalTest extends SpringTest {
    // todos los test de persistencia tienen que extender de SpringTest para testear la base

    @Autowired // inyecya bens en beens
    private RepositorioSucursal repositorioSucursal;

    @Test    @Transactional @Rollback
    public void seCreaSucursalesYRegresaTodas(){
        Sucursal sucursalA = new Sucursal();
        Sucursal sucursalB = new Sucursal();
        sucursalA.setNombre("A");
        sucursalB.setNombre("b");
        final Session session = session();
        session.save(sucursalA);
        session.save(sucursalB);
        // ejecucion
        List<Sucursal> lista= repositorioSucursal.listarSucursales();
        assertThat(lista.isEmpty()).isFalse();
    }



}