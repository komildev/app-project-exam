package pdp.com.appprojectexam.controller;

import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;
import pdp.com.appprojectexam.controller.OutComeProductController;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.*;
import pdp.com.appprojectexam.service.OutComeProductService;

@RestController
//@RequiredArgsConstructor
public class OutComeProductControllerImpl implements OutComeProductController {

    public OutComeProductControllerImpl(@Lazy OutComeProductService outComeProductService) {
        this.outComeProductService = outComeProductService;
    }

    private final OutComeProductService outComeProductService;

    @Override
    public ApiResult<OutcomeProductInfoDTO> getOne(Integer id) {
        return outComeProductService.getOne(id);
    }

    @Override
    public ApiResult<?> getAll(int page, int size) {
        return outComeProductService.getAll(page, size);
    }

    @Override
    public ApiResult<OutcomeProductInfoDTO> add(OutcomeProductAddDTO incomeProductAddDTO) {
        return outComeProductService.add(incomeProductAddDTO);
    }

    @Override
    public ApiResult<OutcomeProductInfoDTO> update(OutcomeProductUpdateDTO outcomeProductUpdateDTO, Integer id) {
        return outComeProductService.update(outcomeProductUpdateDTO,id);
    }

}
