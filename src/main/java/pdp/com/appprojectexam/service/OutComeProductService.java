package pdp.com.appprojectexam.service;

import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.OutcomeProductAddDTO;
import pdp.com.appprojectexam.payload.OutcomeProductInfoDTO;
import pdp.com.appprojectexam.payload.OutcomeProductUpdateDTO;

public interface OutComeProductService {
    ApiResult<OutcomeProductInfoDTO> getOne(Integer id);

    ApiResult<?> getAll(int page, int size);

    ApiResult<OutcomeProductInfoDTO> add(OutcomeProductAddDTO incomeProductAddDTO);

    ApiResult<OutcomeProductInfoDTO> update(OutcomeProductUpdateDTO outcomeProductUpdateDTO, Integer id);
}
