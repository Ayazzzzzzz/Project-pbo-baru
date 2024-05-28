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
public class modeldatafilm extends AbstractTableModel {
     List<datafilm> df;
      public modeldatafilm(List<datafilm> df){
        this.df = df; 
    }

    @Override
    public int getRowCount() {
      return df.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

     @Override
    public String getColumnName(int column){
        switch(column){
            case 0 :
                return "IdFilm";
            case 1 :
                return "Judul";
            case 2 :
                return "Genre";
            case 3 :
                return "Tahun";
            case 4 :
                return "Durasi";
            case 5 :
                return "Poster";
            default : 
                return null;
        }
    }
    @Override
    public Object getValueAt(int row, int column) {
        switch(column){
            case 0 :
                return df.get(row).getIdfilm();
            case 1 :
                return df.get(row).getJudul();
            case 2 :
                return df.get(row).getGenre();
            case 3 :
                return df.get(row).getTahun();
            case 4 :
                return df.get(row).getDurasi();
            case 5 :
                return df.get(row).getPoster();
            default : 
                return null;
        }
    }
}
