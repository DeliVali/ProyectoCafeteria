package modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Producto_DAO_IMP {
    
    public boolean create(ProductoVO producto) throws Exception{
        boolean created = false;
        Statement st;
        Connection conn;
        String sql = "INSERT INTO producto values(NULL, '"+producto.getNombre()+"',"+"'"+producto.getDescripcion()+"',"+
                "'"+producto.getCantidad()+"',"+"'"+producto.getPrecio()+"')";
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
}
