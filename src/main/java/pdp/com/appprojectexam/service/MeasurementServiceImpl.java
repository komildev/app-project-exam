package pdp.com.appprojectexam.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pdp.com.appprojectexam.entity.Measurement;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.exception.RestException;
import pdp.com.appprojectexam.mapper.MeasurementMapper;
import pdp.com.appprojectexam.payload.MeasurementAddDTO;
import pdp.com.appprojectexam.payload.MeasurementInfoDTO;
import pdp.com.appprojectexam.payload.MeasurementUpdateDTO;
import pdp.com.appprojectexam.repository.MeasurementRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeasurementServiceImpl implements MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final MeasurementMapper measurementMapper;

    @Override
    public ApiResult<MeasurementInfoDTO> getOne(Integer id) {
        Measurement measurement = getByIdOrElseThrow(id);
        MeasurementInfoDTO measurementInfoDTO = entityToInfoDTO(measurement);
        return ApiResult.successResponse(measurementInfoDTO);
    }

    @Override
    public ApiResult<?> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Measurement> measurementPage = measurementRepository.findAll(pageable);

        List<Measurement> measurementsList = measurementPage.getContent();

        List<MeasurementInfoDTO> measurementInfoDTOList = measurementsList
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());

        Page<MeasurementInfoDTO> myPage = new PageImpl<>(
                measurementInfoDTOList,
                measurementPage.getPageable(),
                measurementPage.getTotalElements()
        );

        return ApiResult.successResponse(myPage);
    }

    @Override
    public ApiResult<MeasurementInfoDTO> add(MeasurementAddDTO measurementAddDTO) {
        checkName(measurementAddDTO.getName());
        Measurement measurement=measurementMapper.mapAddDTOToEntity(measurementAddDTO);
        measurementRepository.save(measurement);
        return returnApiResult(measurement,true,"success");
    }

    @Override
    public ApiResult<MeasurementInfoDTO> update(MeasurementUpdateDTO measurementUpdateDTO, Integer id) {
        checkName(measurementUpdateDTO.getName(), id);
        Measurement measurement = getByIdOrElseThrow(id);
        measurementMapper.updateEntity(measurementUpdateDTO, measurement);
        measurementRepository.save(measurement);
        return returnApiResult(measurement, true, "success");
    }

    @Override
    public String delete(Integer id) {
        Measurement measurement = getByIdOrElseThrow(id);
        measurementRepository.delete(measurement);
        return "Success";
    }

    public Measurement getByIdOrElseThrow(Integer measurementId) {
        return measurementRepository.findById(measurementId).orElseThrow(()
                -> RestException.notFound("Measurement"));
    }

    public MeasurementInfoDTO entityToInfoDTO(Measurement measurement) {
        MeasurementInfoDTO measurementInfoDTO = measurementMapper.mapEntityToInfoDTO(measurement);
        return measurementInfoDTO;
    }

    private ApiResult<MeasurementInfoDTO> returnApiResult(Measurement measurement, boolean success, String msg) {
        MeasurementInfoDTO measurementInfoDTO = entityToInfoDTO(measurement);
        return new ApiResult<>(measurementInfoDTO, success, msg);
    }

    private void checkName(String name, Integer id) {
        boolean exists = measurementRepository.existsByNameAndIdAndStatusTrue(name, id);
        if (exists) throw RestException.alreadyExist("Measurement");
    }

    private void checkName(String name) {
        boolean exists = measurementRepository.existsByNameAndStatusTrue(name);
        if (exists) throw RestException.alreadyExist("Measurement");
    }

}
