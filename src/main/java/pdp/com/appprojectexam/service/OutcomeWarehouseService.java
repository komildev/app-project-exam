package pdp.com.appprojectexam.service;

import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.OutWarehouseAddDTO;
import pdp.com.appprojectexam.payload.OutWarehouseInfoDTO;

public interface OutcomeWarehouseService {
    ApiResult<OutWarehouseInfoDTO> getOne(Integer id);

    ApiResult<?> getAll(int page, int size);

    ApiResult<OutWarehouseInfoDTO> add(OutWarehouseAddDTO outWarehouseAddDTO);
}
