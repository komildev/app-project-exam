package pdp.com.appprojectexam.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pdp.com.appprojectexam.entity.WareHouse;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.WareHouseAddDTO;
import pdp.com.appprojectexam.payload.WareHouseInfoDTO;
import pdp.com.appprojectexam.utils.AppConstant;


@RequestMapping(WareHouseController.WAREHOUSE_CONTROLLER_PATH)
public interface WareHouseController {
    String WAREHOUSE_CONTROLLER_PATH = AppConstant.BASE_PATH + "/warehouse/";

    String ALL = "all";

    String ADD = "add";

    String UPDATE = "update";

    String DELETE = "delete";

    String GET_ONE = "get-one";

    @PreAuthorize(value = "hasAuthority('VIEW_WAREHOUSE')")
    @GetMapping(GET_ONE)
    ApiResult<WareHouseAddDTO> getOne(@PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('VIEW_WAREHOUSE')")
    @GetMapping(ALL)
    ApiResult<?> getAll(@RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size);

    @PreAuthorize(value = "hasAuthority('ADD_WAREHOUSE')")
    @PostMapping(ADD)
    ApiResult<WareHouseInfoDTO> add(@RequestBody WareHouseAddDTO wareHouseDTO);

    @PreAuthorize(value = "hasAuthority('EDIT_WAREHOUSE')")
    @PutMapping(UPDATE + "/{id}")
    ApiResult<WareHouse> update(@RequestBody WareHouseAddDTO wareHouseDTO,
                                @PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('DELETE_WAREHOUSE')")
    @DeleteMapping(DELETE + "/{id}")
    String delete(@PathVariable Integer id);

}
