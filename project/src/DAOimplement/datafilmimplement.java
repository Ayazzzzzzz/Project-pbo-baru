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
public interface datafilmimplement {
  public void insert(datafilm f);
  public void update(datafilm f);
  public void delete(int id);
  public List<datafilm> getAll();
}