package modelo;

import java.util.List;

public interface Producto_DAO {
    
    public boolean create(ProductoVO producto) throws Exception;
    public List<ProductoVO> readAll() throws Exception;
    public ProductoVO read(int id) throws Exception;
    public ProductoVO search(int id) throws Exception;
    public boolean update(ProductoVO producto) throws Exception;
    public boolean delete(ProductoVO producto) throws Exception;
    
}
