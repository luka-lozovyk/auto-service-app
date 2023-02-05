package com.autoserviceapp.dto.request;

import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServiceTypeRequestDto {
    private Long orderId;
    private Long mechanicId;
    private BigDecimal price;
    private String salaryStatus;

    public ServiceTypeRequestDto(Long orderId,
                                 Long mechanicId,
                                 BigDecimal price,
                                 String salaryStatus) {
        this.orderId = orderId;
        this.mechanicId = mechanicId;
        this.price = price;
        this.salaryStatus = salaryStatus;
    }
}
