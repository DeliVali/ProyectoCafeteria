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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    
   // Items de la reservación de productos
      @FXML
    private JFXTextField txtBuscarProducto;

    @FXML
    private Button btnBuscarProducto;

    @FXML
    private Button btnReservarProducto;

    @FXML
    private JFXTextField txtNombreProducto;

    @FXML
    private JFXTextField txtPrecioProducto;

    @FXML
    private JFXTextField txtMatricula;
    
    private String nombreP, nombrePro, matricula;
    
    //private ProductoVO producto = new ProductoVO();
    
    private int idProducto;
    
    private Producto_DAO_IMP implementacionDAO = new Producto_DAO_IMP();
   // Finaliza items de la reservación de productos
    
    //Elemenetos buscar producto
    @FXML
    private JFXTextField txtBuscar;

    @FXML
    private JFXButton botonBuscar;

    @FXML
    private Label campoNombreBuscar;

    @FXML
    private Label campoDesBuscar;

    @FXML
    private Label campoPrecioBuscar;
    
    private Producto_DAO_IMP implementacionBuscarDao = new Producto_DAO_IMP();
    private ProductoVO productoBuscar = new ProductoVO();
    
    //Termina lista de elementos buscar prodcutos
    

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
       btnReservarProducto.setDisable(true);
       this.imp = new Producto_DAO_IMP();
       this.listaDeProductos = FXCollections.observableArrayList();
       this.colocarProductosTabla();
       this.ocultarDatos();
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
    /*
    // Método de la reservación de productos
    @FXML
    void buscarProducto(ActionEvent event) throws Exception {
      
        
        nombreP = txtBuscarProducto.getText();
        
        ProductoVO producto = new ProductoVO();
        
        if(nombreP.equals("")){
            Alert alert =new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nombre de producto a buscar vacío");
            alert.setHeaderText("Campo de búsqueda vacío");
            alert.showAndWait();
        }else{
                
            producto = implementacionDAO.read(nombreP);
            
            if(producto.getNombre().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Lo sentimos. Producto no disponible");
                alert.setTitle("Búsqueda de producto");
                alert.setContentText("Recuerda que contamos con la opción de sugerencia de productos");
                alert.showAndWait();;
            }else{
                this.txtNombreProducto.setText(producto.getNombre());
                this.txtPrecioProducto.setText(String.valueOf(producto.getPrecio()));
                btnReservarProducto.setDisable(false);
            }
        }
    }

    // Método de la reservación de productos
    @FXML
    void reservarProducto(ActionEvent event) throws Exception {
        
        matricula = txtMatricula.getText(); 
        
        if(matricula.equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ingresa tu matrícula");
            alert.setHeaderText("Ingresa tu matrícula para finalizar la reservación");
            alert.showAndWait();
        }else{
            nombrePro = txtNombreProducto.getText();
            ProductoVO producto = new ProductoVO();
            producto = implementacionDAO.read(nombrePro);

            idProducto = producto.getId();            
             
            try{
                this.implementacionDAO.reservar(idProducto, matricula); 
                
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Reserva exitosa");
                alert.setHeaderText("Tu reserva ha sido exitosa");
                alert.showAndWait();
                
            }catch (Exception ex) {
                System.out.println("Error al resevar producto");
            }
       }
       
    }
    
    //Empiezan métodos buscar producto
    @FXML
    void buscar(ActionEvent event) {
        String nombre = this.txtBuscar.getText();
        try{
            this.productoBuscar = this.implementacionBuscarDao.read(nombre);
            if (this.productoBuscar.getNombre()!=""){
                this.campoNombreBuscar.setText(productoBuscar.getNombre());
                this.campoDesBuscar.setText(productoBuscar.getDescripcion());
                this.campoPrecioBuscar.setText(Integer.toString(productoBuscar.getPrecio()));
            }else{
                this.txtBuscar.setText("producto inexistente");
                this.ocultarDatos();
            }
        }catch (Exception e){
            System.out.println("Error al buscar producto "+ e);
        }
    }
    */
    public void ocultarDatos(){
        this.campoNombreBuscar.setText("");
        this.campoDesBuscar.setText("");
        this.campoPrecioBuscar.setText("");
    }
    //Terminan métodos buscar producto

    @FXML
    private void buscarProducto(ActionEvent event) {
    }

    @FXML
    private void reservarProducto(ActionEvent event) {
    }

    @FXML
    private void buscar(ActionEvent event) {
    }
    
}
