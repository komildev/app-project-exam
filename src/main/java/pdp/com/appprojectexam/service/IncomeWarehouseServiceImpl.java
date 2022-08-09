package pdp.com.appprojectexam.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pdp.com.appprojectexam.entity.*;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.exception.RestException;
import pdp.com.appprojectexam.payload.*;
import pdp.com.appprojectexam.repository.IncomeWarehouseRepository;

@Service
public class IncomeWarehouseServiceImpl implements IncomeWarehouseService {
    private final IncomeWarehouseRepository incomeWarehouseRepository;
    private final WareHouseServiceImpl wareHouseService;
    private final SupplierServiceImpl supplierService;
    private final CurrencyServiceImpl currencyService;

    public IncomeWarehouseServiceImpl(@Lazy IncomeWarehouseRepository incomeWarehouseRepository,
                                      @Lazy WareHouseServiceImpl wareHouseService,
                                      @Lazy SupplierServiceImpl supplierService,
                                      @Lazy CurrencyServiceImpl currencyService) {
        this.incomeWarehouseRepository = incomeWarehouseRepository;
        this.wareHouseService = wareHouseService;
        this.supplierService = supplierService;
        this.currencyService = currencyService;
    }

    @Override
    public ApiResult<InWarehouseInfoDTO> add(InWarehouseAddDTO inWarehouseAddDTO) {
        WareHouse wareHouse = wareHouseService.getByIdOrElseThrow(inWarehouseAddDTO.getWareHouseId());
        Supplier supplier = supplierService.getByIdOrElseThrow(inWarehouseAddDTO.getSupplierId());
        Currency currency = currencyService.getByIdOrElseThrow(inWarehouseAddDTO.getCurrencyId());
        IncomeWarehouse incomeWarehouse = new IncomeWarehouse(
                wareHouse,
                supplier,
                currency,
                Math.toIntExact(random()),
                random()
        );
        incomeWarehouseRepository.save(incomeWarehouse);
        return returnApiResult(incomeWarehouse, true, "success");

    }

    public InWarehouseInfoDTO entityToInfoDTO(IncomeWarehouse incomeWarehouse) {
        return new InWarehouseInfoDTO(
                incomeWarehouse.getWareHouse().getId(),
                incomeWarehouse.getSupplier().getId(),
                incomeWarehouse.getCurrency().getId(),
                incomeWarehouse.getFactureNumber()
        );
    }

    public IncomeWarehouse getByIdOrElseThrow(Integer wareHouseId) {
        return incomeWarehouseRepository.findById(wareHouseId).orElseThrow(
                () -> RestException.notFound("Income Warehouse")
        );
    }
    private Long random() {

        double rand = Math.random();

        Double a = rand * 1_000_000;

        return a.longValue();
    }
    private ApiResult<InWarehouseInfoDTO> returnApiResult(IncomeWarehouse incomeWarehouse, boolean success, String msg) {
        InWarehouseInfoDTO inWarehouseInfoDTO = entityToInfoDTO(incomeWarehouse);
        return new ApiResult<>(inWarehouseInfoDTO, success, msg);
    }

}
