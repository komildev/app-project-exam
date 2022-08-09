package pdp.com.appprojectexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.com.appprojectexam.entity.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {
    boolean existsByNameAndIdAndStatusTrue(String name, Integer id);

    boolean existsByNameAndStatusTrue(String name);
}
