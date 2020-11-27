/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author enrique
 */
public class Usuarios_DAO_Imp implements Usuario_DAO{
    
    @Override
    public boolean create(UsuariosVO usuario) throws Exception {
        return false;
    
    }
      
    
    
       
    @Override
    public boolean update(UsuariosVO usuario) throws Exception{
        Connection connect = null;
        Statement stm = null;

        boolean actualizar = false;

        String sql = "UPDATE usuario SET Matricula= '" + usuario.getMatricula() +"' ,Nombre= '" 
                + usuario.getNombre() + "' , Pass= '" + usuario.getPass() + "', Tipo = " + usuario.isTipo() +  " , Bloqueo= " 
                + usuario.isBloqueo() + " WHERE Matricula= '" + usuario.getMatricula() +"'  ";
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
                Logger.getLogger(Usuarios_DAO_Imp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return actualizar;
    
    }
    
    
    @Override
    public boolean bloqueo(String matricula) throws Exception{
        Connection connect = null;
        Statement stm = null;
        boolean bloqueado = true;

        boolean bloqueo = false;

        String sql = "UPDATE usuario SET Bloqueo= " 
                + bloqueado + " WHERE Matricula= '" + matricula +"'  ";
        try {
            connect = new ConexionDB().conectarMySQL();
            stm = connect.createStatement();
            bloqueo = stm.execute(sql);    
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
                Logger.getLogger(Usuarios_DAO_Imp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return bloqueo;
    
    }
    
    @Override
    public boolean desbloqueo(String matricula) throws Exception{
        Connection connect = null;
        Statement stm = null;
       boolean bloqueado = false;

        boolean bloqueo = false;

        String sql = "UPDATE usuario SET Bloqueo= " 
                + bloqueado + " WHERE Matricula= '" + matricula +"'  ";
        try {
            connect = new ConexionDB().conectarMySQL();
            stm = connect.createStatement();
            bloqueo = stm.execute(sql);    
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
                Logger.getLogger(Usuarios_DAO_Imp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return bloqueo;
    
    }
    
    
   

    @Override
    public List<UsuariosVO> readAll() throws Exception {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM usuario";

        List<UsuariosVO> listaUsuarios = new ArrayList<UsuariosVO>();

        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                UsuariosVO c = new UsuariosVO(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getBoolean(4),
                        rs.getBoolean(5));
                listaUsuarios.add(c);
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            throw new Exception("Error en readAll SQLException: " + e.getCause().toString());
        } catch(Exception e){
            throw new Exception("Error en readAll: " + e.getCause().toString());
        }

        return listaUsuarios;
    }

    @Override
    public boolean delete(UsuariosVO usuario) throws Exception{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UsuariosVO read(String matricula) throws Exception {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM usuario WHERE Matricula ='" + matricula + "'";

        UsuariosVO usuario = new UsuariosVO();

        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            
            while (rs.next()) {
                usuario.setMatricula(rs.getString(1));
                usuario.setNombre(rs.getString(2));
                usuario.setPass(rs.getString(3));
                usuario.setTipo(rs.getBoolean(4));
                usuario.setBloqueo(rs.getBoolean(5));
            }
            stm.close();
            rs.close();
            con.close();
        } catch (Exception e) {
           //System.out.println("Error al buscar usuario");
           e.printStackTrace();
        }
        return usuario;      
    }
}
