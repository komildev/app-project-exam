package pdp.com.appprojectexam.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.CurrencyAddDTO;
import pdp.com.appprojectexam.payload.CurrencyInfoDTO;
import pdp.com.appprojectexam.payload.CurrencyUpdateDTO;
import pdp.com.appprojectexam.payload.IncomeProductInfoDTO;
import pdp.com.appprojectexam.utils.AppConstant;

@RequestMapping(CurrencyController.CURRENCY_CONTROLLER_PATH)
public interface CurrencyController {

    String CURRENCY_CONTROLLER_PATH = AppConstant.BASE_PATH + "/currency/";

    String ADD = "add";

    String ALL = "all";

    String UPDATE = "update";

    String DELETE = "delete";

    String GET_ONE = "get-one";

    @PreAuthorize(value = "hasAuthority('VIEW_CURRENCY')")
    @GetMapping(GET_ONE+ "/{id}")
    ApiResult<CurrencyInfoDTO> getOne(@PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('VIEW_CURRENCY')")
    @GetMapping(ALL)
    ApiResult<?>getAll(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size);
    @PreAuthorize(value = "hasAuthority('ADD_CURRENCY')")
    @PostMapping(ADD)
    ApiResult<CurrencyInfoDTO> add(@RequestBody CurrencyAddDTO currencyAddDTO);

    @PreAuthorize(value = "hasAuthority('EDIT_CURRENCY')")
    @PutMapping(UPDATE + "/{id}")
    ApiResult<CurrencyInfoDTO> update(@RequestBody CurrencyUpdateDTO currencyUpdateDTO,
                                      @PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('DELETE_CURRENCY')")
    @DeleteMapping(DELETE + "/{id}")
    String delete(@PathVariable Integer id);
}
