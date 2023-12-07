package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.config.SpringConfig;
import com.banana.bananawhatsapp.modelos.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import org.springframework.beans.factory.annotation.Autowired;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.Matchers.is;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})

class UsuarioRepositoryTest {
    @Autowired
    IUsuarioRepository repo;


    @Test
    void testBeans() {
        assertThat(repo, notNullValue());
    }
    @Test
    void dadoUnUsuarioValido_cuandoCrear_entoncesUsuarioValido() throws SQLException {
        Usuario us1 = new Usuario(null, "jesus","cmoreno20@gmail.com",LocalDate.now(),true);

        if(us1.valido()){
           repo.crear(us1);
        }


        System.out.println(us1);

        assertThat(us1.getId(), greaterThan(0));
    }

    @Test
    void dadoUnUsuarioNOValido_cuandoCrear_entoncesExcepcion(){
        Usuario us1 = new Usuario(null, "jesus","cmoreno20@gmail.com",null,true);

        assertThrows(Exception.class, () -> {
            repo.crear(us1);
        });

    }

    @Test
    void dadoUnUsuarioValido_cuandoActualizar_entoncesUsuarioValido() throws SQLException {
        Usuario us1 = new Usuario(6, "CorPetit","carmelo@gmail.com",LocalDate.now(),true);
        repo.actualizar(us1);
        System.out.println(us1);

        assertThat(us1.getId(), greaterThan(0));

    }

    @Test
    void dadoUnUsuarioNOValido_cuandoActualizar_entoncesExcepcion() {
        Usuario us1 = new Usuario(4, "ander","cmoreno20@gmail.com",null,true);

        assertThrows(Exception.class, () -> {
            repo.actualizar(us1);
        });
    }

    @Test
    void dadoUnUsuarioValido_cuandoBorrar_entoncesOK() throws SQLException {
        Usuario us1 = new Usuario(3, null, null, null, true);

        boolean ok = repo.borrar(us1);

        assertThat(ok, is(true));
    }

    @Test
    void dadoUnUsuarioNOValido_cuandoBorrar_entoncesExcepcion() {
         Usuario us1 = new Usuario(3, null, null, null, true);

        assertThrows(Exception.class, () -> {
            repo.borrar(us1);
        });
    }

    @Test
    void dadoUnUsuarioValido_cuandoObtenerPosiblesDestinatarios_entoncesLista() throws SQLException {
        Set<Usuario> us = repo.obtenerPosiblesDestinatarios(1,1);

        System.out.println(us);

        assertThat(us.size(), greaterThan(0));
    }

    @Test
    void dadoUnUsuarioNOValido_cuandoObtenerPosiblesDestinatarios_entoncesExcepcion() {
    }

}