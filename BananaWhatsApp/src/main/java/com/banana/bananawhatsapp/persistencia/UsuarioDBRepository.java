package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Usuario;

import java.sql.*;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioDBRepository implements IUsuarioRepository{



    private String db_url;

    @Override
    public Usuario crear(Usuario usuario) throws SQLException {
         String sql = "INSERT INTO usuario values (NULL,?,?,?,?)";

        try (
                Connection conn = DriverManager.getConnection(db_url);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {

            stmt.setInt(1, usuario.isActivo() ? 1 : 0);
            stmt.setString(2, usuario.getAlta().toString());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getNombre());


            int rows = stmt.executeUpdate();

            ResultSet genKeys = stmt.getGeneratedKeys();
            if (genKeys.next()) {
                usuario.setId(genKeys.getInt(1));
            } else {
                throw new SQLException("Usuario creado erroneamente!!!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new UsuarioException();
        }

        return usuario;

    }

    @Override
    public Usuario actualizar(Usuario usuario) throws SQLException {
        return null;
    }

    @Override
    public boolean borrar(Usuario usuario) throws SQLException {
        return false;
    }

    @Override
    public Set<Usuario> obtenerPosiblesDestinatarios(Integer id, Integer max) throws SQLException {
        return null;
    }
}
