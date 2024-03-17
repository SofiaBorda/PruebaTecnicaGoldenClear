package goldenClear.PruebaTecnica.service;

import java.util.List;
import java.util.Optional;

import goldenClear.PruebaTecnica.entity.Producto;

public interface ProductoService {
	Optional<Producto> getProductoById(Long id);
	List<Producto> getAllProductos();
	Optional<Producto> createProducto(Producto producto);
	void deleteProducto(Long id);
}
