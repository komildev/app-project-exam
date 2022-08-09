package pdp.com.appprojectexam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import pdp.com.appprojectexam.controller.CurrencyController;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.CurrencyAddDTO;
import pdp.com.appprojectexam.payload.CurrencyInfoDTO;
import pdp.com.appprojectexam.payload.CurrencyUpdateDTO;
import pdp.com.appprojectexam.service.CurrencyService;

@RestController
@RequiredArgsConstructor
public class CurrencyControllerImpl implements CurrencyController {

    private final CurrencyService currencyService;

    @Override
    public ApiResult<CurrencyInfoDTO> getOne(Integer id) {
        return currencyService.getOne(id);
    }

    @Override
    public ApiResult<?> getAll(int page, int size) {
        return currencyService.getAll(page,size);
    }

    @Override
    public ApiResult<CurrencyInfoDTO> add(CurrencyAddDTO currencyAddDTO) {
        return currencyService.add(currencyAddDTO);
    }

    @Override
    public ApiResult<CurrencyInfoDTO> update(CurrencyUpdateDTO currencyUpdateDTO, Integer id) {
        return currencyService.update(currencyUpdateDTO,id);
    }

    @Override
    public String delete(Integer id) {
        return currencyService.delete(id);
    }
}
