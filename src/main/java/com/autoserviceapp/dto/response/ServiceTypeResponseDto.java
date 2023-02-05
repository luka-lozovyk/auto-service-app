package com.autoserviceapp.dto.response;

import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServiceTypeResponseDto {
    private Long id;
    private Long orderId;
    private Long mechanicId;
    private BigDecimal price;
    private String salaryStatus;

    public ServiceTypeResponseDto(Long id,
                                  Long orderId,
                                  Long mechanicId,
                                  BigDecimal price,
                                  String salaryStatus) {
        this.id = id;
        this.orderId = orderId;
        this.mechanicId = mechanicId;
        this.price = price;
        this.salaryStatus = salaryStatus;
    }
}
