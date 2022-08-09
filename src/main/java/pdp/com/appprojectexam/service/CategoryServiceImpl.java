package pdp.com.appprojectexam.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import pdp.com.appprojectexam.entity.Category;
import pdp.com.appprojectexam.entity.Product;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.exception.RestException;
import pdp.com.appprojectexam.mapper.CategoryMapper;
import pdp.com.appprojectexam.mapper.ProductMapper;
import pdp.com.appprojectexam.payload.*;
import pdp.com.appprojectexam.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    public static CategoryMapper categoryMapper;
    private final ProductMapper productMapper;

    @Override
    public ApiResult<CategoryInfoDTO> getOne(Integer id) {
        Category category = getByIdOrElseThrow(id);
        CategoryInfoDTO categoryInfoDTO = entityToInfoDTO(category);
        return ApiResult.successResponse(categoryInfoDTO);
    }

    @Override
    public ApiResult<?> getAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Category> categoryPage = categoryRepository.findAll(pageable);

        List<Category> categoryList = categoryPage.getContent();

        List<CategoryInfoDTO> categoryDTOList = categoryList
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());

        Page<CategoryInfoDTO> myPage = new PageImpl<>(
                categoryDTOList,
                categoryPage.getPageable(),
                categoryPage.getTotalElements()
        );

        return ApiResult.successResponse(myPage);
    }

    @Override
    public ApiResult<CategoryInfoDTO> add(CategoryAddDTO categoryAddDTO) {
        checkName(categoryAddDTO.getName());
        Category category = categoryMapper.mapAddDTOToEntity(categoryAddDTO);
        categoryRepository.save(category);
        return returnApiResult(category, true, "success");
    }

    @Override
    public ApiResult<CategoryInfoDTO> update(CategoryUpdateDTO categoryUpdateDTO, Integer id) {
        checkName(categoryUpdateDTO.getName(), id);
        Category category = getByIdOrElseThrow(id);
        categoryMapper.updateEntity(categoryUpdateDTO, category);
        categoryRepository.save(category);
        return returnApiResult(category, true, "success");
    }

    @Override
    public String delete(Integer id) {
        Category category = getByIdOrElseThrow(id);
        categoryRepository.delete(category);
        return "Success";
    }

    @Override
    public ApiResult<CategoryInfoDTO> addCategoryAndProducts(CategoryAddWithProductDTO categoryAddWithProductDTO) {
        checkName(categoryAddWithProductDTO.getName());

        Category category = categoryMapper.mapAddDTOToProductAndCategory(categoryAddWithProductDTO);
        List<Product> productList = new ArrayList<>();
        for (ProductAddDTO productAddDTO : categoryAddWithProductDTO.getProducts()) {
            Product product = productMapper.mapAddDTOToEntity(productAddDTO);
            productList.add(product);
        }
        category.setProductList(productList);

        categoryRepository.save(category);

        return returnApiResult(category, true, "success");
    }

    @Override
    public ApiResult<CategoryInfoDTO> updateCategoryProduct(CategoryEditWithProductDTO categoryUpdateDTO, Integer id) {
        Category category = getByIdOrElseThrow(id);

        for (Product product : category.getProductList()) {
            Optional<ProductEditForCategoryDTO> optionalProductEditForCategoryDTO = categoryUpdateDTO.getProducts()
                    .stream()
                    .filter(productEditForUniverDTO -> Objects.equals(productEditForUniverDTO.getId(), product.getId()))
                    .findFirst();
            if (optionalProductEditForCategoryDTO.isEmpty())
                continue;
            ProductEditForCategoryDTO productEditForCategoryDTO = optionalProductEditForCategoryDTO.get();
            category.setName(categoryUpdateDTO.getName());
            category.setUpdatedAt(category.getUpdatedAt());
            product.setName(productEditForCategoryDTO.getName());
        }
        categoryRepository.save(category);
        return returnApiResult(category, true, "success");
    }

    public Category getByIdOrElseThrow(Integer categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(()
                -> RestException.notFound("Category"));
    }

    private CategoryInfoDTO entityToInfoDTO(Category category) {
        CategoryInfoDTO categoryInfoDTO = new CategoryInfoDTO(
                category.getName(),
                category.getDescription()
                );
        return categoryInfoDTO;
    }

    private ApiResult<CategoryInfoDTO> returnApiResult(Category category, boolean success, String msg) {
        CategoryInfoDTO categoryInfoDTO = entityToInfoDTO(category);
        return new ApiResult<>(categoryInfoDTO, success, msg);
    }

    private void checkName(String name, Integer id) {
        boolean exists = categoryRepository.existsByNameAndIdAndStatusTrue(name, id);
        if (exists) throw RestException.alreadyExist("Category");
    }

    private void checkName(String name) {
        boolean exists = categoryRepository.existsByNameAndStatusTrue(name);
        if (exists) throw RestException.alreadyExist("Category");
    }


}
