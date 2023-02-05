package com.autoserviceapp.dto.mapper.impl;


import com.autoserviceapp.dto.request.SparePartRequestDto;
import com.autoserviceapp.dto.response.SparePartResponseDto;
import com.autoserviceapp.model.SparePart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class SparePartDtoMapperImplTest {
    private static final SparePartRequestDto TEST_SPARE_PART_REQUEST_DTO =
            new SparePartRequestDto("Air filter", BigDecimal.valueOf(500));
    private static final  SparePart TEST_SPARE_PART_MODEL =
            new SparePart(77L, "Breaks", BigDecimal.valueOf(130L));

    @InjectMocks
    private SparePartDtoMapperImpl sparePartDtoMapper;// = new SparePartDtoMapperImpl();

    @Test
    void shouldMapSparePartModelFromDto() {
        SparePart expected = new SparePart(null, TEST_SPARE_PART_REQUEST_DTO.getName(),
                TEST_SPARE_PART_REQUEST_DTO.getPrice());
        SparePart actual = sparePartDtoMapper.mapToModel(TEST_SPARE_PART_REQUEST_DTO);

        Assertions.assertEquals(actual.getId(), expected.getId());
        Assertions.assertEquals(actual.getName(), expected.getName());
        Assertions.assertEquals(actual.getPrice(), expected.getPrice());
    }

    @Test
    void shouldMapSparePartResponseDtoFromModel() {
        SparePartResponseDto expected = new SparePartResponseDto(TEST_SPARE_PART_MODEL.getId(),
                TEST_SPARE_PART_MODEL.getName(), TEST_SPARE_PART_MODEL.getPrice());
        SparePartResponseDto actual = sparePartDtoMapper.mapToDto(TEST_SPARE_PART_MODEL);

        Assertions.assertEquals(actual.getId(), expected.getId());
        Assertions.assertEquals(actual.getName(), expected.getName());
        Assertions.assertEquals(actual.getPrice(), expected.getPrice());
    }
}