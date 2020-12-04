/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.MenuVO;
import modelo.Menu_DAO_IMP;

/**
 * FXML Controller class
 *
 * @author jeffr
 */
public class AdministradorMenuController implements Initializable {

    //Elementos ver Menú
    @FXML
    private TableView<MenuVO> tablaMenu;
    @FXML
    private TableColumn<MenuVO, Integer> colID;
    @FXML
    private TableColumn<MenuVO, String> colNombre;
    @FXML
    private TableColumn<MenuVO, String> colDes;
    @FXML
    private TableColumn<MenuVO, String> colTipo;
    @FXML
    private TableColumn<MenuVO, String> colDia;
    @FXML
    private TableColumn<MenuVO, Integer> colPrecio;
    private ObservableList<MenuVO> listaMenus;
    
    
    //Elementos buscar Menú
    @FXML
    private JFXTextField txtBuscar;
    @FXML
    private JFXButton botonBuscar;
    @FXML
    private Label campoNombreBuscar;
    @FXML
    private Label campoDesBuscar;
    @FXML
    private Label campoTipoBuscar;
    @FXML
    private Label campoDiaBuscar;
    @FXML
    private Label campoPrecioBuscar;
    @FXML
    private Label campoIDBuscar;
    private Menu_DAO_IMP implementacionBuscarDao = new Menu_DAO_IMP();
    private MenuVO menuBuscar = new MenuVO();
    //Termina lista de elementos buscar Menú
    
    //Metodos ver Menú
    public void obtenerMenus(){
        List listaConsulta =null;
        try{
            listaConsulta = this.implementacionBuscarDao.readAll();
        }catch (Exception e){
            System.out.println("Error al leer la consulta");
        }
        Iterator it=listaConsulta.iterator();
        this.listaMenus.clear();
        while(it.hasNext()){
            listaMenus.add((MenuVO)it.next());
        }
    }
    
    public void colocarMenusTabla(){
        this.obtenerMenus();
        this.colID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        this.colNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        this.colDes.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
        this.colTipo.setCellValueFactory(new PropertyValueFactory<>("Tipo"));
        this.colDia.setCellValueFactory(new PropertyValueFactory<>("Dia"));
        this.colPrecio.setCellValueFactory(new PropertyValueFactory<>("Precio"));
        this.tablaMenu.setItems(listaMenus);
    }
    
    //Metodos Buscar Menu
    public void ocultarDatos(){
        this.campoIDBuscar.setText("");
        this.campoNombreBuscar.setText("");
        this.campoDesBuscar.setText("");
        this.campoTipoBuscar.setText("");
        this.campoDiaBuscar.setText("");
        this.campoPrecioBuscar.setText("");
    }
    @FXML
    void buscar(ActionEvent event) {
        int id = 0;
        if (this.txtBuscar.getText().startsWith("1") || this.txtBuscar.getText().startsWith("2") || this.txtBuscar.getText().startsWith("3")
                 || this.txtBuscar.getText().startsWith("4") || this.txtBuscar.getText().startsWith("5") || this.txtBuscar.getText().startsWith("6")
                 || this.txtBuscar.getText().startsWith("7") || this.txtBuscar.getText().startsWith("8") || this.txtBuscar.getText().startsWith("9")
                 || this.txtBuscar.getText().startsWith("0")){
            id = Integer.parseInt(this.txtBuscar.getText());
            try{
                this.menuBuscar = this.implementacionBuscarDao.read(id);
                if (this.menuBuscar.getNombre()!=""){
                    this.campoIDBuscar.setText(Integer.toString(menuBuscar.getId()));
                    this.campoNombreBuscar.setText(menuBuscar.getNombre());
                    this.campoDesBuscar.setText(menuBuscar.getDescripcion());
                    this.campoTipoBuscar.setText(menuBuscar.getTipo());
                    this.campoDiaBuscar.setText(menuBuscar.getDia());
                    this.campoPrecioBuscar.setText(Integer.toString(menuBuscar.getPrecio()));
                }else{
                    this.txtBuscar.setText("Menu inexistente");
                    this.ocultarDatos();
                }
            }catch (Exception e){
                System.out.println("Error al buscar menu "+ e);
            }
        }else{
                this.txtBuscar.setText("Datos erroneos");
        }
        
    }
    //Terminan metodos BuscarMenu
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.ocultarDatos();
        this.listaMenus = FXCollections.observableArrayList();
        this.colocarMenusTabla();
    }    

    
}
