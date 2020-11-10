/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author joel_
 */
public class CerrarSesión {
    
    private Stage stage;
    
    public void CerrarSesión(){
        this.stage= new Stage ();
    }
    
    public void setStage(Stage stageV){
        this.stage = stage;
    }
    
    public void cs(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Ventanas/LoginCafeteriaFXML.fxml"));
            Parent login = (Parent) loader.load();
            Stage dialogoStage = new Stage();
            dialogoStage.initModality(Modality.WINDOW_MODAL);
            dialogoStage.initOwner(stage);
            Scene scene = new Scene(login);
            dialogoStage.setScene(scene);
            
            dialogoStage.showAndWait();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
}
