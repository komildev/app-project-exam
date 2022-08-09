package pdp.com.appprojectexam.service;

import pdp.com.appprojectexam.entity.WareHouse;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.WareHouseAddDTO;
import pdp.com.appprojectexam.payload.WareHouseInfoDTO;

public interface WareHouseService {

    ApiResult<?> getAll(int page, int size);

    ApiResult<WareHouseInfoDTO> add(WareHouseAddDTO wareHouseDTO);

    ApiResult<WareHouse> update(WareHouseAddDTO wareHouseDTO, Integer id);

    String delete(Integer id);

    ApiResult<WareHouseAddDTO> getOne(Integer id);
}
