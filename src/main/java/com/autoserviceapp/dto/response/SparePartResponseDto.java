package com.autoserviceapp.dto.response;

import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SparePartResponseDto {
    private Long id;
    private String name;
    private BigDecimal price;

    public SparePartResponseDto(Long id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
