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
public class Sugerir_Menu_DAO_IMP {
    
    
      public boolean create (Sugerir_Menu menu) throws Exception{
        boolean created = false;
        Statement stm;
        Connection con;
        String sql = "INSERT INTO sugerir_menu values(NULL,'"+menu.getDescripcion()+"', '"+menu.getTipo()+"');";
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
