package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.exceptions.MensajeException;
import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.sql.SQLException;
import java.util.ArrayList;
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
    public List<Mensaje> obtener(Usuario usuario) throws Exception {
        List<Mensaje> listADevolver = new ArrayList<>();

        String sql = "SELECT * FROM mensaje u WHERE from_user=?";


        try (
                Connection conn = DriverManager.getConnection(db_url);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ) {

            stmt.setString(1, usuario.getId().toString());
            ResultSet rs = stmt.executeQuery();

             if (!rs.next()) {
                  throw new MensajeException();
             }

            while (rs.next()) {

                Mensaje m1= new Mensaje();
                Usuario remitente = new Usuario(),destinatario= new Usuario();

                m1.setId(rs.getInt("id"));
                m1.setCuerpo(rs.getString("cuerpo"));
                m1.setFecha(rs.getDate("fecha").toLocalDate());

                remitente.setId(rs.getInt("from_user"));
                m1.setRemitente(remitente);

                destinatario.setId(rs.getInt("to_user"));
                m1.setDestinatario(destinatario);

                listADevolver.add(m1);



            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e);
        }

        return listADevolver;

    }

    @Override
    public boolean borrarTodos(Usuario usuario) throws SQLException {
      String sql = "DELETE FROM mensaje WHERE from_user=?";

        try (
                Connection conn = DriverManager.getConnection(db_url);
                PreparedStatement stmt = conn.prepareStatement(sql);
                ) {
            stmt.setString(1, usuario.getId().toString());

            int rows = stmt.executeUpdate();
            System.out.println(rows);

           if (rows <= 0) {
                throw new MensajeException();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return true;
    }

}
