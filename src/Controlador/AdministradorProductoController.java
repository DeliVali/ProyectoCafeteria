package Controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mysql.cj.util.StringUtils;
import java.util.Iterator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import modelo.ProductoVO;
import modelo.Producto_DAO_IMP;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
    private TableView<ProductoVO> tvProducto;
    @FXML
    private TableColumn<ProductoVO, String> tabNombre;
    @FXML
    private TableColumn<ProductoVO, String> tabDesc;
    @FXML
    private TableColumn<ProductoVO, Integer> tabCanti;
    @FXML
    private TableColumn<ProductoVO, Integer> tabPrecio;
    
    private ProductoVO producto = new ProductoVO();
    private Producto_DAO_IMP imp = new Producto_DAO_IMP();
    private ObservableList<ProductoVO> listaDeProductos;
    
    
    
    @FXML
    private JFXTextField search;
    
   

    @FXML
    void agregarProducto(ActionEvent event) throws Exception {
        producto.setNombre(tNombre.getText());
        producto.setDescripcion(tDesc.getText());
        producto.setCantidad(Integer.parseInt(tCant.getText()));
        producto.setPrecio(Integer.parseInt(tPrecio.getText()));
        
        try{
            imp.create(producto);
           Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alta Exitosa");
            alert.setHeaderText("Se ha agregado correctamente el producto!");
            alert.showAndWait();
        } catch(Exception e){
            throw new Exception("Error al crear producto "+e.getMessage());
        }
        colocarProductosTabla();
        buscar();
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


    @Override
    public void initialize(URL url, ResourceBundle rb) {
       this.imp = new Producto_DAO_IMP();
       this.listaDeProductos = FXCollections.observableArrayList();
       this.colocarProductosTabla();
      buscar();
    
    }    
    
   

    public AdministradorProductoController() {
    }
    
    
    @FXML
    private void searchEvent(MouseEvent event) {
    }
    //Buscar los productos con el textfield
    private void buscar(){
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