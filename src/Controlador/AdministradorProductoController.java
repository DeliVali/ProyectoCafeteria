package Controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.util.Iterator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
    private JFXTextField search;
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
    @FXML
    private Label campoIDBuscar;
    @FXML
    private Label campoCanBuscar;
    @FXML
    private JFXButton bEliminar;
 private Producto_DAO_IMP implementacionBuscarDao = new Producto_DAO_IMP();
    private ProductoVO productoBuscar = new ProductoVO();

    //Termina lista de elementos buscar prodcutos
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
        colocarProductosTabla();
        filtrar();
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
    
    @FXML
    void eliminarProducto(ActionEvent event) {
        int selectedIndex = this.tvProducto.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0){
            ProductoVO producto = this.tvProducto.getSelectionModel().getSelectedItem();
            this.tvProducto.getSelectionModel().selectLast();
            try{
                this.imp.delete(producto);
            } catch(Exception e){
                System.out.println("Error al eliminar");
            }
            this.colocarProductosTabla();
            if(selectedIndex != 0){
                selectedIndex--;
                this.tvProducto.getSelectionModel().select(selectedIndex);
            }
        }
        colocarProductosTabla();
        filtrar();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
       this.imp = new Producto_DAO_IMP();
       this.listaDeProductos = FXCollections.observableArrayList();
       this.colocarProductosTabla();
       filtrar();
    }    

    @FXML
    private void buscar(ActionEvent event) {
         int id = 0;
        if (this.txtBuscar.getText().startsWith("1") || this.txtBuscar.getText().startsWith("2") || this.txtBuscar.getText().startsWith("3")
                 || this.txtBuscar.getText().startsWith("4") || this.txtBuscar.getText().startsWith("5") || this.txtBuscar.getText().startsWith("6")
                 || this.txtBuscar.getText().startsWith("7") || this.txtBuscar.getText().startsWith("8") || this.txtBuscar.getText().startsWith("9")
                 || this.txtBuscar.getText().startsWith("0")){
            id = Integer.parseInt(this.txtBuscar.getText());
            try{
                this.productoBuscar = this.implementacionBuscarDao.readId(id);
                if (this.productoBuscar.getNombre()!=""){
                    this.campoIDBuscar.setText(Integer.toString(productoBuscar.getId()));
                    this.campoNombreBuscar.setText(productoBuscar.getNombre());
                    this.campoDesBuscar.setText(productoBuscar.getDescripcion());
                    this.campoCanBuscar.setText(Integer.toString(productoBuscar.getCantidad()));
                    this.campoPrecioBuscar.setText(Integer.toString(productoBuscar.getPrecio()));
                }else{
                    this.txtBuscar.setText("producto inexistente");
                    this.ocultarDatos();
                }
            }catch (Exception e){
                System.out.println("Error al buscar producto "+ e);
            }
        }else{
                this.txtBuscar.setText("Datos erroneos");
                this.ocultarDatos();
        }
    }
    
    public void ocultarDatos(){
        this.campoIDBuscar.setText("");
        this.campoNombreBuscar.setText("");
        this.campoDesBuscar.setText("");
        this.campoCanBuscar.setText("");
        this.campoPrecioBuscar.setText("");
    }
    
    private void filtrar(){
         FilteredList<ProductoVO> listaFiltrada = new FilteredList<>(listaDeProductos,b->true) ;
       search.textProperty().addListener((observable,oldValue,newValue) -> {

      listaFiltrada.setPredicate(productoaux -> {
				// If filter text is empty, display all persons.

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (productoaux.getNombre().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (productoaux.getDescripcion().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				     else  
				    	 return false; // Does not match.
			});
		});


		SortedList<ProductoVO> sortedData = new SortedList<>(listaFiltrada);
		 sortedData = new SortedList<>(listaFiltrada);

		sortedData.comparatorProperty().bind(tvProducto.comparatorProperty());


		tvProducto.setItems(sortedData);
    }
    
    
}
