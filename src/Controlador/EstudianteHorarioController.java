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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Horario_Atencion_VO;
import modelo.Horario_DAO_Imp;

/**
 * FXML Controller class
 *
 * @author jeffr
 */
public class EstudianteHorarioController implements Initializable {
    
    @FXML
    private TableView<Horario_Atencion_VO> tablaHorariosEst;
    
    @FXML
    private TableColumn<Horario_Atencion_VO, String> colDiaEst;

    @FXML
    private TableColumn<Horario_Atencion_VO, String> colHoraAperEst;

    @FXML
    private TableColumn<Horario_Atencion_VO, String> colHoraCierreEst;
    

    private ObservableList<Horario_Atencion_VO> listaDeHorarios;
    private Horario_Atencion_VO horario_atencion =new Horario_Atencion_VO();
    private Horario_DAO_Imp implementacionDAO = new Horario_DAO_Imp();
    
    
    //Mostrar horario
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
    
    public void colocarHorariosTabla(){
        this.obtenerHorarios();
        this.colDiaEst.setCellValueFactory(new PropertyValueFactory<>("dia"));
        this.colHoraAperEst.setCellValueFactory(new PropertyValueFactory<>("horaApertura"));        
        this.colHoraCierreEst.setCellValueFactory(new PropertyValueFactory<>("horaCierre"));
        this.tablaHorariosEst.setItems(listaDeHorarios);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.implementacionDAO = new Horario_DAO_Imp();
        this.listaDeHorarios = FXCollections.observableArrayList();
        this.colocarHorariosTabla();
    }    
    
}
