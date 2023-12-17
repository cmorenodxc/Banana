package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface IMensajeRepository {
    public Mensaje crear(Mensaje mensaje) throws SQLException;

    public List<Mensaje> obtener(Usuario usuario) throws Exception;

    public boolean borrarTodos(Usuario usuario) throws SQLException;
}
