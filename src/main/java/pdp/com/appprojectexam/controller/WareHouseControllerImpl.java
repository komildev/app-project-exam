package pdp.com.appprojectexam.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import pdp.com.appprojectexam.controller.WareHouseController;
import pdp.com.appprojectexam.entity.WareHouse;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.WareHouseAddDTO;
import pdp.com.appprojectexam.payload.WareHouseInfoDTO;
import pdp.com.appprojectexam.service.WareHouseService;

@RestController
@RequiredArgsConstructor
public class WareHouseControllerImpl implements WareHouseController {

    private final WareHouseService wareHouseService;

    @Override
    public ApiResult<WareHouseAddDTO> getOne(Integer id) {
        return wareHouseService.getOne(id);
    }

    @Override
    public ApiResult<?> getAll(int page, int size) {
        return wareHouseService.getAll(page,size);
    }

    @Override
    public ApiResult<WareHouseInfoDTO> add(WareHouseAddDTO wareHouseDTO) {
        return wareHouseService.add(wareHouseDTO);
    }

    @Override
    public ApiResult<WareHouse> update(WareHouseAddDTO wareHouseDTO, Integer id) {
        return wareHouseService.update(wareHouseDTO,id);
    }

    @Override
    public String delete(Integer id) {
        return wareHouseService.delete(id);
    }
}
