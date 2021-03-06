/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.UsuariosVO;
import modelo.Usuarios_DAO_Imp;

/**
 * FXML Controller class
 *
 * @author enrique
 */
public class AdminUsuarioController implements Initializable {

    // ------------------ Mostrar Usuarios ---------------------
    @FXML
    private TableView<UsuariosVO> tablaUsuarios;

    @FXML
    private TableColumn<UsuariosVO,String> columnaMatricula;

    @FXML
    private TableColumn<UsuariosVO, String> columnaNombre;

    @FXML
    private TableColumn<UsuariosVO, String> columnaPass;

    @FXML
    private TableColumn<UsuariosVO, Boolean> columnaTipo;

    @FXML
    private TableColumn<UsuariosVO, Boolean> columnaBloqueo;

    //private Usuarios_DAO_Imp implementacionDAO;
    
    private ObservableList<UsuariosVO> listaDeUsuarios;
    
    // --------------------- Editar Usuario ------------------------
    
    
    @FXML
    private TextField txtBuscarUsuario;

    @FXML
    private Button btnBuscarUsuario;

    @FXML
    private Button btnBloquearUsuario;
    
    @FXML
    private Button btnDesbloquearUsuario;

    @FXML
    private Button btnGuardarEdicion;
    
    @FXML
    private TextField txtMatricula;

    @FXML
    private TextField txtNombre;
    
    @FXML
    private TextField txtTipo;

    @FXML
    private TextField txtContrasena;

    @FXML
    private TextField txtBloqueo;
   
    private UsuariosVO usuario =new UsuariosVO();
    
    private Usuarios_DAO_Imp implementacionDAO = new Usuarios_DAO_Imp();
    
    private String matricula;
    @FXML
    private JFXRadioButton radioAdmin;
    @FXML
    private ToggleGroup tipoUsuario;
    @FXML
    private JFXRadioButton radioEstudiante;
    @FXML
    private JFXButton botonAgregarUsu;
    @FXML
    private JFXTextField nombreField;
    @FXML
    private JFXTextField contraField;
    @FXML
    private JFXTextField matriculaField;
    @FXML
    private JFXButton btnEliminarUsu;
    
    
    
    public void obtenerUsuarios(){
        List listaConsulta = null;
        try{
           listaConsulta = implementacionDAO.readAll();
        }catch(Exception e){
            System.out.println("Error al leer los usuarios");
        }
        Iterator it = listaConsulta.iterator();
        listaDeUsuarios.clear();
        while (it.hasNext()){
            listaDeUsuarios.add((UsuariosVO)it.next());
        }
    }
    
    
    public void colocarUsuariosTabla(){
        this.obtenerUsuarios();
        this.columnaMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        this.columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columnaPass.setCellValueFactory(new PropertyValueFactory<>("pass"));        
        this.columnaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        this.columnaBloqueo.setCellValueFactory(new PropertyValueFactory<>("bloqueo"));
        this.tablaUsuarios.setItems(listaDeUsuarios);
    }
    
    
    @FXML
    void bloquearUsuario(ActionEvent event) throws Exception {
        this.usuario.setMatricula((this.txtMatricula.getText()));
        this.usuario.setNombre((this.txtNombre.getText()));
        this.usuario.setPass((this.txtContrasena.getText()));
        this.usuario.setTipo(false);
        this.usuario.setBloqueo(true);
        
         try {
             
             if(this.implementacionDAO.bloqueo(usuario.getMatricula())){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Bloqueo de usuario");
                alert.setHeaderText("No se pudo bloquear al usuario");
                alert.showAndWait();
             }else{
                 Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Bloqueo de usuario");
                alert.setHeaderText("El usuario ha sido bloqueado");
                alert.showAndWait();
             }
         } catch (Exception ex) {
             System.out.println("Error al editar usuario");
         }
         colocarUsuariosTabla();
    }

    @FXML
    void desbloquearUsuario(ActionEvent event) {
        
        String matricula = txtMatricula.getText();
         
        try{
            if(!this.implementacionDAO.desbloqueo(matricula)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Desbloqueo de usuario");
                alert.setHeaderText("El usuario ha sido desbloqueado");
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Desbloqueo de usuario");
                alert.setHeaderText("No se pudo desbloquear al usuario");
                alert.showAndWait();
             }
        } catch (Exception ex) {
             System.out.println("Error al desbloquear usuario");
         }
        colocarUsuariosTabla();
    }
    
