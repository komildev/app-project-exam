package pdp.com.appprojectexam.service;

import pdp.com.appprojectexam.entity.HaveProducts;

import java.util.Optional;

public interface HaveProductService {

    boolean getProduct(Integer productId, Double amount);

    void save(HaveProducts haveProducts);

    Optional<HaveProducts> findById(Integer productId);


}
