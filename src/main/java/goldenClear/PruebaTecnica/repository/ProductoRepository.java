package goldenClear.PruebaTecnica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import goldenClear.PruebaTecnica.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
}
