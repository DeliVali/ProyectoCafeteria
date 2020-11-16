package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    
    public List<ProductoVO> readAll() throws Exception{
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "select * from producto order by ID";
        
        List<ProductoVO> listaProductos = new ArrayList<ProductoVO>();
        
        try{
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                ProductoVO c = new ProductoVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
                listaProductos.add(c);
            }
            stm.close();
            rs.close();
            con.close();
        } catch(SQLException e){
            throw new Exception ("Error en readAll SQLException" + e.getCause().toString());
        } catch(Exception e){
            throw new Exception ("Error en readAll Exceotion " + e.getMessage().toString());
        }
        
        return listaProductos;
    
    }
    
    
    
    public ProductoVO read(String nombre) throws Exception {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM producto WHERE Nombre ='" + nombre + "'";

        ProductoVO producto = new ProductoVO();

        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            
            while (rs.next()) {
                producto.setId(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setDescripcion(rs.getString(3));
                producto.setCantidad(rs.getInt(4));
                producto.setPrecio(rs.getInt(5));
            }
            stm.close();
            rs.close();
            con.close();
        } catch (Exception e) {
           e.printStackTrace();
        }
        return producto;      
    }
    
    public boolean reservar(int  idProducto, String matricula) throws Exception{
       
        boolean reservado = false;
        Statement st;
        Connection conn;
        String sql = "INSERT INTO pedido values(NULL, '"+ matricula +"',"+"'"+ idProducto +"')";
        ConexionDB co = new ConexionDB();
        try{
            conn = co.conectarMySQL();
            st = conn.createStatement();
            st.execute(sql);
            reservado = true;
            st.close();
            conn.close();
        } catch(SQLException e){
            throw new Exception ("Error SQLException "+e.getMessage());
        } catch(NullPointerException e){
            throw new Exception("Error "+e.getMessage());
        } catch(Exception e){
            throw new Exception("Error "+e.getMessage());
        }
        
        return reservado;
    }
    
}
