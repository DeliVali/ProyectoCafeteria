package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joel_
 */
public class Menu_DAO_IMP implements Menu_DAO{

    @Override
    public boolean create(MenuVO menu) throws Exception {
        //Abigail 
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MenuVO> readAll() throws Exception {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM menu order by id";
        
        List<MenuVO> lista = new ArrayList<MenuVO>();
        
        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            
            while (rs.next()) {
                
                MenuVO m = new MenuVO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
                
                lista.add(m);
            }
            stm.close();
            rs.close();
            con.close();
        } catch (Exception e) {
           //System.out.println("Error al buscar usuario");
           e.printStackTrace();
        }
        
        return lista;
    }

    @Override
    public MenuVO read(int id) throws Exception {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM menu WHERE ID =" + id;
        
        MenuVO menu = new MenuVO();
        
        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            
            while (rs.next()) {
                menu.setId(rs.getInt(1));
                menu.setNombre(rs.getString(2));
                menu.setDescripcion(rs.getString(3));
                menu.setTipo(rs.getString(4));
                menu.setDia(rs.getString(5));
                menu.setPrecio(rs.getInt(6));
            }
            stm.close();
            rs.close();
            con.close();
        } catch (Exception e) {
           //System.out.println("Error al buscar usuario");
           e.printStackTrace();
        }
        
        return menu;
    }

    @Override
    public MenuVO readNombre(String nombre) throws Exception {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM menu WHERE Nombre = '" + nombre + "'";
        
        MenuVO menu = new MenuVO();
        
        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            
            while (rs.next()) {
                menu.setId(rs.getInt(1));
                menu.setNombre(rs.getString(2));
                menu.setDescripcion(rs.getString(3));
                menu.setTipo(rs.getString(4));
                menu.setDia(rs.getString(5));
                menu.setPrecio(rs.getInt(6));
            }
            stm.close();
            rs.close();
            con.close();
        } catch (Exception e) {
           //System.out.println("Error al buscar usuario");
           e.printStackTrace();
        }
        
        return menu;
    }

    @Override
    public boolean update(MenuVO menu) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(MenuVO menu) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
