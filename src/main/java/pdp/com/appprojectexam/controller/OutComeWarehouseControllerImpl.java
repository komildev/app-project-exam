package pdp.com.appprojectexam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import pdp.com.appprojectexam.controller.OutComeWareHouseController;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.OutWarehouseAddDTO;
import pdp.com.appprojectexam.payload.OutWarehouseInfoDTO;
import pdp.com.appprojectexam.service.OutcomeWarehouseService;

@RestController
@RequiredArgsConstructor
public class OutComeWarehouseControllerImpl implements OutComeWareHouseController {

    private final OutcomeWarehouseService outcomeWarehouseService;

    @Override
    public ApiResult<OutWarehouseInfoDTO> getOne(Integer id) {
        return outcomeWarehouseService.getOne(id);
    }

    @Override
    public ApiResult<?> getAll(int page, int size) {
        return outcomeWarehouseService.getAll(page,size);
    }

    @Override
    public ApiResult<OutWarehouseInfoDTO> add(OutWarehouseAddDTO outWarehouseAddDTO) {
        return outcomeWarehouseService.add(outWarehouseAddDTO);
    }
}
