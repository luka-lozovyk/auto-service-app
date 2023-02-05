package com.autoserviceapp.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarResponseDto {
    private Long id;
    private String manufacturer;
    private String model;
    private int year;
    private String registrationNumber;
    private Long ownerId;

    public CarResponseDto(Long id,
                          String manufacturer,
                          String model,
                          int year,
                          String registrationNumber,
                          Long ownerId) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.registrationNumber = registrationNumber;
        this.ownerId = ownerId;
    }
}
