package pdp.com.appprojectexam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import pdp.com.appprojectexam.controller.SupplierController;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.SupplierAddDTO;
import pdp.com.appprojectexam.payload.SupplierInfoDTO;
import pdp.com.appprojectexam.payload.SupplierUpdateDTO;
import pdp.com.appprojectexam.service.SupplierService;

@RestController
@RequiredArgsConstructor
public class SupplierControllerImpl implements SupplierController {

    private final SupplierService supplierService;

    @Override
    public ApiResult<SupplierInfoDTO> getOne(Integer id) {
        return supplierService.getOne(id);
    }

    @Override
    public ApiResult<?> getAll(int page, int size) {
        return supplierService.getAll(page, size);
    }

    @Override
    public ApiResult<SupplierInfoDTO> add(SupplierAddDTO supplierAddDTO) {
        return supplierService.add(supplierAddDTO);
    }

    @Override
    public ApiResult<SupplierInfoDTO> update(SupplierUpdateDTO supplierUpdateDTO, Integer id) {
        return supplierService.update(supplierUpdateDTO,id);
    }

    @Override
    public String delete(Integer id) {
        return supplierService.delete(id);
    }
}
