/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jeffr
 */
public class Ventana_EstudiantesFXMLController implements Initializable {

    @FXML
    private BorderPane borderPane;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void boton_Pro(MouseEvent event) {
        cargarVentanas("/VentanaAux/EstudianteProducto");
    }

    @FXML
    private void boton_Horario(MouseEvent event) {
        cargarVentanas("/VentanaAux/EstudianteHorario");
    }

    @FXML
    private void boton_Menu(MouseEvent event) {
        cargarVentanas("/VentanaAux/EstudianteMenu");
        
    }

    private void cargarVentanas(String ui){
        Parent root = null;
        try {
           root= FXMLLoader.load(getClass().getResource(ui+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Ventana_EstudiantesFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderPane.setCenter(root);
    }
    
    
    @FXML
    private void boton_Salir(MouseEvent event) {
        cerrarVentana(event);
        
    }
    
            public void cerrarVentana(MouseEvent event){
        Node  source = (Node)  event.getSource(); 
    Stage stage  = (Stage) source.getScene().getWindow();
    stage.close();
    }
    
    
}
