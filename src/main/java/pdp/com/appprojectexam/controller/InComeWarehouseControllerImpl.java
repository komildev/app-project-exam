package pdp.com.appprojectexam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import pdp.com.appprojectexam.controller.InComeWareHouseController;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.InWarehouseAddDTO;
import pdp.com.appprojectexam.payload.InWarehouseInfoDTO;
import pdp.com.appprojectexam.service.IncomeWarehouseService;

@RestController
@RequiredArgsConstructor
public class InComeWarehouseControllerImpl implements InComeWareHouseController {

    private final IncomeWarehouseService incomeWarehouseService;

    @Override
    public ApiResult<InWarehouseInfoDTO> add(InWarehouseAddDTO inWarehouseAddDTO) {
        return incomeWarehouseService.add(inWarehouseAddDTO);
    }
}
