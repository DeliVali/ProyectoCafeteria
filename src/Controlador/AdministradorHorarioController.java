 /* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Horario_Atencion_VO;
import modelo.Horario_DAO_Imp;
import modelo.Usuarios_DAO_Imp;

/**
 * FXML Controller class
 *
 * @author jeffr
 */
public class AdministradorHorarioController implements Initializable {
    
    /*------------ Mostrar horarios ------------*/
    @FXML
    private TableView<Horario_Atencion_VO> tablaHorarios;

    @FXML
    private TableColumn<Horario_Atencion_VO, String> columnaId;

    @FXML
    private TableColumn<Horario_Atencion_VO, String> columnaDia;

    @FXML
    private TableColumn<Horario_Atencion_VO, Date> columnaHoraApertura;

    @FXML
    private TableColumn<Horario_Atencion_VO, Date> columnaHoraCierre;
    
    // ----------------------------------------
    private ObservableList<Horario_Atencion_VO> listaDeHorarios;
    
    /*------------ Editar horario ------------*/
    @FXML
    private Button btnBuscarHorario;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField txtBuscarHorario;

    @FXML
    private TextField txtHoraApertura;

    @FXML
    private TextField txtHoraCierre;

    @FXML
    private TextField txtDiaHorario;
    
    
    private Horario_Atencion_VO horario_atencion =new Horario_Atencion_VO();
    
    private Horario_DAO_Imp implementacionDAO = new Horario_DAO_Imp();
    
    private String id;
    
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
        this.columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.columnaDia.setCellValueFactory(new PropertyValueFactory<>("dia"));
        this.columnaHoraApertura.setCellValueFactory(new PropertyValueFactory<>("hora_apertura"));        
        this.columnaHoraCierre.setCellValueFactory(new PropertyValueFactory<>("hora_cierre"));
        this.tablaHorarios.setItems(listaDeHorarios);
    }
    
    void buscarHorario(ActionEvent event) throws Exception {
        id = txtBuscarHorario.getText();
        Horario_Atencion_VO horario_atencion = new Horario_Atencion_VO();
        horario_atencion = implementacionDAO.read(id);
        
        this.txtDiaHorario.setText(horario_atencion.getDia());
        this.txtHoraApertura.setText(horario_atencion.getHoraApertura().toString());
        this.txtHoraCierre.setText(horario_atencion.getHoraCierre().toString());
    }
    
    void guardarEdicionHorario(ActionEvent event) {
        this.horario_atencion.setDia((this.txtDiaHorario.getText()));
        //this.horario_atencion.setHoraApertura((this.txtHoraApertura.getText()));
        //this.horario_atencion.setHoraCierre((this.txtHoraCierre.getText()));
        
         try {       
             this.implementacionDAO.update(horario_atencion);
         } catch (Exception ex) {
             System.out.println("Error al editar el horario");
         }
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