package pdp.com.appprojectexam.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.*;
import pdp.com.appprojectexam.utils.AppConstant;

import javax.validation.Valid;

@RequestMapping(OutComeProductController.OUTCOME_PRODUCT_PATH)
public interface OutComeProductController {
    String OUTCOME_PRODUCT_PATH = AppConstant.BASE_PATH + "/out-come-product/";
    String GET_ALL_PATH = "all";
    String ADD = "add";
    String UPDATE = "update";

    String GET_ONE = "get-one";

    @PreAuthorize(value = "hasAuthority('GET_ONE_OUTCOME_PRODUCT')")
    @GetMapping(GET_ONE+ "/{id}")
    ApiResult<OutcomeProductInfoDTO> getOne(@PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('GET_ONE_OUTCOME_PRODUCT')")
    @GetMapping(GET_ALL_PATH)
    ApiResult<?> getAll(@RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size);

    @PreAuthorize(value = "hasAuthority('ADD_OUTCOME_PRODUCT')")
    @PostMapping(ADD)
    ApiResult<OutcomeProductInfoDTO> add(@RequestBody @Valid OutcomeProductAddDTO outcomeProductAddDTO);


    @PreAuthorize(value = "hasAuthority('EDIT_OUTCOME_PRODUCT')")
    @PutMapping(UPDATE+ "/{id}")
    ApiResult<OutcomeProductInfoDTO> update(@RequestBody OutcomeProductUpdateDTO outcomeProductUpdateDTO,
                                      @PathVariable Integer id);
}
