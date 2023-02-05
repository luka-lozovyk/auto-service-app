package com.autoserviceapp.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderResponseDto {
    private Long id;
    private Long carId;
    private String problemDescription;
    private LocalDateTime startDate;
    private List<Long> serviceTypeIds;
    private List<Long> sparePartIds;
    private String orderStatus;
    private BigDecimal totalPrice;
    private LocalDateTime finishDate;

    public OrderResponseDto(Long id, Long carId,
                            String problemDescription,
                            LocalDateTime startDate,
                            List<Long> serviceTypeIds,
                            List<Long> sparePartIds,
                            String orderStatus,
                            BigDecimal totalPrice,
                            LocalDateTime finishDate) {
        this.id = id;
        this.carId = carId;
        this.problemDescription = problemDescription;
        this.startDate = startDate;
        this.serviceTypeIds = serviceTypeIds;
        this.sparePartIds = sparePartIds;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
        this.finishDate = finishDate;
    }
}
