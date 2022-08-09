package pdp.com.appprojectexam.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import pdp.com.appprojectexam.entity.Currency;
import pdp.com.appprojectexam.payload.CurrencyAddDTO;
import pdp.com.appprojectexam.payload.CurrencyInfoDTO;
import pdp.com.appprojectexam.payload.CurrencyUpdateDTO;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {

    void updateEntity(CurrencyUpdateDTO currencyUpdateDTO, @MappingTarget Currency currency);

    Currency mapAddToEntity(CurrencyAddDTO currencyAddDTO);

    CurrencyInfoDTO mapEntityToInfoDTO(Currency currency);
}
