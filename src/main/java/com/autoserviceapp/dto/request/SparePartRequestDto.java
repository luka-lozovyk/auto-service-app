package com.autoserviceapp.dto.request;

import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SparePartRequestDto {
    private String name;
    private BigDecimal price;

    public SparePartRequestDto(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}
