package com.autoserviceapp.model;

import com.autoserviceapp.model.enums.SalaryStatus;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "service_types")
public class ServiceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Order order;

    @OneToOne
    private Mechanic mechanic;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private SalaryStatus salaryStatus;

    public ServiceType(Long id, Order order,
                       Mechanic mechanic, BigDecimal price,
                       SalaryStatus salaryStatus) {
        this.id = id;
        this.order = order;
        this.mechanic = mechanic;
        this.price = price;
        this.salaryStatus = salaryStatus;
    }

    public ServiceType(Long id, BigDecimal price) {
        this.id = id;
        this.price = price;
    }
}
