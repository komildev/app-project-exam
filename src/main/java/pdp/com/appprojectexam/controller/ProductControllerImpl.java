package pdp.com.appprojectexam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import pdp.com.appprojectexam.controller.ProductController;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.ProductAddDTO;
import pdp.com.appprojectexam.payload.ProductInfoDTO;
import pdp.com.appprojectexam.payload.ProductUpdateDTO;
import pdp.com.appprojectexam.service.ProductService;

@RestController
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {
    private final ProductService productService;

    @Override
    public ApiResult<ProductInfoDTO> getOne(Integer id) {
        return productService.getOne(id);
    }

    @Override
    public ApiResult<?> getAll(int page, int size) {
        return productService.getAll(page,size);
    }

    @Override
    public ApiResult<ProductInfoDTO> add(ProductAddDTO product) {
        return productService.add(product);
    }

    @Override
    public ApiResult<ProductInfoDTO> update(ProductUpdateDTO product, Integer id) {
        return productService.update(product,id);
    }

    @Override
    public String delete(Integer id) {
        return productService.delete(id);
    }
}
