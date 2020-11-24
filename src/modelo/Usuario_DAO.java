/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;

/**
 *
 * @author enrique
 */
public interface Usuario_DAO {
    
    public boolean create(UsuariosVO usuario) throws Exception;

    public List<UsuariosVO> readAll() throws Exception;
    
    public UsuariosVO read(String matricula)throws Exception;
    
    //public UsuariosVO search(String matricula)throws Exception;
    
    public boolean update(UsuariosVO usuario) throws Exception;
    
    public boolean bloqueo(UsuariosVO usuario) throws Exception;
    
    public boolean delete(UsuariosVO usuario) throws Exception; 
    
}
