/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
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
    
    //Agregar menú
    @FXML
    private JFXTextField txtNombreMenuAdd;

    @FXML
    private JFXTextArea txtDescMenuAdd;

    @FXML
    private JFXTextField txtTipoMenuAdd;

    @FXML
    private JFXTextField txtDiaMenuAdd;

    @FXML
    private JFXTextField intPrecioMenuAdd;

    @FXML
    private JFXButton btnGuardarMenuAdd;

    @FXML
    private JFXButton btnCancelarMenuAdd;

    //Editar menú
    @FXML
    private JFXTextField txtNombreMenuEdit;

    @FXML
    private JFXTextField txtTipoMenuEdit;

    @FXML
    private JFXTextArea txtDescMenuEdit;

    @FXML
    private JFXTextField txtDiaMenuEdit;

    @FXML
    private JFXTextField txtPrecioMenuEdit;

    @FXML
    private JFXButton btnCancelarMenuEdit;

    @FXML
    private JFXButton btnGuardarMenuEdit;

    @FXML
    private JFXButton btnBuscarMenuEdit;
    
    
    private MenuVO menu = new MenuVO();
    private Menu_DAO_IMP imp = new Menu_DAO_IMP();
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
    
    //Métodos agregar menú
    @FXML
    void agregarMenu(ActionEvent event) throws Exception {
        menu.setNombre(txtNombreMenuAdd.getText());
        menu.setDescripcion(txtDescMenuAdd.getText());
        menu.setTipo(txtTipoMenuAdd.getText());
        menu.setDia(txtDiaMenuAdd.getText());
        menu.setPrecio(Integer.parseInt(intPrecioMenuAdd.getText()));
        
        try{
            imp.create(menu);
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Agregado!");
            alert.setHeaderText("Se ha agregado correctamente el menú!");
            alert.showAndWait();
        } catch(Exception e){
            throw new Exception("Error al agregar menú "+e.getMessage());
        }
        //colocarMenuTabla();
    }
    
    public void obtenerMenu(){
        List listaConsulta = null;
        try{
            listaConsulta = imp.readAll();
        } catch(Exception e){
            System.out.println("Error al leer la consulta");
        }
        Iterator it = listaConsulta.iterator();
        listaMenus.clear();
        while(it.hasNext()){
           listaMenus.add((MenuVO)it.next());
        }
    
    }
    
    /*public void colocarMenuTabla(){
        this.obtenerMenu();
        this.tabNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        this.tabDesc.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
        this.tabCanti.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
        this.tabPrecio.setCellValueFactory(new PropertyValueFactory<>("Precio"));
        this.tvProducto.setItems(listaDeProductos);
    }*/
    
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
    }    

    
}
