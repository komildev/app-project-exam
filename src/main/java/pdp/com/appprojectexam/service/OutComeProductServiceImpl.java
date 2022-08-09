package pdp.com.appprojectexam.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pdp.com.appprojectexam.entity.Measurement;
import pdp.com.appprojectexam.entity.OutcomeProduct;
import pdp.com.appprojectexam.entity.OutcomeWarehouse;
import pdp.com.appprojectexam.entity.Product;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.exception.RestException;
import pdp.com.appprojectexam.payload.OutcomeProductAddDTO;
import pdp.com.appprojectexam.payload.OutcomeProductInfoDTO;
import pdp.com.appprojectexam.payload.OutcomeProductUpdateDTO;
import pdp.com.appprojectexam.repository.OutcomeProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OutComeProductServiceImpl implements OutComeProductService {
    private final ProductService productService;
    private final OutcomeWarehouseServiceImpl outcomeWarehouseService;
    private final MeasurementService measurementService;
    private final OutcomeProductRepository outcomeProductRepository;
    private final HaveProductService haveProductService;


    @Override
    public ApiResult<OutcomeProductInfoDTO> getOne(Integer id) {
        OutcomeProduct outcomeProduct = getByIdOrElseThrow(id);
        OutcomeProductInfoDTO outcomeProductInfoDTO = entityToInfoDTO(outcomeProduct);
        return ApiResult.successResponse(outcomeProductInfoDTO);
    }

    @Override
    public ApiResult<List<OutcomeProductInfoDTO>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<OutcomeProduct> outcomeProductPage = outcomeProductRepository.findAll(pageable);
        List<OutcomeProduct> outcomeProductList = outcomeProductPage.getContent();
        List<OutcomeProductInfoDTO> outcomeProductInfoDTOS = outcomeProductList
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());
        return ApiResult.successResponse(outcomeProductInfoDTOS);
    }

    @Override
    public ApiResult<OutcomeProductInfoDTO> add(OutcomeProductAddDTO outcomeProductAddDTO) {
        Measurement measurement = measurementService.getByIdOrElseThrow(outcomeProductAddDTO.getMeasurementId());
        Product product = productService.getByIdOrElseThrow(outcomeProductAddDTO.getProductId());
        OutcomeWarehouse outcomeWarehouse = outcomeWarehouseService.getByIdOrElseThrow(outcomeProductAddDTO.getOutcomeWarehouseId());
        boolean haveProduct = haveProductService.getProduct(product.getId(),outcomeProductAddDTO.getMeasurementAmount());

        if (haveProduct) {
            OutcomeProduct outcomeProduct = new OutcomeProduct(
                    product,
                    outcomeWarehouse,
                    measurement,
                    outcomeProductAddDTO.getMeasurementAmount(),
                    outcomeProductAddDTO.getPrice()
            );
            outcomeProductRepository.save(outcomeProduct);
            return ApiResult.successResponse(entityToInfoDTO(outcomeProduct));
        } else {
            throw RestException.notFound(product.getName());
        }
    }



    @Override
    public ApiResult<OutcomeProductInfoDTO> update(OutcomeProductUpdateDTO outcomeProductUpdateDTO, Integer id) {
        OutcomeProduct outcomeProduct = getByIdOrElseThrow(id);
        Product product = productService.getByIdOrElseThrow(outcomeProductUpdateDTO.getProductId());
        Measurement measurement = measurementService.getByIdOrElseThrow(outcomeProductUpdateDTO.getMeasurementId());
        outcomeProduct.setProduct(product);
        outcomeProduct.setMeasurement(measurement);
        outcomeProduct.setPrice(outcomeProductUpdateDTO.getPrice());
        outcomeProduct.setMeasurementAmount(outcomeProductUpdateDTO.getMeasurementAmount());
        outcomeProductRepository.save(outcomeProduct);
        return ApiResult.successResponse(entityToInfoDTO(outcomeProduct));
    }

    public OutcomeProduct getByIdOrElseThrow(Integer id) {
        return outcomeProductRepository.findById(id).orElseThrow(
                () -> RestException.notFound("OutcomeProduct")
        );
    }


    public OutcomeProductInfoDTO entityToInfoDTO(OutcomeProduct outcomeProduct) {
        return new OutcomeProductInfoDTO(
                outcomeProduct.getId(),
                outcomeProduct.getOutcomeWarehouse().getId(),
                outcomeProduct.getMeasurement().getId(),
                outcomeProduct.getMeasurementAmount(),
                outcomeProduct.getPrice());
    }
}
