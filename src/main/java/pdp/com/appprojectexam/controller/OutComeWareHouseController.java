package pdp.com.appprojectexam.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.OutWarehouseAddDTO;
import pdp.com.appprojectexam.payload.OutWarehouseInfoDTO;
import pdp.com.appprojectexam.utils.AppConstant;

import javax.validation.Valid;

@RequestMapping(OutComeWareHouseController.INCOME_WAREHOUSE_PATH)
public interface OutComeWareHouseController {
    String INCOME_WAREHOUSE_PATH = AppConstant.BASE_PATH + "/out-come-warehouse/";

    String GET_ALL_PATH = "all";
    String ADD = "add";

    String UPDATE = "update";

    String GET_ONE = "get-one";

    @PreAuthorize(value = "hasAuthority('GET_ONE_OUTCOME_PRODUCT')")
    @GetMapping(GET_ONE + "/{id}")
    ApiResult<OutWarehouseInfoDTO> getOne(@PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('GET_ONE_OUTCOME_PRODUCT')")
    @GetMapping(GET_ALL_PATH)
    ApiResult<?> getAll(@RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size);

    @PreAuthorize(value = "hasAuthority('ADD_OUTCOME_WAREHOUSE')")
    @PostMapping(ADD)
    ApiResult<OutWarehouseInfoDTO> add(@RequestBody OutWarehouseAddDTO outWarehouseAddDTO);


}
