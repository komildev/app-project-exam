package pdp.com.appprojectexam.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pdp.com.appprojectexam.entity.*;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.exception.RestException;
import pdp.com.appprojectexam.payload.IncomeProductAddDTO;
import pdp.com.appprojectexam.payload.IncomeProductInfoDTO;
import pdp.com.appprojectexam.payload.IncomeProductUpdateDTO;
import pdp.com.appprojectexam.repository.IncomeProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
public class InComeProductServiceImpl implements InComeProductService {
    private final ProductService productService;
    private final IncomeWarehouseServiceImpl incomeWarehouseService;
    private final MeasurementService measurementService;
    private final IncomeProductRepository incomeProductRepository;

    private final HaveProductService haveProductService;

    public InComeProductServiceImpl(@Lazy ProductService productService,
                                    @Lazy IncomeWarehouseServiceImpl incomeWarehouseService,
                                    @Lazy MeasurementService measurementService,
                                    @Lazy IncomeProductRepository incomeProductRepository,
                                    @Lazy HaveProductService haveProductService) {
        this.productService = productService;
        this.incomeWarehouseService = incomeWarehouseService;
        this.measurementService = measurementService;
        this.incomeProductRepository = incomeProductRepository;
        this.haveProductService = haveProductService;
    }

    @Override
    public ApiResult<IncomeProductInfoDTO> getOne(Integer id) {
        IncomeProduct incomeProduct = getByIdOrElseThrow(id);
        IncomeProductInfoDTO incomeProductInfoDTO = entityToInfoDTO(incomeProduct);
        return ApiResult.successResponse(incomeProductInfoDTO);
    }

    @Override
    public ApiResult<List<IncomeProductInfoDTO>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<IncomeProduct> incomeProductPage = incomeProductRepository.findAll(pageable);
        List<IncomeProduct> incomeProductList = incomeProductPage.getContent();
        List<IncomeProductInfoDTO> incomeProductInfoDTOS = incomeProductList
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());
        return ApiResult.successResponse(incomeProductInfoDTOS);
    }

    @Override
    public ApiResult<IncomeProductInfoDTO> add(IncomeProductAddDTO incomeProductAddDTO) {
        Measurement measurement = measurementService.getByIdOrElseThrow(incomeProductAddDTO.getMeasurementId());
        Product product = productService.getByIdOrElseThrow(incomeProductAddDTO.getProductId());
        IncomeWarehouse incomeWarehouse = incomeWarehouseService.getByIdOrElseThrow(incomeProductAddDTO.getIncomeWarehouseId());
        IncomeProduct incomeProduct = new IncomeProduct(
                product,
                incomeWarehouse,
                measurement,
                incomeProductAddDTO.getMeasurementAmount(),
                incomeProductAddDTO.getPrice(),
                getFinalPrice(incomeProductAddDTO.getPrice(),incomeProductAddDTO.getMeasurementAmount()),
                incomeProductAddDTO.getExpirationDate()
        );
        incomeProductRepository.save(incomeProduct);
        haveProduct(product.getId(),incomeProductAddDTO.getMeasurementAmount());

        return ApiResult.successResponse(entityToInfoDTO(incomeProduct));
    }


    @Override
    public ApiResult<IncomeProductInfoDTO> update(IncomeProductUpdateDTO incomeProductUpdateDTO, Integer id) {
        IncomeProduct incomeProduct = getByIdOrElseThrow(id);
        Product product = productService.getByIdOrElseThrow(incomeProductUpdateDTO.getProductId());
        Measurement measurement = measurementService.getByIdOrElseThrow(incomeProductUpdateDTO.getMeasurementId());
        incomeProduct.setProduct(product);
        incomeProduct.setMeasurement(measurement);
        incomeProduct.setPrice(incomeProductUpdateDTO.getPrice());
        incomeProduct.setMeasurementAmount(incomeProductUpdateDTO.getMeasurementAmount());
        incomeProductRepository.save(incomeProduct);
        return ApiResult.successResponse(entityToInfoDTO(incomeProduct));
    }

    private Double getFinalPrice(Double price, Double amount) {
        return price * amount;
    }

    public IncomeProduct getByIdOrElseThrow(Integer id) {
        return incomeProductRepository.findById(id).orElseThrow(
                () -> RestException.notFound("IncomeProduct")
        );
    }

    public void haveProduct(Integer productId, Double amount) {
        Optional<HaveProducts> optionalHaveProduct = haveProductService.findById(productId);
        Product product = productService.getByIdOrElseThrow(productId);
        if (optionalHaveProduct.isEmpty()) {
            HaveProducts haveProducts = new HaveProducts(
                    product,
                    amount
            );
            haveProductService.save(haveProducts);
        } else {
            HaveProducts haveProducts = optionalHaveProduct.get();
            haveProducts.setAmount(haveProducts.getAmount() + amount);
            haveProductService.save(haveProducts);
        }
    }


    private IncomeProductInfoDTO entityToInfoDTO(IncomeProduct incomeProduct) {
        return new IncomeProductInfoDTO(
                incomeProduct.getId(),
                incomeProduct.getIncomeWarehouse().getId(),
                incomeProduct.getMeasurement().getId(),
                incomeProduct.getMeasurementAmount(),
                incomeProduct.getPrice(),
                getFinalPrice(incomeProduct.getPrice(), incomeProduct.getMeasurementAmount()),
                incomeProduct.getExpiredDate()
        );
    }
}
