package pdp.com.appprojectexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.com.appprojectexam.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsByNameAndId(String name, Integer id);
    boolean existsByName(String name);
}
