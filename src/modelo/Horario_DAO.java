/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;

/**
 *
 * @author abiga
 */
public interface Horario_DAO {
    public boolean update(Horario_Atencion_VO horario_atencion) throws Exception;
    public List<Horario_Atencion_VO> readAll() throws Exception;
    public Horario_Atencion_VO read(String id) throws Exception;
}
