package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joel_
 */
public class Menu_DAO_IMP implements Menu_DAO{

    @Override
    public boolean create(MenuVO menu) throws Exception {
        boolean created = false;
        Statement st;
        Connection conn;
        String sql = "INSERT INTO menu values(NULL, '"+ menu.getNombre()+"',"+"'"+ menu.getDescripcion()+"',"+
                "'"+ menu.getTipo()+"',"+"'"+ menu.getDia()+"',"+"'"+ menu.getPrecio()+"')";
        ConexionDB co = new ConexionDB();
        try{
            conn = co.conectarMySQL();
            st = conn.createStatement();
            st.execute(sql);
            created = true;
            st.close();
            conn.close();
        } catch(SQLException e){
            throw new Exception ("Error SQLException "+e.getMessage());
        } catch(NullPointerException e){
            throw new Exception("Error "+e.getMessage());
        } catch(Exception e){
            throw new Exception("Error "+e.getMessage());
        }
        return created;
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
        Connection connect = null;
        Statement stm = null;

        boolean actualizar = false;

        String sql = "UPDATE menu SET ID= '" + menu.getId() + "' , Nombre= '"+ menu.getNombre() + "' , Descripcion= '" 
                + menu.getDescripcion() + "' , Tipo= '" 
                + menu.getTipo() + "', Dia= '" + menu.getDia() + "', Precio= '"
                + menu.getPrecio() + "' WHERE ID= '" + menu.getId() + "' ;";
        
        try {
            connect = new ConexionDB().conectarMySQL();
            stm = connect.createStatement();
            actualizar = stm.execute(sql);    
        } catch (SQLException e) {
            try {
                throw new Exception("Error en update SQLException: " + e.getMessage());
            } catch (Exception ex) {
                e.printStackTrace();
            }
        } catch(Exception e){
            try {
                throw new Exception("Error en update Exception " + e.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(Menu_DAO_IMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return actualizar;
    }

    @Override
    public boolean delete(MenuVO menu) throws Exception {
        Connection con = null;
        Statement stm = null;
        
        boolean eliminar = false;
        
        String sql = "DELETE FROM menu WHERE ID =" + menu.getId();
        try{
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            eliminar = stm.execute(sql);
        } catch(SQLException e){
            throw new Exception ("Error en el delete SQLException " + e.getCause().toString());
          } catch (Exception e){
              throw new Exception ("Error en el delete Exception " + e.getMessage().toString());
          }
        return eliminar;
    }
    
}
