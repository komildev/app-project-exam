package pdp.com.appprojectexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.com.appprojectexam.entity.WareHouse;

public interface WareHouseRepository extends JpaRepository<WareHouse, Integer> {

    boolean existsByNameAndIdAndStatusTrue(String name, Integer id);

    boolean existsByNameAndStatusTrue(String name);

}
