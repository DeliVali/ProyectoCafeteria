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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.ProductoVO;
import modelo.Producto_DAO_IMP;
import modelo.Sugerir_Pro;
import modelo.Sugerir_Pro_DAO_IMP;

/**
 * FXML Controller class
 *
 * @author jeffr
 */
public class EstudianteProductoController implements Initializable {

    @FXML
    private JFXTextField textNombre;
    @FXML
    private JFXTextArea textDesc;
    @FXML
    private JFXButton botonAgregar;
    @FXML
    private TableView<ProductoVO> tvProducto;
    @FXML
    private TableColumn<ProductoVO, String> tabNombre;
    @FXML
    private TableColumn<ProductoVO, String> tabDesc;
    @FXML
    private TableColumn<ProductoVO, Integer> tabCanti;
    @FXML
    private TableColumn<ProductoVO, Integer> tabPrecio;
    
    Sugerir_Pro_DAO_IMP implementacionSugerirDAO=new Sugerir_Pro_DAO_IMP();
    String nombreAux="";
    private ProductoVO producto = new ProductoVO();
    private Producto_DAO_IMP imp = new Producto_DAO_IMP();
    private ObservableList<ProductoVO> listaDeProductos;

    

    /**
     * Initializes the controller class.
     * @param url
     */
    
        public void obtenerProducto(){
        List listaConsulta = null;
        try{
            listaConsulta = imp.readAll();
        } catch(Exception e){
            System.out.println("Error al leer la consulta");
        }
        Iterator it = listaConsulta.iterator();
       listaDeProductos.clear();
       while(it.hasNext()){
           listaDeProductos.add((ProductoVO)it.next());
       }
    
    }
    
    public void colocarProductosTabla(){
        this.obtenerProducto();
        this.tabNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        this.tabDesc.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
        this.tabCanti.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
        this.tabPrecio.setCellValueFactory(new PropertyValueFactory<>("Precio"));
        this.tvProducto.setItems(listaDeProductos);
    }
    
    @Override

    public void initialize(URL url, ResourceBundle rb) {
       this.imp = new Producto_DAO_IMP();
       this.listaDeProductos = FXCollections.observableArrayList();
       this.colocarProductosTabla();
    }    

    
    //Metodos
    @FXML
    private void eventoAgregar(ActionEvent event) {
        nombreAux=textNombre.getText();
        if(textNombre.getText().equals("") || textDesc.getText().equals("")){
       Alert alert =new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText("Alguno de los campos se encuentra vacio");
            alert.showAndWait();
        }else{
              try {
            Sugerir_Pro producto = new Sugerir_Pro();
            producto.setNombre(nombreAux);
            System.out.println(producto.toString());
          implementacionSugerirDAO.create(producto);
          Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alta Exitosa");
            alert.setHeaderText("Se ha agregado correctamente el producto!");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
            
    }
    
    
    
}
