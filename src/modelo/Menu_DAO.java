package modelo;
import java.util.List;

/**
 *
 * @author joel_
 */
public interface Menu_DAO {
    
    public boolean create(MenuVO menu) throws Exception;
    public List<MenuVO> readAll() throws Exception;
    public MenuVO read(int id) throws Exception;
    public MenuVO readNombre(String nombre) throws Exception;
    public boolean update(MenuVO menu) throws Exception;
    public boolean delete(MenuVO menu) throws Exception;
    
}


