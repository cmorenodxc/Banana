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

import java.sql.SQLException;
import java.time.LocalDate;

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
        Usuario us1 = new Usuario(null, "carmelo","cmoreno20",LocalDate.now(),true);
        repo.crear(us1);

        System.out.println(us1);

        assertThat(us1.getId(), greaterThan(0));
    }

    @Test
    void dadoUnUsuarioNOValido_cuandoCrear_entoncesExcepcion() {
    }

    @Test
    void dadoUnUsuarioValido_cuandoActualizar_entoncesUsuarioValido() {
    }

    @Test
    void dadoUnUsuarioNOValido_cuandoActualizar_entoncesExcepcion() {
    }

    @Test
    void dadoUnUsuarioValido_cuandoBorrar_entoncesOK() {
    }

    @Test
    void dadoUnUsuarioNOValido_cuandoBorrar_entoncesExcepcion() {
    }

    @Test
    void dadoUnUsuarioValido_cuandoObtenerPosiblesDestinatarios_entoncesLista() {
    }

    @Test
    void dadoUnUsuarioNOValido_cuandoObtenerPosiblesDestinatarios_entoncesExcepcion() {
    }

}