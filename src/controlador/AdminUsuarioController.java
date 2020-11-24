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
             
             if(this.implementacionDAO.bloqueo(usuario)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Bloqueo de usuario");
                alert.setHeaderText("El usuario ha sido bloqueado");
                alert.showAndWait();
             }else{
                  Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Bloqueo de usuario");
                alert.setHeaderText("No se pudo bloquear al usuario");
                alert.showAndWait();
             }
         } catch (Exception ex) {
             System.out.println("Error al editar usuario");
         }
    }

    @FXML
    void desbloquearUsuario(ActionEvent event) {

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
        
         try {       
             this.implementacionDAO.update(usuario);
         } catch (Exception ex) {
             System.out.println("Error al editar usuario");
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
    
}
