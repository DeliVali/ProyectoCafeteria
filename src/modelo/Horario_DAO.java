package modelo;

import java.util.List;

public interface Horario_DAO {
    
    public boolean create(Horario_Atencion_VO horario_atencion) throws Exception;
    public boolean update(Horario_Atencion_VO horario_atencion) throws Exception;
    public List<Horario_Atencion_VO> readAll() throws Exception;
    public Horario_Atencion_VO read(String id) throws Exception;
}


