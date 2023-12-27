package com.banana.bananawhatsapp.servicios;

import com.banana.bananawhatsapp.config.SpringConfig;
import com.banana.bananawhatsapp.modelos.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import static org.hamcrest.Matchers.is;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
class ServicioUsuariosTest {
    @Autowired
    IServicioUsuarios sr;

    @Test
    void testBeans() {
        assertThat(sr, notNullValue());
    }

    @Test
    void dadoUnUsuarioValido_cuandoCrearUsuario_entoncesUsuarioValido() {
        Usuario us1 = new Usuario(null, "marcos","marcos@gmail.com", LocalDate.now(),true);
        sr.crearUsuario(us1);
        System.out.println(us1);
        assertThat(us1.getId(), greaterThan(0));
    }

    @Test
    void dadoUnUsuarioNOValido_cuandoCrearUsuario_entoncesExcepcion() {
        Usuario us1 = new Usuario(null, "jesus","cmoreno20@gmail.com",null,true);

        assertThrows(Exception.class, () -> {
            sr.crearUsuario(us1);
        });
    }

    @Test
    void dadoUnUsuarioValido_cuandoBorrarUsuario_entoncesUsuarioValido() {
        Usuario us1 = new Usuario(6, null, null, null, true);

        boolean ok = sr.borrarUsuario(us1);

        assertThat(ok, is(true));

    }

    @Test
    void dadoUnUsuarioNOValido_cuandoBorrarUsuario_entoncesExcepcion() {
        Usuario us1 = new Usuario(6, null, null, null, true);


        assertThrows(Exception.class, () -> {
             boolean ok = sr.borrarUsuario(us1);
        });
    }

    @Test
    void dadoUnUsuarioValido_cuandoActualizarUsuario_entoncesUsuarioValido() {
        Usuario us1 = new Usuario(7, "goku","goku@gmail.com",LocalDate.now(),true);
        sr.actualizarUsuario(us1);
        System.out.println(us1);

        assertThat(us1.getId(), greaterThan(0));

    }

    @Test
    void dadoUnUsuarioNOValido_cuandoActualizarUsuario_entoncesExcepcion() {
        Usuario us1 = new Usuario(4, "ander","cmoreno20@gmail.com",null,true);

        assertThrows(Exception.class, () -> {
            sr.actualizarUsuario(us1);
        });
    }

    @Test
    void dadoUnUsuarioValido_cuandoObtenerPosiblesDesinatarios_entoncesUsuarioValido() {
    }

    @Test
    void dadoUnUsuarioNOValido_cuandoObtenerPosiblesDesinatarios_entoncesExcepcion() {
    }
}