package pdp.com.appprojectexam.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.SupplierAddDTO;
import pdp.com.appprojectexam.payload.SupplierInfoDTO;
import pdp.com.appprojectexam.payload.SupplierUpdateDTO;
import pdp.com.appprojectexam.utils.AppConstant;

@RequestMapping(SupplierController.SUPPLIER_PATH)
public interface SupplierController {
    String SUPPLIER_PATH = AppConstant.BASE_PATH + "/supplier/";
    String ADD = "add";
    String ALL = "all";
    String UPDATE = "update";
    String DELETE = "delete";

    String GET_ONE = "get-one";

    @PreAuthorize(value = "hasAuthority('VIEW_SUPPLIER')")
    @GetMapping(GET_ONE+"/{id}")
    ApiResult<SupplierInfoDTO> getOne(@PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('VIEW_SUPPLIER')")
    @GetMapping(ALL)
    ApiResult<?> getAll(@RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size);
    @PreAuthorize(value = "hasAuthority('ADD_SUPPLIER')")
    @PostMapping(ADD)
    ApiResult<SupplierInfoDTO> add(@RequestBody SupplierAddDTO supplierAddDTO);
    @PreAuthorize(value = "hasAuthority('EDIT_SUPPLIER')")
    @PutMapping(UPDATE + "/{id}")
    ApiResult<SupplierInfoDTO> update(@RequestBody SupplierUpdateDTO supplierUpdateDTO, @PathVariable Integer id);
    @PreAuthorize(value = "hasAuthority('DELETE_SUPPLIER')")
    @DeleteMapping(DELETE + "/{id}")
    String delete(@PathVariable Integer id);


}
