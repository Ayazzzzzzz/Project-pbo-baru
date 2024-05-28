/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOdatafilm;
import DAOImplement.datatiketimplement;
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
public class datatiketDAO implements datatiketimplement {

    Connection connection;

    final String select = "SELECT * FROM `tiket` INNER JOIN `film` ON tiket.idFilm = film.idFilm INNER JOIN `pegawai` ON tiket.idPegawai = pegawai.idPegawai";
    final String delete = "delete from `tiket` where idTiket = ?";
    final String insert = "INSERT INTO `tiket` (`idFilm`, `idPegawai`, `tanggal`, `jam`, `jumlahTiket`, `studio`, `kursi`, `bill`, `metodePembayaran`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);"; //id gausadimasukin
    String searchFilm = "SELECT * FROM film WHERE idFilm = ?";
    String searchPegawai = "SELECT * FROM pegawai WHERE idPegawai = ?";
    String hitungduit = "SELECT Sum(bill) FROM `tiket`;";
    String hitungtiket = "SELECT sum(jumlahTiket) from tiket;";
    
    public datatiketDAO() {
        connection = connector.connection();
    }

    @Override
    public void insert(datatiket t) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, t.getIdFilm());
            statement.setInt(2, t.getIdPegawai());
            statement.setString(3, t.getTanggal());
            statement.setString(4, t.getJam());
            statement.setInt(5, t.getJumlahTiket());
            statement.setString(6, t.getStudio());
            statement.setString(7, t.getKursi());
            statement.setInt(8, t.getBill());
            statement.setString(9, t.getMetodePembayaran());

            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                t.setIdTiket(rs.getInt(1));
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

    @Override
    public void delete(int idTiket) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setInt(1, idTiket);
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
    public List<datatiket> getAll() {
        List<datatiket> dt = null;
        try {
            dt = new ArrayList<datatiket>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                datatiket t = new datatiket();
                t.setIdTiket(rs.getInt("tiket.idTiket"));
                t.setJudul(rs.getString("film.judul"));
                t.setNamapegawai(rs.getString("pegawai.nama"));
                t.setTanggal(rs.getString("tiket.tanggal"));
                t.setJam(rs.getString("tiket.jam"));
                t.setJumlahTiket(rs.getInt("tiket.jumlahTiket"));
                t.setStudio(rs.getString("tiket.studio"));
                t.setKursi(rs.getString("tiket.kursi"));
                t.setCreatedat(rs.getString("tiket.createdAt"));
                t.setBill(rs.getInt("tiket.bill"));
                t.setMetodePembayaran(rs.getString("tiket.metodePembayaran"));
                dt.add(t);
            }

        } catch (SQLException ex) {

            Logger.getLogger(datafilm.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dt;

    }

    @Override
    public datafilm searchFilmById(int filmId) {
        datafilm df = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(searchFilm);

            statement.setInt(1, filmId); // Mengisi parameter query dengan idfilm

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                df = new datafilm();
                df.setIdfilm(rs.getInt("idFilm"));
                df.setJudul(rs.getString("judul"));
                df.setGenre(rs.getString("genre"));
                df.setTahun(rs.getString("tahun"));
                df.setDurasi(rs.getString("durasi"));
                df.setPoster(rs.getString("poster"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(datafilm.class.getName()).log(Level.SEVERE, null, ex);
        }
        //   return df; 
        return df;

    }

    @Override
    public datatiket searchPegawai(int idPegawai) { // Changed return type to datatiket
        PreparedStatement statement = null;
        datatiket dt = null; // Create an instance of datatiket to store the result
        try {
            statement = connection.prepareStatement(searchPegawai);
            statement.setInt(1, idPegawai); // Fill the query parameter
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                dt = new datatiket(); // Initialize datatiket object
                dt.setIdPegawai(idPegawai); // Set idPegawai
                dt.setNamapegawai(rs.getString("nama")); // Set nama
                // Set other properties as needed
            }
        } catch (SQLException ex) {
            Logger.getLogger(datatiketDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(datatiketDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dt; // Return the datatiket object with pegawai data
    }
    
    public datatiket hitungmasukan() {
        PreparedStatement statement = null;
        datatiket dt = null;
    try {
        statement = connection.prepareStatement(hitungduit);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            int totalBill = rs.getInt(1); // mendapatkan total bill dari hasil query
            dt = new datatiket();
            dt.setTotalpendapatan(totalBill);     
        }
    } catch (SQLException ex) {
        Logger.getLogger(datatiketDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(datatiketDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    return dt;
 }
    

    public datatiket hitungtiket() {
        PreparedStatement statement = null;
         datatiket dt = null;
    try {
        statement = connection.prepareStatement(hitungtiket);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            int totalTiket = rs.getInt(1); 
            dt = new datatiket();
            dt.setTotalTiket(totalTiket);
        }
    } catch (SQLException ex) {
        Logger.getLogger(datatiketDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(datatiketDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        return dt;
    }
}
