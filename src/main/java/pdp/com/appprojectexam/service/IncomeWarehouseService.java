package pdp.com.appprojectexam.service;

import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.InWarehouseAddDTO;
import pdp.com.appprojectexam.payload.InWarehouseInfoDTO;

public interface IncomeWarehouseService {

    ApiResult<InWarehouseInfoDTO> add(InWarehouseAddDTO inWarehouseAddDTO);
}
