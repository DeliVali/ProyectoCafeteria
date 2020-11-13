/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList <String> listaTipo= FXCollections.observableArrayList("Desayuno","Merienda","Comida");
        tipo_Menu_SUME.setItems(listaTipo);
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
    
    
    
    
}
