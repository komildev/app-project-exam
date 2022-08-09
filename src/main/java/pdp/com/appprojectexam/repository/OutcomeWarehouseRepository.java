package pdp.com.appprojectexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.com.appprojectexam.entity.IncomeWarehouse;
import pdp.com.appprojectexam.entity.OutcomeWarehouse;

public interface OutcomeWarehouseRepository extends JpaRepository<OutcomeWarehouse,Integer> {
}
