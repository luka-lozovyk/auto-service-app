package com.autoserviceapp.dto.response;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OwnerResponseDto {
    private Long id;
    private List<Long> carIds;
    private List<Long> orderIds;

    public OwnerResponseDto(Long id, List<Long> carIds, List<Long> orderIds) {
        this.id = id;
        this.carIds = carIds;
        this.orderIds = orderIds;
    }
}
