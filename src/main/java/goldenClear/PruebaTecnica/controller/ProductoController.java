package goldenClear.PruebaTecnica.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import goldenClear.PruebaTecnica.entity.Producto;
import goldenClear.PruebaTecnica.errores.ErrorResponse;
import goldenClear.PruebaTecnica.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        Optional<Producto> productoOptional = productoService.createProducto(producto);
        return productoOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @GetMapping
    public List<Producto> getAllProducto() {
        return productoService.getAllProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductoById(@PathVariable Long id) {
        Optional<Producto> productoOptional = productoService.getProductoById(id);
        if (productoOptional.isPresent()) {
            return ResponseEntity.ok(productoOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró producto con el ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
        String mensaje = "Producto con ID " + id + " se eliminó correctamente";
        return ResponseEntity.ok(mensaje);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        Optional<Producto> productoOptional = productoService.getProductoById(id);
        if (productoOptional.isPresent()) {
            Producto existingProducto = productoOptional.get();
            existingProducto.setNombre(producto.getNombre());
            existingProducto.setPrecio(producto.getPrecio());
            productoService.createProducto(existingProducto);
            return ResponseEntity.ok(existingProducto);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("No se encontró producto con el ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

}
