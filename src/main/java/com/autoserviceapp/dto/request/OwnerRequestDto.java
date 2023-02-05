package com.autoserviceapp.dto.request;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OwnerRequestDto {
    private List<Long> carIds;
    private List<Long> orderIds;

    public OwnerRequestDto(List<Long> carIds, List<Long> orderIds) {
        this.carIds = carIds;
        this.orderIds = orderIds;
    }
}
