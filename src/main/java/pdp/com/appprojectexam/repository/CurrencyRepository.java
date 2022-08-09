package pdp.com.appprojectexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.com.appprojectexam.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency,Integer> {
    boolean existsByNameAndIdAndStatusTrue(String name, Integer id);

    boolean existsByNameAndStatusTrue(String name);
}
