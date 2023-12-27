package com.banana.bananawhatsapp.servicios;

import com.banana.bananawhatsapp.exceptions.MensajeException;
import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import com.banana.bananawhatsapp.persistencia.IMensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ServicioMensaje implements IServicioMensajeria{

    @Autowired
    private IMensajeRepository repoMensaje;



    @Override
    public Mensaje enviarMensaje(Usuario remitente, Usuario destinatario, String texto) throws UsuarioException, MensajeException {
       Mensaje mensaje=new Mensaje();

       mensaje.setRemitente(remitente);
       mensaje.setDestinatario(destinatario);
       mensaje.setCuerpo(texto);
       mensaje.setFecha(LocalDate.now());


       try{
           repoMensaje.crear(mensaje);
       }catch( SQLException e){
           e.printStackTrace();
           throw new MensajeException("Error al crear usuario");
       }
       return mensaje;
    }

    @Override
    public List<Mensaje> mostrarChatConUsuario(Usuario remitente, Usuario destinatario) throws UsuarioException, MensajeException {
        return null;
    }

    @Override
    public boolean borrarChatConUsuario(Usuario remitente, Usuario destinatario) throws UsuarioException, MensajeException {
        return false;
    }
}
