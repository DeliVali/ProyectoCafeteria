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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.UsuariosVO;
import modelo.Usuarios_DAO_Imp;

/**
 * FXML Controller class
 *
 * @author enrique
 */
public class FXMLMostrarUsuarioController implements Initializable {
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

    private Usuarios_DAO_Imp implementacionDAO;
    
    private ObservableList<UsuariosVO> listaDeUsuarios;
    
    
    
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
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.implementacionDAO = new Usuarios_DAO_Imp();
        this.listaDeUsuarios = FXCollections.observableArrayList();
        this.colocarUsuariosTabla();
    }    
    
}
