package pdp.com.appprojectexam.service;

import pdp.com.appprojectexam.entity.Product;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.ProductAddDTO;
import pdp.com.appprojectexam.payload.ProductInfoDTO;
import pdp.com.appprojectexam.payload.ProductUpdateDTO;

public interface ProductService {


    ApiResult<?> getAll(int page, int size);

    ApiResult<ProductInfoDTO> add(ProductAddDTO product);

    ApiResult<ProductInfoDTO> update(ProductUpdateDTO product, Integer id);

    String delete(Integer id);

    Product getByIdOrElseThrow(Integer productId);

    ApiResult<ProductInfoDTO> getOne(Integer id);
}
