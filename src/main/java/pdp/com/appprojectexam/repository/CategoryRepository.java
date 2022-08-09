package pdp.com.appprojectexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.com.appprojectexam.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    boolean existsByNameAndStatusTrue(String name);
    boolean existsByNameAndIdAndStatusTrue(String name, Integer id);



}
