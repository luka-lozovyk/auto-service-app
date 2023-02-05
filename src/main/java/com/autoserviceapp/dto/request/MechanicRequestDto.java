package com.autoserviceapp.dto.request;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MechanicRequestDto {
    private String fullName;
    private List<Long> completedOrderIds;

    public MechanicRequestDto(String fullName, List<Long> completedOrderIds) {
        this.fullName = fullName;
        this.completedOrderIds = completedOrderIds;
    }
}
