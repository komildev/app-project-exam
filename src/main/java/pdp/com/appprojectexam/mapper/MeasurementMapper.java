package pdp.com.appprojectexam.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import pdp.com.appprojectexam.entity.Measurement;
import pdp.com.appprojectexam.payload.*;

@Mapper(componentModel = "spring")
public interface MeasurementMapper {

    void updateEntity(MeasurementUpdateDTO measurementUpdateDTO, @MappingTarget Measurement measurement);

    MeasurementInfoDTO mapEntityToInfoDTO(Measurement measurement);

    Measurement status(Measurement measurement);

    Measurement mapAddDTOToEntity(MeasurementAddDTO measurementAddDTO);

}
