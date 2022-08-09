package pdp.com.appprojectexam.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.CategoryAddDTO;
import pdp.com.appprojectexam.payload.CategoryInfoDTO;
import pdp.com.appprojectexam.payload.InWarehouseAddDTO;
import pdp.com.appprojectexam.payload.InWarehouseInfoDTO;
import pdp.com.appprojectexam.utils.AppConstant;

import javax.validation.Valid;

@RequestMapping(InComeWareHouseController.INCOME_WAREHOUSE_PATH)
public interface InComeWareHouseController {
    String INCOME_WAREHOUSE_PATH = AppConstant.BASE_PATH + "/in-come-warehouse/";

    String GET_ALL_PATH = "all";
    String ADD = "add";

    String UPDATE = "update";

    String GET_ONE = "get-one";

    @PreAuthorize(value = "hasAuthority('ADD_INCOME_WAREHOUSE')")
    @PostMapping(ADD)
    ApiResult<InWarehouseInfoDTO> add(@RequestBody @Valid InWarehouseAddDTO inWarehouseAddDTO);


}
