/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import modelo.MenuVO;
import modelo.Menu_DAO_IMP;
import modelo.Sugerir_Menu;
import modelo.Sugerir_Menu_DAO_IMP;

/**
 * FXML Controller class
 *
 * @author jeffr
 */
public class EstudianteMenuController implements Initializable {

    @FXML
    private JFXButton botonAgregarSUME;
Sugerir_Menu_DAO_IMP implementacionSugerirDAO=new Sugerir_Menu_DAO_IMP();
    @FXML
    private JFXTextArea textArea_SUME;
    @FXML
    private ChoiceBox<String> tipo_Menu_SUME;
    
    
    //Elementos buscar Menú
    @FXML
    private JFXTextField txtBuscar;
    @FXML
    private Label campoNombreBuscar;
    @FXML
    private Label campoDesBuscar;
    @FXML
    private Label campoTipoBuscar;
    @FXML
    private Label campoDiaBuscar;
    @FXML
    private Label campoPrecioBuscar;
    @FXML
    private JFXButton botonBuscar;
    private Menu_DAO_IMP implementacionBuscarDao = new Menu_DAO_IMP();
    private MenuVO menuBuscar = new MenuVO();
    //Termina lista de elementos buscar Menú
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList <String> listaTipo= FXCollections.observableArrayList("Desayuno","Merienda","Comida");
        tipo_Menu_SUME.setItems(listaTipo);
        ocultarDatos();
    }    
//Sugerir Menu
    @FXML
    private void evento_Agregar_SUME(ActionEvent event) {
        
          String nombreAux=textArea_SUME.getText();
          String tipoAux=tipo_Menu_SUME.getSelectionModel().getSelectedItem();
        if(textArea_SUME.getText().equals("") || tipo_Menu_SUME.getSelectionModel().getSelectedItem().equals("")){
       Alert alert =new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText("Alguno de los campos se encuentra vacio");
            alert.showAndWait();
        }else{
              try {
            Sugerir_Menu menu = new Sugerir_Menu();
            menu.setDescripcion(nombreAux);
            menu.setTipo(tipoAux);
          implementacionSugerirDAO.create(menu);
          Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alta Exitosa");
            alert.setHeaderText("Se ha agregado correctamente el producto!");
            alert.showAndWait();
            textArea_SUME.setText("");
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
    }
    
    
    //Metodos Buscar Menu
    @FXML
    void buscar(ActionEvent event) {
        String nombre = this.txtBuscar.getText();
        try{
            this.menuBuscar = this.implementacionBuscarDao.readNombre(nombre);
            if (this.menuBuscar.getNombre()!=""){
                this.campoNombreBuscar.setText(menuBuscar.getNombre());
                this.campoDesBuscar.setText(menuBuscar.getDescripcion());
                this.campoTipoBuscar.setText(menuBuscar.getTipo());
                this.campoDiaBuscar.setText(menuBuscar.getDia());
                this.campoPrecioBuscar.setText(Integer.toString(menuBuscar.getPrecio()));
            }else{
                this.txtBuscar.setText("Menu inexistente");
                this.ocultarDatos();
            }
        }catch (Exception e){
            System.out.println("Error al buscar menu "+ e);
        }
    }
    
    public void ocultarDatos(){
        this.campoNombreBuscar.setText("");
        this.campoDesBuscar.setText("");
        this.campoTipoBuscar.setText("");
        this.campoDiaBuscar.setText("");
        this.campoPrecioBuscar.setText("");
    }
    //Terminan metodos BuscarMenu
    
}
