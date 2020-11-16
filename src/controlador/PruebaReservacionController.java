/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modelo.ProductoVO;
import modelo.Producto_DAO_IMP;

/**
 * FXML Controller class
 *
 * @author enrique
 */
public class PruebaReservacionController implements Initializable {

    @FXML
    private TextField txtBuscarProducto;

    @FXML
    private TextField txtNombreProducto;

    @FXML
    private TextField txtPrecioProducto;

    @FXML
    private TextField txtMatricula;

    @FXML
    private Button btnBuscarProducto;

    @FXML
    private Button btnReservarProducto;
    
    private String nombreP, nombrePro, matricula;
    
    private ProductoVO producto = new ProductoVO();
    
    private int idProducto;
    
    private Producto_DAO_IMP implementacionDAO = new Producto_DAO_IMP();

    
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
            this.txtNombreProducto.setText(producto.getNombre());
            this.txtPrecioProducto.setText(String.valueOf(producto.getPrecio()));
            btnReservarProducto.setDisable(false);
        }
    }

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
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        btnReservarProducto.setDisable(true);
        
        
    }    
    
}
