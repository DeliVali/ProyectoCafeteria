/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jeffr
 */
public class Sugerir_Pro_DAO_IMP {
    
     public boolean create (Sugerir_Pro producto) throws Exception{
        boolean created = false;
        Statement stm;
        Connection con;
        String sql = "INSERT INTO sugerir_producto values(NULL,'"+producto.getNombre()+"');";
        ConexionDB cc = new ConexionDB();
        try {
            con = cc.conectarMySQL();
            stm = con.createStatement();
            stm.execute(sql);
            created = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
           throw new Exception ("Error en create SQLException "+e.getMessage());
        } catch (NullPointerException e){
            throw new Exception("Error en create objeto null "+e.getMessage());
        }catch (Exception e){
            throw new Exception("Error en create "+e.getMessage());
        }
        return created;
    }
    
    
}
