/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.UsuariosVO;
import modelo.Usuarios_DAO_Imp;

/**
 * FXML Controller class
 *
 * @author enrique
 */
public class FXMLEditarUsuarioController implements Initializable {

     @FXML
    private Button btnEditarUsuarioMenu;

    @FXML
    private TextField txtBuscarUsuario;

    @FXML
    private Button btnBuscarUsuario;

    @FXML
    private Button btnBloquearUsuario;

    @FXML
    private Button btnGuardarEdicion;

    @FXML
    private Button btnCancelar;
    
    @FXML
    private TextField txtMatricula;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtContrasena;

    @FXML
    private TextField txtBloqueo;
   
    private UsuariosVO usuario;
    
    private Usuarios_DAO_Imp implementacionDAO=new Usuarios_DAO_Imp();
    
    private String matricula;
    
    private ObservableList<UsuariosVO> listaUsuario;

    @FXML
    void EditarUsuarioMenu(ActionEvent event) {

    }

    @FXML
    void bloquearUsuario(ActionEvent event) {

    }

  
    
    @FXML
    void buscarUsuario(ActionEvent event) throws Exception {
        matricula = txtMatricula.getText();
        this.implementacionDAO.search(matricula);
        
        this.txtMatricula.setText(usuario.getMatricula());
        this.txtNombre.setText(usuario.getNombre());
        this.txtContrasena.setText(usuario.getPass());
        
        if(usuario.isBloqueo() == false){
            this.txtBloqueo.setText("No bloqueado");
        }else{
            this.txtBloqueo.setText("Bloqueado");
        }
    }
    
    
    @FXML
    void cancelar(ActionEvent event) throws Exception {
    }

    @FXML
    void guardarEdicion(ActionEvent event) {
        this.usuario.setMatricula((this.txtMatricula.getText()));
        this.usuario.setNombre((this.txtNombre.getText()));
        this.usuario.setPass((this.txtContrasena.getText()));
        this.usuario.setBloqueo(false);
        
        this.implementacionDAO.update(usuario);       
    }
    
   
    private void start(Stage stage) throws Exception {
        //Editar source
        Parent root = FXMLLoader.load(getClass().getResource("/vista/FXMLPrincipal.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

  
    
}