package pdp.com.appprojectexam.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pdp.com.appprojectexam.entity.*;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.exception.RestException;
import pdp.com.appprojectexam.mapper.ProductMapper;
import pdp.com.appprojectexam.payload.ProductAddDTO;
import pdp.com.appprojectexam.payload.ProductInfoDTO;
import pdp.com.appprojectexam.payload.ProductUpdateDTO;
import pdp.com.appprojectexam.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryServiceImpl categoryService;
    private final MeasurementServiceImpl measurementService;

    private final AttachmentServiceImpl attachmentService;

    @Override
    public ApiResult<ProductInfoDTO> getOne(Integer id) {
        Product product = getByIdOrElseThrow(id);
        ProductInfoDTO productInfoDTO = entityToInfoDTO(product);
        return ApiResult.successResponse(productInfoDTO);    }

    @Override
    public ApiResult<?> getAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Product> productPage = productRepository.findAll(pageable);

        List<Product> productList = productPage.getContent();

        List<ProductInfoDTO> productDTOList = productList
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());

        Page<ProductInfoDTO> myPage = new PageImpl<>(
                productDTOList,
                productPage.getPageable(),
                productPage.getTotalElements()
        );

        return ApiResult.successResponse(myPage);

    }

    @Override
    public ApiResult<ProductInfoDTO> add(ProductAddDTO productAddDTO) {
        checkName(productAddDTO.getName());
        Measurement measurement = measurementService.getByIdOrElseThrow(productAddDTO.getMeasurementId());
        Category category = categoryService.getByIdOrElseThrow(productAddDTO.getCategoryId());
        Attachment attachment = attachmentService.getAttachmentByIdOrElseThrow(productAddDTO.getAttachmentId());
        Product product = new Product(
                productAddDTO.getName(),
                category,
                attachment,
                generateVerificationCode(),
                measurement
        );
        productRepository.save(product);
        return returnApiResult(product, true, "success");

    }

    @Override
    public ApiResult<ProductInfoDTO> update(ProductUpdateDTO productUpdateDTO, Integer id) {
        checkName(productUpdateDTO.getName(), id);
        Product product = getByIdOrElseThrow(id);
        productMapper.updateEntity(productUpdateDTO, product);
        productRepository.save(product);
        return returnApiResult(product, true, "Updated");
    }

    @Override
    public String delete(Integer id) {
        Product product = getByIdOrElseThrow(id);
        productRepository.delete(product);
        return "Deleted";
    }


    @Override
    public Product getByIdOrElseThrow(Integer productId) {
        return productRepository.findById(productId).orElseThrow(
                () -> RestException.notFound("Product"));
    }

    private ProductInfoDTO entityToInfoDTO(Product product) {
        ProductInfoDTO productInfoDTO = productMapper.mapEntityToInfoDTO(product);
        return productInfoDTO;
    }

    private ApiResult<ProductInfoDTO> returnApiResult(Product product, boolean success, String msg) {
        ProductInfoDTO productInfoDTO = entityToInfoDTO(product);
        return new ApiResult<>(productInfoDTO, success, msg);
    }

    public String generateVerificationCode() {
        String code = String.valueOf((int) (Math.random() * 1_000_000_000));
        return code.substring(0, 6);
    }

    private void checkName(String name, Integer id) {
        boolean exists = productRepository.existsByNameAndId(name, id);
        if (exists) throw RestException.alreadyExist("Product");
    }

    private void checkName(String name) {
        boolean exists = productRepository.existsByName(name);
        if (exists) throw RestException.alreadyExist("Product");
    }


}
