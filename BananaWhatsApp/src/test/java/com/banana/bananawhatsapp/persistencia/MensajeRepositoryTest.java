package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.config.SpringConfig;
import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.IsNull.notNullValue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
class MensajeRepositoryTest {
    @Autowired
    IMensajeRepository repo;

    @Test
    void testBeans() {
        assertThat(repo, notNullValue());
    }
    @Test
    void dadoUnMensajeValido_cuandoCrear_entoncesMensajeValido() throws SQLException {
        Usuario usDestinatario = new Usuario(),usRemitente = new Usuario();

        usDestinatario.setId(4);
        usRemitente.setId(6);

        Mensaje men = new Mensaje(null, usRemitente,usDestinatario,"hola, que tal?", LocalDate.now());

        if(men.valido()){
           repo.crear(men);
        }


        System.out.println(men);

        assertThat(men.getId(), greaterThan(0));
    }

    @Test
    void dadoUnMensajeNOValido_cuandoCrear_entoncesExcepcion() {
        Usuario usDestinatario = new Usuario(),usRemitente = new Usuario();

        usDestinatario.setId(4);
        usRemitente.setId(6);

        Mensaje men = new Mensaje(null, usRemitente,usDestinatario,"hola, que tal?", null);

         assertThrows(Exception.class, () -> {
            repo.crear(men);
        });
    }

    @Test
    void dadoUnUsuarioValido_cuandoObtener_entoncesListaMensajes() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1);

       List<Mensaje> men = repo.obtener(usuario);

        System.out.println(men);

        assertThat(men.size(), greaterThan(0));
    }


    @Test
    void dadoUnUsuarioNOValido_cuandoObtener_entoncesExcepcion()  {
        Usuario usuario = new Usuario();
        usuario.setId(9);

         assertThrows(Exception.class, () -> {
            List<Mensaje> men = repo.obtener(usuario);
        });
    }

    @Test
    void dadoUnUsuarioValido_cuandoBorrarTodos_entoncesOK() throws SQLException {
           Usuario usuario = new Usuario(6,null,null,null,false);
           boolean ok = repo.borrarTodos(usuario);

            assertThat(ok, is(true));
    }

    @Test
    void dadoUnUsuarioNOValido_cuandoBorrarTodos_entoncesExcepcion() throws SQLException {
           Usuario usuario = new Usuario(6,null,null,null,false);

           assertThrows(Exception.class, () -> {
            boolean ok = repo.borrarTodos(usuario);
        });
    }

}