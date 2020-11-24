/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocafeteria;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author jeffr
 */
public class ProyectoCafeteria extends Application {

     public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/Ventanas/LoginCafeteriaFXML.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/Ventanas/Ventana_EstudiantesFXML.fxml"));
       //Parent root = FXMLLoader.load(getClass().getResource("/Ventanas/Ventana_AdministradorFXML.fxml"));
       //Parent root = FXMLLoader.load(getClass().getResource("/Ventanas/Ventana_AdministradorFXML.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/Ventanas/Ventana_EstudiantesFXML.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/Ventanas/LoginCafeteriaFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
