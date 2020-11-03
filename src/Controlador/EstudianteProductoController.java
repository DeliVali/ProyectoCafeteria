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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import modelo.Sugerir_Pro;
import modelo.Sugerir_Pro_DAO_IMP;

/**
 * FXML Controller class
 *
 * @author jeffr
 */
public class EstudianteProductoController implements Initializable {

    @FXML
    private JFXTextField textNombre;
    @FXML
    private JFXTextArea textDesc;
    @FXML
    private JFXButton botonAgregar;
    Sugerir_Pro_DAO_IMP implementacionSugerirDAO=new Sugerir_Pro_DAO_IMP();
    String nombreAux="";
    

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    //Metodos
    @FXML
    private void eventoAgregar(ActionEvent event) {
        nombreAux=textNombre.getText();
        if(textNombre.getText().equals("") || textDesc.getText().equals("")){
       Alert alert =new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText("Alguno de los campos se encuentra vacio");
            alert.showAndWait();
        }else{
              try {
            Sugerir_Pro producto = new Sugerir_Pro();
            producto.setNombre(nombreAux);
            System.out.println(producto.toString());
          implementacionSugerirDAO.create(producto);
          Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alta Exitosa");
            alert.setHeaderText("Se ha agregado correctamente el producto!");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
            
    }
    
    
    
}
