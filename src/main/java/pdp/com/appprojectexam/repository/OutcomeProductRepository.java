package pdp.com.appprojectexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.com.appprojectexam.entity.IncomeProduct;
import pdp.com.appprojectexam.entity.OutcomeProduct;

import java.sql.Timestamp;
import java.util.List;

public interface OutcomeProductRepository extends JpaRepository<OutcomeProduct,Integer> {
}
