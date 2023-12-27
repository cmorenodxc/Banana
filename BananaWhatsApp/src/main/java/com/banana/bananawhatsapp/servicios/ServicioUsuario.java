package com.banana.bananawhatsapp.servicios;

import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Usuario;
import com.banana.bananawhatsapp.persistencia.IUsuarioRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class ServicioUsuario implements IServicioUsuarios{


    @Autowired
    private IUsuarioRepository repoUsuario;


    @Override
    public Usuario crearUsuario(Usuario usuario) throws UsuarioException {

       try{
           repoUsuario.crear(usuario);
       }catch(SQLException e){
           e.printStackTrace();
           throw new UsuarioException("Error al crear usuario");
       }
        return usuario;
    }


    @Override
    public boolean borrarUsuario(Usuario usuario) throws UsuarioException {
        boolean ok=false;

       try{
           ok = repoUsuario.borrar(usuario);
       }catch(SQLException e){
           e.printStackTrace();
           throw new UsuarioException("Error al borrar usuario");
       }


        return ok;
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) throws UsuarioException {
       try{
           repoUsuario.actualizar(usuario);
       }catch(SQLException e){
           e.printStackTrace();
           throw new UsuarioException("Error al actualizar el usuario");
       }
        return usuario;
    }

    @Override
    public Usuario obtenerPosiblesDesinatarios(Usuario usuario, int max) throws UsuarioException {
        return null;
    }
}
