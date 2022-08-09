package pdp.com.appprojectexam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import pdp.com.appprojectexam.controller.MeasurementController;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.MeasurementAddDTO;
import pdp.com.appprojectexam.payload.MeasurementInfoDTO;
import pdp.com.appprojectexam.payload.MeasurementUpdateDTO;
import pdp.com.appprojectexam.service.MeasurementService;

@RestController
@RequiredArgsConstructor
public class MeasurementControllerImpl implements MeasurementController {

    private final MeasurementService measurementService;

    @Override
    public ApiResult<MeasurementInfoDTO> getOne(Integer id) {
        return measurementService.getOne(id);
    }

    @Override
    public ApiResult<?> getAll(int page, int size) {
        return measurementService.getAll(page,size);
    }

    @Override
    public ApiResult<MeasurementInfoDTO> add(MeasurementAddDTO measurementAddDTO) {
        return measurementService.add(measurementAddDTO);
    }

    @Override
    public ApiResult<MeasurementInfoDTO> update(MeasurementUpdateDTO measurementUpdateDTO, Integer id) {
        return measurementService.update(measurementUpdateDTO,id);
    }

    @Override
    public String delete(Integer id) {
        return measurementService.delete(id);
    }
}
