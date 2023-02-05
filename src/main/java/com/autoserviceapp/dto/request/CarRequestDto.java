package com.autoserviceapp.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarRequestDto {
    private String manufacturer;
    private String model;
    private int year;
    private String registrationNumber;
    private Long ownerId;

    public CarRequestDto(String manufacturer,
                         String model,
                         int year,
                         String registrationNumber,
                         Long ownerId) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.registrationNumber = registrationNumber;
        this.ownerId = ownerId;
    }
}
