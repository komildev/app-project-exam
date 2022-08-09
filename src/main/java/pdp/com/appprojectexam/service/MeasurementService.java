package pdp.com.appprojectexam.service;

import pdp.com.appprojectexam.entity.Measurement;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.MeasurementAddDTO;
import pdp.com.appprojectexam.payload.MeasurementInfoDTO;
import pdp.com.appprojectexam.payload.MeasurementUpdateDTO;

public interface MeasurementService {
    ApiResult<MeasurementInfoDTO> getOne(Integer id);

    ApiResult<?> getAll(int page, int size);

    ApiResult<MeasurementInfoDTO> add(MeasurementAddDTO measurementAddDTO);

    ApiResult<MeasurementInfoDTO> update(MeasurementUpdateDTO measurementUpdateDTO, Integer id);

    String delete(Integer id);

    Measurement getByIdOrElseThrow(Integer measurementId);
}
