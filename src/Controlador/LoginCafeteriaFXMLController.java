/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import modelo.UsuariosVO;
import modelo.Usuarios_DAO_Imp;


/**
 * FXML Controller class
 *
 * @author joel_
 */
public class LoginCafeteriaFXMLController implements Initializable {

    @FXML
    private TextField campoMatricula;

    @FXML
    private PasswordField campoPass;

    @FXML
    private Button botonIngresar;

    @FXML
    private Button botonCancelar;
    
    private Usuarios_DAO_Imp implementacionDAO;
    
    private UsuariosVO estudiante;
    
    public boolean verificacion(){
        String user = this.campoMatricula.getText();
        String pass = this.campoPass.getText();
        try{
            this.estudiante = implementacionDAO.read(user.intern());
            if(this.estudiante.getPass().intern() == pass.intern() ){
                return true;
            }else{
                return false;
            }
        } catch (Exception e){
            System.out.println("Error al leer el usuario en el login "+ e);
        }
        return false;
    }
    
    public void acceso(){
        try{
            Parent root = null;
            if (this.estudiante.isTipo()==false){
                root = FXMLLoader.load(getClass().getResource("/Ventanas/Ventana_EstudiantesFXML.fxml")); 
                Scene scene = new Scene (root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            }else if (this.estudiante.isTipo()==true){
                //root = FXMLLoader.load(getClass().getResource("/vista/VentasFXML.fxml"));
            }
            /*Scene scene = new Scene (root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();*/
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void cancela(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void ingresa(ActionEvent event) {
        if (verificacion()){
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            this.acceso();
        }else{
            if (this.campoMatricula.getLength()==0 || this.campoPass.getLength()==0){
                System.out.println("Campos vacios");
            }else{
                System.out.println("Usuario incorrecto");
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       this.implementacionDAO = new Usuarios_DAO_Imp();
       this.estudiante = new UsuariosVO();
    }    
    
}
