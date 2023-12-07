package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
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
                throw new UsuarioException();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return usuario;

    }

    @Override
    public Usuario actualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario set activo=?,alta=?,email=?,nombre=? where id=?";

        try (
                Connection conn = DriverManager.getConnection(db_url);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {

            stmt.setInt(1, usuario.isActivo() ? 1 : 0);
            stmt.setString(2, usuario.getAlta().toString());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getNombre());
            stmt.setInt(5, usuario.getId());

            int rows = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return usuario;
    }

    @Override
    public boolean borrar(Usuario usuario) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id=?";

        try (
                Connection conn = DriverManager.getConnection(db_url);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setInt(1, usuario.getId());

            int rows = stmt.executeUpdate();
            System.out.println(rows);

            if (rows <= 0) {
                throw new UsuarioException();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return true;
    }


    @Override
    public Set<Usuario> obtenerPosiblesDestinatarios(Integer id, Integer max) throws SQLException {

        Set<Usuario> listADevolver = new HashSet<>();

         String sql = "SELECT * FROM usuario u WHERE 1";

         try (
                Connection conn = DriverManager.getConnection(db_url);
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
        ) {

            while (rs.next()) {
                listADevolver.add(new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("email"),
                         rs.getDate("alta").toLocalDate(),rs.getBoolean("activo")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }


        return listADevolver;
    }
}
