package pdp.com.appprojectexam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import pdp.com.appprojectexam.controller.InComeProductController;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.IncomeProductAddDTO;
import pdp.com.appprojectexam.payload.IncomeProductInfoDTO;
import pdp.com.appprojectexam.payload.IncomeProductUpdateDTO;
import pdp.com.appprojectexam.service.InComeProductService;

@RestController
@RequiredArgsConstructor
public class InComeProductControllerImpl implements InComeProductController {

    private final InComeProductService inComeProductService;

    @Override
    public ApiResult<IncomeProductInfoDTO> getOne(Integer id) {
        return inComeProductService.getOne(id);
    }

    @Override
    public ApiResult<?> getAll(int page, int size) {
        return inComeProductService.getAll(page, size);
    }

    @Override
    public ApiResult<IncomeProductInfoDTO> add(IncomeProductAddDTO incomeProductAddDTO) {
        return inComeProductService.add(incomeProductAddDTO);
    }

    @Override
    public ApiResult<IncomeProductInfoDTO> update(IncomeProductUpdateDTO incomeProductUpdateDTO, Integer id) {
        return inComeProductService.update(incomeProductUpdateDTO,id);
    }

}
