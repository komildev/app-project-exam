package pdp.com.appprojectexam.service;

import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.CurrencyAddDTO;
import pdp.com.appprojectexam.payload.CurrencyInfoDTO;
import pdp.com.appprojectexam.payload.CurrencyUpdateDTO;

public interface CurrencyService {
    ApiResult<?> getAll(int page, int size);

    ApiResult<CurrencyInfoDTO> add(CurrencyAddDTO currencyAddDTO);

    ApiResult<CurrencyInfoDTO> update(CurrencyUpdateDTO currencyUpdateDTO, Integer id);

    String delete(Integer id);

    ApiResult<CurrencyInfoDTO> getOne(Integer id);
}
