/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.util.List;
import DAOdatafilm.datafilmDAO;
import DAOImplement.datafilmimplement;
import model.*;
import project.*;

/**
 *
 * @author Ayas
 */
public class filmcontroller {
    utama frame;
    crudfilm frame2;
    datafilmimplement implementfilm;
    List<datafilm> df;
    
    public filmcontroller(utama frame) {
        this.frame = frame;
        implementfilm = new datafilmDAO();
        df = implementfilm.getAll();
    }
    
    public filmcontroller(crudfilm frame2) {
        this.frame2 = frame2;
        implementfilm = new datafilmDAO();
        df = implementfilm.getAll();
    }

    
    public void isitabel(){
        df = implementfilm.getAll();
        modeldatafilm mp = new modeldatafilm(df);
        frame.getTabelFilm().setModel(mp); 
    }
    
    public void isitabel2(){
        df = implementfilm.getAll();
        modeldatafilm mp = new modeldatafilm(df);
        frame2.getTabelFilm().setModel(mp); 
    }
    
    public void delete(){
        int selectedRow = frame2.getTabelFilm().getSelectedRow();
        int id = Integer.parseInt(frame2.getId().getText());
        implementfilm.delete(id);
    
    }
    
    public void insert(){
        datafilm df = new datafilm();
        df.setJudul(frame2.getJudul().getText());
        df.setGenre(frame2.getGenre().getText());
        df.setTahun(frame2.getTahun1().getText());
        df.setDurasi(frame2.getDurasi().getText());
        df.setPoster(frame2.getPoster().getText());
        implementfilm.insert(df);
    }
    
    public void update(){
        datafilm df = new datafilm();
        df.setJudul(frame2.getJudul().getText());
        df.setGenre(frame2.getGenre().getText());
        df.setTahun(frame2.getTahun1().getText());
        df.setDurasi(frame2.getDurasi().getText());
        df.setPoster(frame2.getPoster().getText());
        df.setIdfilm(Integer.parseInt(frame2.getId().getText()));
        implementfilm.update(df);
    }
    

}
