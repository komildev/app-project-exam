package pdp.com.appprojectexam.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pdp.com.appprojectexam.entity.HaveProducts;
import pdp.com.appprojectexam.entity.Product;
import pdp.com.appprojectexam.repository.HaveProductsRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HaveProductServiceImpl implements HaveProductService {

    private final HaveProductsRepository haveProductsRepository;
    private final ProductService productService;

    @Override
    public boolean getProduct(Integer productId, Double amount) {
        Optional<HaveProducts> optionalHaveProduct = haveProductsRepository.findByProductIdAndAmountGreaterThan(productId,amount);
        Product product = productService.getByIdOrElseThrow(productId);
        if (optionalHaveProduct.isEmpty()) return false;
        HaveProducts haveProducts = optionalHaveProduct.get();
        haveProducts.setAmount(haveProducts.getAmount() - amount);
        save(haveProducts);
        return true;
    }

    @Override
    public void save(HaveProducts haveProducts) {
        haveProductsRepository.save(haveProducts);
    }

    @Override
    public Optional<HaveProducts> findById(Integer productId) {
        return haveProductsRepository.findById(productId);
    }


}
