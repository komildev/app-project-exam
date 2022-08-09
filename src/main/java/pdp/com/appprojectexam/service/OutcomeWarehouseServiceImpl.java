package pdp.com.appprojectexam.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pdp.com.appprojectexam.entity.Currency;
import pdp.com.appprojectexam.entity.OutcomeWarehouse;
import pdp.com.appprojectexam.entity.WareHouse;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.exception.RestException;
import pdp.com.appprojectexam.payload.OutWarehouseAddDTO;
import pdp.com.appprojectexam.payload.OutWarehouseInfoDTO;
import pdp.com.appprojectexam.repository.OutcomeWarehouseRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OutcomeWarehouseServiceImpl implements OutcomeWarehouseService {
    private final OutcomeWarehouseRepository outcomeWarehouseRepository;
    private final WareHouseServiceImpl wareHouseService;
    private final CurrencyServiceImpl currencyService;

    public OutcomeWarehouseServiceImpl(@Lazy OutcomeWarehouseRepository outcomeWarehouseRepository,
                                       @Lazy WareHouseServiceImpl wareHouseService,
                                       @Lazy CurrencyServiceImpl currencyService) {
        this.outcomeWarehouseRepository = outcomeWarehouseRepository;
        this.wareHouseService = wareHouseService;
        this.currencyService = currencyService;
    }

    @Override
    public ApiResult<OutWarehouseInfoDTO> getOne(Integer id) {
        OutcomeWarehouse outcomeWarehouse = getByIdOrElseThrow(id);
        OutWarehouseInfoDTO outWarehouseInfoDTO = entityToInfoDTO(outcomeWarehouse);
        return ApiResult.successResponse(outWarehouseInfoDTO);
    }

    @Override
    public ApiResult<?> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<OutcomeWarehouse> page1 = outcomeWarehouseRepository.findAll(pageable);

        List<OutcomeWarehouse> outcomeList = page1.getContent();

        List<OutWarehouseInfoDTO> outWarehouseInfoDTOS = outcomeList
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());

        Page<OutWarehouseInfoDTO> myPage = new PageImpl<>(
                outWarehouseInfoDTOS,
                page1.getPageable(),
                page1.getTotalElements()
        );

        return ApiResult.successResponse(myPage);
    }


    @Override
    public ApiResult<OutWarehouseInfoDTO> add(OutWarehouseAddDTO outWarehouseAddDTO) {
        WareHouse warehouse = wareHouseService.getByIdOrElseThrow(outWarehouseAddDTO.getWareHouseId());
        Currency currency = currencyService.getByIdOrElseThrow(outWarehouseAddDTO.getCurrencyId());
        OutcomeWarehouse outcomeWarehouse = new OutcomeWarehouse(
                warehouse,
                currency,
                randomUniqueId()
        );
        outcomeWarehouseRepository.save(outcomeWarehouse);
        return ApiResult.successResponse(entityToInfoDTO(outcomeWarehouse));
    }

    public OutWarehouseInfoDTO entityToInfoDTO(OutcomeWarehouse outcomeWarehouse) {
        return new OutWarehouseInfoDTO(
                outcomeWarehouse.getWareHouse().getId(),
                outcomeWarehouse.getCurrency().getId()
        );
    }

    public OutcomeWarehouse getByIdOrElseThrow(Integer wareHouseId) {
        return outcomeWarehouseRepository.findById(wareHouseId).orElseThrow(
                () -> RestException.notFound("Outcome Warehouse")
        );
    }

    private String randomUniqueId() {
        String code = String.valueOf((int) (Math.random() * 1_000_000_000));
        return code.substring(0, 6);
    }

}
