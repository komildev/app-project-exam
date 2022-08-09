package pdp.com.appprojectexam.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.IncomeProductAddDTO;
import pdp.com.appprojectexam.payload.IncomeProductInfoDTO;
import pdp.com.appprojectexam.payload.IncomeProductUpdateDTO;
import pdp.com.appprojectexam.utils.AppConstant;

import javax.validation.Valid;

@RequestMapping(InComeProductController.INCOME_PRODUCT_PATH)
public interface InComeProductController {
    String INCOME_PRODUCT_PATH = AppConstant.BASE_PATH + "/in-come-product/";
    String GET_ALL_PATH = "all";
    String ADD = "add";
    String UPDATE = "update";

    String GET_ONE = "get-one";

    @PreAuthorize(value = "hasAuthority('GET_ONE_INCOME_PRODUCT')")
    @GetMapping(GET_ONE+ "/{id}")
    ApiResult<IncomeProductInfoDTO> getOne(@PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('GET_ONE_INCOME_PRODUCT')")
    @GetMapping(GET_ALL_PATH)
    ApiResult<?> getAll(@RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size);

    @PreAuthorize(value = "hasAuthority('ADD_INCOME_PRODUCT')")
    @PostMapping(ADD)
    ApiResult<IncomeProductInfoDTO> add(@RequestBody @Valid IncomeProductAddDTO incomeProductAddDTO);


    @PreAuthorize(value = "hasAuthority('EDIT_INCOME_PRODUCT')")
    @PutMapping(UPDATE+ "/{id}")
    ApiResult<IncomeProductInfoDTO> update(@RequestBody IncomeProductUpdateDTO incomeProductUpdateDTO,
                                      @PathVariable Integer id);
}
