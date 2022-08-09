package pdp.com.appprojectexam.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pdp.com.appprojectexam.entity.WareHouse;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.exception.RestException;
import pdp.com.appprojectexam.mapper.WarehouseMapper;
import pdp.com.appprojectexam.payload.WareHouseAddDTO;
import pdp.com.appprojectexam.payload.WareHouseInfoDTO;
import pdp.com.appprojectexam.repository.WareHouseRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WareHouseServiceImpl implements WareHouseService {

    private final WareHouseRepository wareHouseRepository;

    private final WarehouseMapper warehouseMapper;

    @Override
    public ApiResult<?> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<WareHouse> wareHousePage = wareHouseRepository.findAll(pageable);

        List<WareHouse> warehouseList = wareHousePage.getContent();

        List<WareHouseInfoDTO> wareHouseInfoDTOS = warehouseList
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());

        Page<WareHouseInfoDTO> myPage = new PageImpl<>(
                wareHouseInfoDTOS,
                wareHousePage.getPageable(),
                wareHousePage.getTotalElements()
        );

        return ApiResult.successResponse(myPage);
    }

    @Override
    public ApiResult<WareHouseInfoDTO> add(WareHouseAddDTO wareHouseDTO) {
        checkName(wareHouseDTO.getName());
        WareHouse wareHouse = warehouseMapper.mapAddDTOToEntity(wareHouseDTO);
        wareHouseRepository.save(wareHouse);
        return returnApiResult(wareHouse, true, "Succesfully added");
    }

    @Override
    public ApiResult<WareHouse> update(WareHouseAddDTO wareHouseDTO, Integer id) {
        return null;
    }

    @Override
    public String delete(Integer id) {
        WareHouse wareHouse = getByIdOrElseThrow(id);
        wareHouseRepository.delete(wareHouse);
        return "Successfully deleted";
    }

    @Override
    public ApiResult<WareHouseAddDTO> getOne(Integer id) {
        WareHouse wareHouse = getByIdOrElseThrow(id);
        WareHouseAddDTO wareHouseInfoDTO = entity(wareHouse);
        return ApiResult.successResponse(wareHouseInfoDTO);
    }


    public WareHouse getByIdOrElseThrow(Integer warehouseId) {
        return wareHouseRepository.findById(warehouseId).orElseThrow(() -> RestException.notFound("WareHouse"));
    }


    private WareHouseInfoDTO entityToInfoDTO(WareHouse wareHouse) {
        WareHouseInfoDTO wareHouseInfoDTO = warehouseMapper.mapEntityToInfoDTO(wareHouse);
        return wareHouseInfoDTO;
    }

    private WareHouseAddDTO entity(WareHouse wareHouse) {
        WareHouseAddDTO wareHouseAddDTO = new WareHouseAddDTO(
                wareHouse.getName()
        );
        return wareHouseAddDTO;
    }

    private ApiResult<WareHouseInfoDTO> returnApiResult(WareHouse warehouse, boolean success, String msg) {
        WareHouseInfoDTO warehouseInfoDTO = entityToInfoDTO(warehouse);
        return new ApiResult<>(warehouseInfoDTO, success, msg);
    }

    private void checkName(String name, Integer id) {
        boolean exist = wareHouseRepository.existsByNameAndIdAndStatusTrue(name, id);
        if (exist) throw RestException.alreadyExist("Warehouse");
    }


    private void checkName(String name) {
        boolean exist = wareHouseRepository.existsByNameAndStatusTrue(name);
        if (exist) throw RestException.alreadyExist("Warehouse");
    }
}
