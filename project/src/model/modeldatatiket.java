/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ayas
 */
public class modeldatatiket extends AbstractTableModel {
     List<datatiket> dt;
      public modeldatatiket(List<datatiket> dt){
        this.dt = dt; 
    }

    @Override
    public int getRowCount() {
      return dt.size();
    }

    @Override
    public int getColumnCount() {
        return 11;
    }

    public String getColumnName(int column){
        switch(column){
            case 0 :
                return "id Tiket";
            case 1 :
                return "Judul";
            case 2 :
                return "Tanggal";
            case 3 :
                return "Jam";
            case 4 :
                return "Jumlah Tiket";
            case 5 :
                return "Studio";
            case 6 :
                return "Kursi";
            case 7 :
                return "Bill";
            case 8 :
                return "MetodePembayaran";
            case 9 :
                return "CreatedAt";
            case 10 :
                return "Kasir";
            default : 
                return null;
        }
    }
    @Override
    public Object getValueAt(int row, int column) {
        switch(column){
            case 0 :
                return dt.get(row).getIdTiket();
            case 1 :
                return dt.get(row).getJudul();
            case 2 :
                return dt.get(row).getTanggal();
            case 3 :
                return dt.get(row).getJam();
            case 4 :
                return dt.get(row).getJumlahTiket();
            case 5 :
                return dt.get(row).getStudio();
            case 6 :
                return dt.get(row).getKursi();
            case 7 :
                return dt.get(row).getBill();
            case 8 :
                return dt.get(row).getMetodePembayaran();
            case 9 :
                return dt.get(row).getCreatedat();
            case 10 :
                return dt.get(row).getNamapegawai();
            default : 
                return null;
        }
    }
}
