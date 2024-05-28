/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAOImplement.datapegawaiimplement;
import DAOdatafilm.datapegawaiDAO;
import model.*;
import project.login;

public class logincontroller {

    login frame;
    datapegawaiimplement implementpegawai;

    public logincontroller(login frame) {
        this.frame = frame;
        implementpegawai = new datapegawaiDAO();
    }

    public datapegawai ceklogin() {
        datapegawai dp = new datapegawai();
        dp.setUsername(frame.getUsername().getText());
        dp.setPass(frame.getPass().getText());
        return implementpegawai.ceklogin(dp);
    }
}
