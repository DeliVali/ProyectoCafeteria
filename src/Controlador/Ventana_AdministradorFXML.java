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
import javafx.event.ActionEvent;
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
public class Ventana_AdministradorFXML implements Initializable {

    @FXML
    private BorderPane borderPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void boton_Pro(MouseEvent event) {
        cargarVentanas("/VentanaAux/AdministradorProducto");

    }

    @FXML
    private void boton_Menu(MouseEvent event) {
        cargarVentanas("/VentanaAux/AdministradorMenu");
    }

    @FXML
    private void boton_Horario(MouseEvent event) {
        cargarVentanas("/VentanaAux/AdministradorHorario");
    }

    @FXML
    private void boton_Usu(MouseEvent event) {
        cargarVentanas("/VentanaAux/AdminUsuario");
    }

    private void cargarVentanas(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        } catch (IOException ex) {
            //Logger.getLogger(Ventana_EstudiantesFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderPane.setCenter(root);
    }

    @FXML
    private void boton_Salir(MouseEvent event) {
        cerrarVentana(event);
        CerrarSesión al = new CerrarSesión();
        al.cs();
    }

    public void cerrarVentana(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
