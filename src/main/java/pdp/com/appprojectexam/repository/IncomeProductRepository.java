package pdp.com.appprojectexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.com.appprojectexam.entity.IncomeProduct;

import java.sql.Timestamp;
import java.util.List;

public interface IncomeProductRepository extends JpaRepository<IncomeProduct,Integer> {
    List<IncomeProduct> getByExpiredDateLessThanEqual(Timestamp expiredDate);
}
