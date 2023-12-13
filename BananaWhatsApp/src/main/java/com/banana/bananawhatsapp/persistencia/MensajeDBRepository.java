package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.exceptions.MensajeException;
import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.sql.SQLException;
import java.util.List;
import java.sql.*;

@Setter
@Getter
public class MensajeDBRepository implements IMensajeRepository{

    private String db_url;
    @Override
    public Mensaje crear(Mensaje mensaje) throws SQLException {
         String sql = "INSERT INTO mensaje values (NULL,?,?,?,?)";


        try (
                Connection conn = DriverManager.getConnection(db_url);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {

            stmt.setString(1, mensaje.getCuerpo());
            stmt.setString(2, mensaje.getFecha().toString());
            stmt.setInt(   3, mensaje.getRemitente().getId());
            stmt.setInt(   4, mensaje.getDestinatario().getId());


            int rows = stmt.executeUpdate();

            ResultSet genKeys = stmt.getGeneratedKeys();
            if (genKeys.next()) {
                mensaje.setId(genKeys.getInt(1));
            } else {
                throw new MensajeException();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return mensaje;
    }

    @Override
    public List<Mensaje> obtener(Usuario usuario) throws SQLException {
        return null;
    }

    @Override
    public boolean borrarTodos(Usuario usuario) throws SQLException {
        return false;
    }
}
