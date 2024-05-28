/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOdatafilm;

import DAOImplement.datapegawaiimplement;
import java.sql.*;
import koneksi.connector;
import model.*;

public class datapegawaiDAO implements datapegawaiimplement {

    Connection connection;

    final String query = "SELECT * FROM pegawai WHERE nama = ? AND nim = ?";

    public datapegawaiDAO() {
        connection = connector.connection();
    }

     @Override
    public datapegawai ceklogin(datapegawai p) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, p.getUsername());
            statement.setString(2, p.getPass());
            rs = statement.executeQuery();

            if (rs.next()) {
                p.setIdPegawai(rs.getInt("idPegawai"));
                return p;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}