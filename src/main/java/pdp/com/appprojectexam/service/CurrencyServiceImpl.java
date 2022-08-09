package pdp.com.appprojectexam.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pdp.com.appprojectexam.entity.Currency;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.exception.RestException;
import pdp.com.appprojectexam.mapper.CurrencyMapper;
import pdp.com.appprojectexam.payload.CurrencyAddDTO;
import pdp.com.appprojectexam.payload.CurrencyInfoDTO;
import pdp.com.appprojectexam.payload.CurrencyUpdateDTO;
import pdp.com.appprojectexam.repository.CurrencyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository currencyRepository;

    private final CurrencyMapper currencyMapper;

    @Override
    public ApiResult<CurrencyInfoDTO> getOne(Integer id) {
        Currency currency = getByIdOrElseThrow(id);
        CurrencyInfoDTO currencyInfoDTO = entityToInfoDTO(currency);
        return ApiResult.successResponse(currencyInfoDTO);

    }

    @Override
    public ApiResult<?> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Currency> currencyPage = currencyRepository.findAll(pageable);

        List<Currency> currenciesList = currencyPage.getContent();

        List<CurrencyInfoDTO> categoryDTOList = currenciesList
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());

        Page<CurrencyInfoDTO> myPage = new PageImpl<>(
                categoryDTOList,
                currencyPage.getPageable(),
                currencyPage.getTotalElements()
        );

        return ApiResult.successResponse(myPage);
    }

    @Override
    public ApiResult<CurrencyInfoDTO> add(CurrencyAddDTO currencyAddDTO) {
        checkName(currencyAddDTO.getName());
        Currency currency = currencyMapper.mapAddToEntity(currencyAddDTO);
        currencyRepository.save(currency);
        return returnApiResult(currency, true, "success");
    }

    @Override
    public ApiResult<CurrencyInfoDTO> update(CurrencyUpdateDTO currencyUpdateDTO, Integer id) {
        checkName(currencyUpdateDTO.getName(), id);
        Currency currency = getByIdOrElseThrow(id);
        currencyMapper.updateEntity(currencyUpdateDTO, currency);
        currencyRepository.save(currency);
        return returnApiResult(currency, true, "success");
    }

    @Override
    public String delete(Integer id) {
        Currency currency = getByIdOrElseThrow(id);
        currencyRepository.delete(currency);
        return "Success";
    }


    public Currency getByIdOrElseThrow(Integer currencyId) {
        return currencyRepository.findById(currencyId).orElseThrow(()
                -> RestException.notFound("Currency"));
    }

    public CurrencyInfoDTO entityToInfoDTO(Currency currency) {
          CurrencyInfoDTO currencyInfoDTO = currencyMapper.mapEntityToInfoDTO(currency);
//        CurrencyInfoDTO currencyInfoDTO = new CurrencyInfoDTO(
//                currency.getName(),
//                currency.isStatus()
//        );
        return currencyInfoDTO;
    }

    private ApiResult<CurrencyInfoDTO> returnApiResult(Currency currency, boolean success, String msg) {
        CurrencyInfoDTO currencyInfoDTO = entityToInfoDTO(currency);
        return new ApiResult<>(currencyInfoDTO, success, msg);
    }

    private void checkName(String name, Integer id) {
        boolean exists = currencyRepository.existsByNameAndIdAndStatusTrue(name, id);
        if (exists) throw RestException.alreadyExist("Currency");
    }

    private void checkName(String name) {
        boolean exists = currencyRepository.existsByNameAndStatusTrue(name);
        if (exists) throw RestException.alreadyExist("Currency");
    }
}
