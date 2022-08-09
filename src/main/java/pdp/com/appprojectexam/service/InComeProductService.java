package pdp.com.appprojectexam.service;

import pdp.com.appprojectexam.entity.IncomeProduct;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.IncomeProductAddDTO;
import pdp.com.appprojectexam.payload.IncomeProductInfoDTO;
import pdp.com.appprojectexam.payload.IncomeProductUpdateDTO;

import java.sql.Timestamp;
import java.util.List;

public interface InComeProductService {
    ApiResult<IncomeProductInfoDTO> getOne(Integer id);

    ApiResult<List<IncomeProductInfoDTO>> getAll(int page, int size);

    ApiResult<IncomeProductInfoDTO> add(IncomeProductAddDTO incomeProductAddDTO);

    ApiResult<IncomeProductInfoDTO> update(IncomeProductUpdateDTO incomeProductUpdateDTO, Integer id);

}
