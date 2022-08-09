package pdp.com.appprojectexam.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.MeasurementAddDTO;
import pdp.com.appprojectexam.payload.MeasurementInfoDTO;
import pdp.com.appprojectexam.payload.MeasurementUpdateDTO;
import pdp.com.appprojectexam.utils.AppConstant;

@RequestMapping(MeasurementController.CURRENCY_CONTROLLER_PATH)
public interface MeasurementController {

    String CURRENCY_CONTROLLER_PATH = AppConstant.BASE_PATH + "/measurement/";

    String ADD = "add";

    String ALL = "all";

    String UPDATE = "update";

    String DELETE = "delete";

    String GET_ONE = "get-one";

    @PreAuthorize(value = "hasAuthority('VIEW_MEASUREMENT')")
    @GetMapping(GET_ONE + "/{id}")
    ApiResult<MeasurementInfoDTO> getOne(@PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('VIEW_MEASUREMENT')")
    @GetMapping(ALL)
    ApiResult<?> getAll(@RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size);

    @PreAuthorize(value = "hasAuthority('ADD_MEASUREMENT')")
    @PostMapping(ADD)
    ApiResult<MeasurementInfoDTO> add(@RequestBody MeasurementAddDTO measurementAddDTO);

    @PreAuthorize(value = "hasAuthority('EDIT_MEASUREMENT')")
    @PutMapping(UPDATE + "/{id}")
    ApiResult<MeasurementInfoDTO> update(@RequestBody MeasurementUpdateDTO measurementUpdateDTO,
                                         @PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('DELETE_MEASUREMENT')")
    @DeleteMapping(DELETE + "/{id}")
    String delete(@PathVariable Integer id);
}
