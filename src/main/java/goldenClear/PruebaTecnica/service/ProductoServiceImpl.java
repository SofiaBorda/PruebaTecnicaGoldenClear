package goldenClear.PruebaTecnica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import goldenClear.PruebaTecnica.entity.Producto;
import goldenClear.PruebaTecnica.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService{
	@Autowired
	ProductoRepository productoRepository;

	@Override
	public Optional<Producto> getProductoById(Long id) {
		return productoRepository.findById(id);
	}

	@Override
	public List<Producto> getAllProductos() {
		return productoRepository.findAll();
	}

	@Override
	public Optional<Producto> createProducto(Producto producto) {
		Producto cursoGuardado = productoRepository.save(producto);
        return Optional.ofNullable(cursoGuardado);
	}

	@Override
	public void deleteProducto(Long id) {
		productoRepository.deleteById(id);
		
	}

}
