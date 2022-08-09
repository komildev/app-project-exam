package pdp.com.appprojectexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.com.appprojectexam.entity.HaveProducts;

import java.sql.Timestamp;
import java.util.Optional;

public interface HaveProductsRepository extends JpaRepository<HaveProducts,Integer> {

    Optional<HaveProducts> findByProductIdAndAmountGreaterThan(Integer product_id, Double amount);

  //  HaveProducts findFirstByCreatedAtAndUpdatedAtEqualsOrderByAmountDesc(Timestamp createdAt);

}
