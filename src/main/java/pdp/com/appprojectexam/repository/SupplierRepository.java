package pdp.com.appprojectexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.com.appprojectexam.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier,Integer> {

    boolean existsByNameAndId(String name, Integer id);

    boolean existsByName(String name);
}
