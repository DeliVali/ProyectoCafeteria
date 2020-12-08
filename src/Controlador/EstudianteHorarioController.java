/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import modelo.Horario_Atencion_VO;
import modelo.Horario_DAO_Imp;

/**
 * FXML Controller class
 *
 * @author jeffr
 */
public class EstudianteHorarioController implements Initializable {

    private ObservableList<Horario_Atencion_VO> listaDeHorarios;
    
    private Horario_Atencion_VO horario_atencion =new Horario_Atencion_VO();
    

    
    private ObservableList<Horario_Atencion_VO> listaDeHorarios;
    private Horario_Atencion_VO horario_atencion =new Horario_Atencion_VO();

    private Horario_DAO_Imp implementacionDAO = new Horario_DAO_Imp();
    
    public void obtenerHorarios(){
        List listaConsulta = null;
        try{
           listaConsulta = implementacionDAO.readAll();
        }catch(Exception e){
            System.out.println("Error al leer los horarios");
        }
        Iterator it = listaConsulta.iterator();
        listaDeHorarios.clear();
        while (it.hasNext()){
            listaDeHorarios.add((Horario_Atencion_VO)it.next());
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
