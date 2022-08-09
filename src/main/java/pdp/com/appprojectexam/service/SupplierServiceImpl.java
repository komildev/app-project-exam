package pdp.com.appprojectexam.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pdp.com.appprojectexam.entity.Supplier;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.exception.RestException;
import pdp.com.appprojectexam.mapper.SupplierMapper;
import pdp.com.appprojectexam.payload.SupplierAddDTO;
import pdp.com.appprojectexam.payload.SupplierInfoDTO;
import pdp.com.appprojectexam.payload.SupplierUpdateDTO;
import pdp.com.appprojectexam.repository.SupplierRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    @Override
    public ApiResult<SupplierInfoDTO> getOne(Integer id) {
        Supplier supplier = getByIdOrElseThrow(id);
        SupplierInfoDTO supplierInfoDTO = entityToInfoDTO(supplier);
        return ApiResult.successResponse(supplierInfoDTO);
    }

    @Override
    public ApiResult<?> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Supplier> supplierPage = supplierRepository.findAll(pageable);

        List<Supplier> supplierList = supplierPage.getContent();

        List<SupplierInfoDTO> supplierAddDTOList = supplierList
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());

        Page<SupplierInfoDTO> myPage = new PageImpl<>(
                supplierAddDTOList,
                supplierPage.getPageable(),
                supplierPage.getTotalElements()
        );

        return ApiResult.successResponse(myPage);
    }

    @Override
    public ApiResult<SupplierInfoDTO> add(SupplierAddDTO supplierAddDTO) {
        checkName(supplierAddDTO.getName());
        Supplier supplier = supplierMapper.mapAddDTOToEntity(supplierAddDTO);
        supplierRepository.save(supplier);
        return returnApiResult(supplier, true, "Succesfully added");
    }

    @Override
    public ApiResult<SupplierInfoDTO> update(SupplierUpdateDTO supplierUpdateDTO, Integer id) {
        checkName(supplierUpdateDTO.getName(), id);
        Supplier supplier = getByIdOrElseThrow(id);
        supplierMapper.updateEntity(supplierUpdateDTO, supplier);
        supplierRepository.save(supplier);
        return returnApiResult(supplier, true, "Updated");

    }

    @Override
    public String delete(Integer id) {
        Supplier supplier = getByIdOrElseThrow(id);
        supplierRepository.delete(supplier);
        return "Successfully deleted";
    }


    public Supplier getByIdOrElseThrow(Integer supplierId) {
        return supplierRepository.findById(supplierId).orElseThrow(() -> RestException.notFound("Supplier"));
    }

    private SupplierInfoDTO entityToInfoDTO(Supplier supplier) {
        SupplierInfoDTO supplierInfoDTO = supplierMapper.mapEntityToInfoDTO(supplier);
        return supplierInfoDTO;
    }

    private ApiResult<SupplierInfoDTO> returnApiResult(Supplier supplier, boolean success, String msg) {
        SupplierInfoDTO supplierInfoDTO = entityToInfoDTO(supplier);
        return new ApiResult<>(supplierInfoDTO, success, msg);
    }

    private void checkName(String name, Integer id) {
        boolean exist = supplierRepository.existsByNameAndId(name, id);
        if (exist) throw RestException.alreadyExist("Supplier");
    }

    private void checkName(String name) {
        boolean exist = supplierRepository.existsByName(name);
        if (exist) throw RestException.alreadyExist("Supplier");
    }
}
