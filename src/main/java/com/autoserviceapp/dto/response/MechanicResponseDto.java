package com.autoserviceapp.dto.response;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MechanicResponseDto {
    private Long id;
    private String fullName;
    private List<Long> completedOrderIds;

    public MechanicResponseDto(Long id, String fullName, List<Long> completedOrderIds) {
        this.id = id;
        this.fullName = fullName;
        this.completedOrderIds = completedOrderIds;
    }
}
