package pdp.com.appprojectexam.service;

import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.SupplierAddDTO;
import pdp.com.appprojectexam.payload.SupplierInfoDTO;
import pdp.com.appprojectexam.payload.SupplierUpdateDTO;

public interface SupplierService {

    ApiResult<?> getAll(int page, int size);

    ApiResult<SupplierInfoDTO> add(SupplierAddDTO supplierAddDTO);

    ApiResult<SupplierInfoDTO> update(SupplierUpdateDTO supplierUpdateDTO, Integer id);

    String delete(Integer id);

    ApiResult<SupplierInfoDTO> getOne(Integer id);
}
