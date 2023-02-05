package com.autoserviceapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String manufacturer;
    private String model;
    private int year;
    private String registrationNumber;

    @OneToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public Car(Long id,
               String manufacturer,
               String model,
               int year,
               String registrationNumber,
               Owner owner) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.registrationNumber = registrationNumber;
        this.owner = owner;
    }

    public Car(String manufacturer,
               String model,
               int year,
               String registrationNumber,
               Owner owner) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.registrationNumber = registrationNumber;
        this.owner = owner;
    }

    public Car(Long id) {
        this.id = id;
    }

    public Car(Long id, Owner owner) {
        this.id = id;
        this.owner = owner;
    }
}