    @FXML
    void buscarUsuario(ActionEvent event) throws Exception {
        
        matricula = txtBuscarUsuario.getText();
        
        UsuariosVO usuario = new UsuariosVO();
        
        usuario = implementacionDAO.read(matricula);
        
        this.txtMatricula.setText(usuario.getMatricula());
        this.txtNombre.setText(usuario.getNombre());
        this.txtContrasena.setText(usuario.getPass());
  
        
        if(usuario.isTipo() == false){
            this.txtTipo.setText("Cliente");
        }else{
            this.txtTipo.setText("Administrador");
        }
        
        if(usuario.isBloqueo() == false){
            this.txtBloqueo.setText("No bloqueado");
        }else{
            this.txtBloqueo.setText("Bloqueado");
        }
        
    }
    
    @FXML
    void guardarEdicion(ActionEvent event) {
                
        this.usuario.setMatricula((this.txtMatricula.getText()));
        this.usuario.setNombre((this.txtNombre.getText()));
        this.usuario.setPass((this.txtContrasena.getText()));
        this.usuario.setTipo(false);
        this.usuario.setBloqueo(false);
        
      if(!txtNombre.getText().equals("") && !txtMatricula.getText().equals("") && !txtContrasena.getText().equals("")){  
         try {      
             if(!this.implementacionDAO.update(usuario)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Edición de usuario");
                alert.setHeaderText("Usuario editado");
                alert.showAndWait();
             }else{
                 Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Edición de usuario");
                alert.setHeaderText("Usuario no editado");
                alert.showAndWait();
             }
         } catch (Exception ex) {
             System.out.println("Error al editar usuario");
         }
      }else{
          //error
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Edición de usuario");
                alert.setHeaderText("Campos vacíos");
                alert.showAndWait();
      }
      
    
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.implementacionDAO = new Usuarios_DAO_Imp();
        this.listaDeUsuarios = FXCollections.observableArrayList();
        this.colocarUsuariosTabla();
    }    

    @FXML
    
    private void eventoAgregarUsu(ActionEvent event) {
        UsuariosVO usuario=new UsuariosVO();
        if(contraField.getText().equals("") || nombreField.getText().equals("") || matriculaField.getText().equals("") ){
             Alert alert =new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Alguno de los campos esta vacio");
            alert.showAndWait();
        }else{
            if(radioAdmin.isSelected() || radioEstudiante.isSelected()){
                usuario.setNombre(nombreField.getText());
                usuario.setPass(contraField.getText());
                usuario.setMatricula(matriculaField.getText());
                if(radioAdmin.isSelected()){
                    usuario.setTipo(true);
                }else{
                    usuario.setTipo(false);
                }
                try {
                    implementacionDAO.create(usuario);
                    Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alta Exitosa");
            alert.setHeaderText("Se ha agregado correctamente el Usuario!");
            alert.showAndWait();
            colocarUsuariosTabla();
                } catch (Exception ex) {
                     Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("La matricula ya Existe");
            alert.showAndWait();
                }
                
             
            }else{
                   Alert alert =new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Seleccione un tipo de usuario");
            alert.showAndWait();
                
            }
        }
        colocarUsuariosTabla();
    }

    @FXML
    private void eventEliminarUsu(ActionEvent event) {
        UsuariosVO usuarioSelect = this.tablaUsuarios.getSelectionModel().getSelectedItem();
        if(usuarioSelect!=null){
            try {
                implementacionDAO.delete(usuarioSelect);
                  Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Terminado");
            alert.setHeaderText("El usuario se ha eliminado con exito");
            alert.showAndWait();
            } catch (Exception ex) {
                Alert alert =new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText("No se ha podido eliminar el usuario");
            alert.showAndWait();
            }
    }else{
              Alert alert =new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText("Por favor seleccione un usuario");
            alert.showAndWait();
        }
        colocarUsuariosTabla();
    }
    
    
   
    
}
