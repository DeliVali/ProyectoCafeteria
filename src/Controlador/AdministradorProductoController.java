package Controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import java.util.Iterator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import modelo.ProductoVO;
import modelo.Producto_DAO_IMP;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdministradorProductoController implements Initializable {

    @FXML
    private JFXButton bAgregarProducto;
    @FXML
    private TextField tNombre;
    @FXML
    private TextArea tDesc;
    @FXML
    private TextField tCant;
    @FXML
    private TextField tPrecio;
    @FXML
    private TextField eNombre;
    @FXML
    private TextField eCant;
    @FXML
    private TextField ePrecio;
    @FXML
    private TextArea eDesc;
    @FXML
    private TextField eID;
    @FXML
    private JFXButton bEditarProducto;
    @FXML
    private JFXButton bEdit;
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
    private boolean Edicion;
    
    private ProductoVO producto = new ProductoVO();
    private Producto_DAO_IMP imp = new Producto_DAO_IMP();
    private ObservableList<ProductoVO> listaDeProductos;
    private ProductoVO productoEditar;

    @FXML
    void agregarProducto(ActionEvent event) throws Exception {
        producto.setNombre(tNombre.getText());
        producto.setDescripcion(tDesc.getText());
        producto.setCantidad(Integer.parseInt(tCant.getText()));
        producto.setPrecio(Integer.parseInt(tPrecio.getText()));
        
        try{
            imp.create(producto);
            System.out.println("Producto agregado");
        } catch(Exception e){
            throw new Exception("Error al crear producto "+e.getMessage());
        }
        
    }
    
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
    
    
    private void mostrarProducto(ProductoVO producto){
        
        if(producto!=null){
            this.eID.setText(Integer.toString(producto.getId()));
            this.eID.setEditable(false);
            this.eNombre.setText(producto.getNombre());
            this.eDesc.setText(producto.getDescripcion());
            this.eCant.setText(Integer.toString(producto.getCantidad()));
            this.ePrecio.setText(Integer.toString(producto.getPrecio()));
            System.out.println("Ve a la pestaña 'Editar Producto'");
        } else{
            this.eNombre.setText("");
            this.eDesc.setText("");
            this.eCant.setText("");
            this.ePrecio.setText("");
        }
    }
    
    public boolean getEdicion(){
        return Edicion;
    }
    
    @FXML
    void colocarProducto(ActionEvent event) throws Exception {
        productoEditar = this.tvProducto.getSelectionModel().getSelectedItem();
        this.mostrarProducto(productoEditar);
        this.Edicion = true;
    }
    
    public boolean Editar(ProductoVO producto){
        try{
            return this.getEdicion();
        } catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    @FXML
    void editarProducto(ActionEvent event) throws Exception {
        
        productoEditar  = this.tvProducto.getSelectionModel().getSelectedItem();
        if(productoEditar !=null){
             boolean esEdicion = this.Editar(productoEditar);
             if(esEdicion){
                 int ultimo = this.tvProducto.getSelectionModel().getSelectedIndex();
                 try{
                     productoEditar.setId(Integer.parseInt(this.eID.getText()));
                     productoEditar.setNombre(this.eNombre.getText());
                     productoEditar.setDescripcion(this.eDesc.getText());
                     productoEditar.setCantidad(Integer.parseInt(this.eCant.getText()));
                     productoEditar.setPrecio(Integer.parseInt(this.ePrecio.getText()));
                     this.imp.update(productoEditar);
                     System.out.println("Producto editado");
                 } catch(Exception e){
                    System.out.println("Error al editar el producto");
                   }
             this.colocarProductosTabla();
             this.tvProducto.getSelectionModel().select(productoEditar);
             }
        } else{
            System.out.println("No se seleccionó un producto");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
       this.imp = new Producto_DAO_IMP();
       this.listaDeProductos = FXCollections.observableArrayList();
       this.colocarProductosTabla();
    }    
    
}
