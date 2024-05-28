/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOImplement;
import java.util.List;
import model.*;
/**
 *
 * @author Ayas
 */
public interface datatiketimplement {
    public void insert(datatiket t);
    public void delete(int id);
    public List<datatiket> getAll();    
    public datafilm searchFilmById(int filmId);
    public datatiket searchPegawai(int idPegawai);
    public datatiket hitungmasukan();
    public datatiket hitungtiket();
}
