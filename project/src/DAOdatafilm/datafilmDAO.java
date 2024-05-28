/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOdatafilm;

import DAOImplement.datafilmimplement;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import koneksi.connector;
import model.*;

/**
 *
 * @author Ayas
 */
public class datafilmDAO implements datafilmimplement {

    Connection connection;

    final String select = "SELECT * FROM `film`";
    final String delete = "delete from  film where idFilm = ?";
    final String insert = "INSERT INTO `film` (`judul`, `genre`, `tahun`, `durasi`, `poster`) VALUES (?, ?, ?, ?, ?);"; //id gausadimasukin
    final String update = "update film set judul =?, genre = ?, tahun = ?, durasi = ?, poster = ? where idFilm = ?";

    public datafilmDAO() {
        connection = connector.connection();
    }

    @Override
    public void insert(datafilm f) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, f.getJudul());
            statement.setString(2, f.getGenre());
            statement.setString(3, f.getTahun());
            statement.setString(4, f.getDurasi());
            statement.setString(5, f.getPoster());
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                f.setIdfilm(rs.getInt(1));
            }
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void update(datafilm f) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, f.getJudul());
            statement.setString(2, f.getGenre());
            statement.setString(3, f.getTahun());
            statement.setString(4, f.getDurasi());
            statement.setString(5, f.getPoster());
            statement.setInt(6, f.getIdfilm());

            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int idfilm) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setInt(1, idfilm);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<datafilm> getAll() {
        List<datafilm> df = null;
        try {
            df = new ArrayList<datafilm>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                datafilm f = new datafilm();
                f.setIdfilm(rs.getInt("idFilm"));
                f.setJudul(rs.getString("judul"));
                f.setGenre(rs.getString("genre"));
                f.setTahun(rs.getString("tahun"));
                f.setDurasi(rs.getString("durasi"));
                f.setPoster(rs.getString("poster"));
                df.add(f);
            }

        } catch (SQLException ex) {

            Logger.getLogger(datafilm.class.getName()).log(Level.SEVERE, null, ex);
        }

        return df;

    }

}
