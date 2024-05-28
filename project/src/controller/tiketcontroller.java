/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.util.List;
import DAOdatafilm.datatiketDAO;
import DAOImplement.datatiketimplement;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import model.*;
import project.*;
/**
 *
 * @author Ayas
 */
public class tiketcontroller {
    tampiltiket frame;
    pesantiket frame2;
    datatiketimplement implementtiket;
    List<datatiket> df;
    
    public tiketcontroller(tampiltiket frame) {
        this.frame = frame;
        implementtiket = new datatiketDAO();
        df = implementtiket.getAll();
    }
    
    public tiketcontroller(pesantiket frame2) {
        this.frame2 = frame2;
        implementtiket = new datatiketDAO();
    }
    
    public void insert() {
        datatiket dt = new datatiket();
        
        //field id Pegawai
        dt.setIdPegawai(Integer.parseInt(frame2.getIdpegawai().getText()));

        //field id film
        dt.setIdFilm(Integer.parseInt(frame2.getId().getText()));

        //field judul
        dt.setJudul(frame2.getJudul4().getText());
        
        //field genre
        dt.setGenre(frame2.getGenre().getText());
        
        //field tahun
        dt.setTahun(frame2.getTahun().getText());
        
        //field durasi
        dt.setDurasi(frame2.getDurasi().getText());
        
        //field tanggal
        dt.setTanggal(frame2.getTanggal().getText());
        
        //field jam
        dt.setJam(frame2.getJam().getText());
        
        //field jumlah tiket
        int jumlahtiket = Integer.parseInt(frame2.getJumlahtiket().getText());
        dt.setJumlahTiket(jumlahtiket);
        
        //field studio
        dt.setStudio(frame2.getStudio().getText());
        
        //field kursi
        dt.setKursi(frame2.getKursi().getText());
        
        //field metode
        dt.setMetodePembayaran(frame2.getMetode().getText());
        
        String studio = frame2.getStudio().getText();
        int harga = 0;
        
        if(null != studio)
            switch (studio) {
            case "2D":
                harga = 35000;
                break;
            case "3D":
                harga = 50000;
                break;
            case "4D":
                harga = 70000;
                break;
            default:
                break;
        }

        //menghitung nilai rating
        int bill = jumlahtiket * harga;
        dt.setBill(bill);

        implementtiket.insert(dt);
    }
    
    public void getDataFilm() {
        // Lakukan pencarian data film berdasarkan idfilm
        datafilm df = implementtiket.searchFilmById(frame2.filmId);
        
        // Isi text field dengan data dari objek datafilm
        if (df != null) {
            frame2.getJudul4().setText(df.getJudul());
            frame2.getGenre().setText(df.getGenre());
            frame2.getTahun().setText(df.getTahun());
            frame2.getDurasi().setText(df.getDurasi());
            String urlGambar = df.getPoster();
    try {
        java.net.URL url = new java.net.URL(urlGambar);
        BufferedImage img = ImageIO.read(url);
        
        if (img != null) {
            // Scale the image to fit the JLabel
            Image scaledImg = img.getScaledInstance(frame2.getPoster().getWidth(), frame2.getPoster().getHeight(), Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(scaledImg);
            frame2.getPoster().setIcon(imageIcon);
        } else {
            System.out.println("Image could not be loaded.");
        }
    } catch (MalformedURLException e) {
        System.err.println("URL is malformed: " + e.getMessage());
    } catch (IOException e) {
        System.err.println("IOException while loading image: " + e.getMessage());
    }
        }
    }
    
    public void getNamaPegawai() {
        // Lakukan pencarian data film berdasarkan idfilm
        datatiket dt = implementtiket.searchPegawai(frame2.idPegawai);
        
        // Isi text field dengan data dari objek datafilm
        if (dt != null) {
             frame2.getPegawai().setText(dt.getNamapegawai());

        }
    }

    
    public void isitabeltiket(){
        df = implementtiket.getAll();
        modeldatatiket mp = new modeldatatiket(df);
        frame.getTabeltiket().setModel(mp); 
    }
    
    public void hitungmasukan(){
        datatiket dt = implementtiket.hitungmasukan();
        frame.getUang().setText(String.valueOf(dt.getTotalpendapatan()));
    }
    
    public void hitungtiket(){
        datatiket dt = implementtiket.hitungtiket();
        frame.getTransaksi().setText(String.valueOf(dt.getTotalTiket()));
    }
}
