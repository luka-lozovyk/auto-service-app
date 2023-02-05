package com.autoserviceapp.model;

import com.autoserviceapp.model.enums.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Car car;

    private String problemDescription;
    private LocalDateTime startDate;

    @OneToMany
    @JoinTable(name = "orders_service_types",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "service_type_id"))
    private List<ServiceType> serviceTypes;

    @ManyToMany
    @JoinTable(name = "orders_spare_parts",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "spare_part_id"))
    private List<SparePart> spareParts;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private BigDecimal totalPrice;
    private LocalDateTime finishDate;

    public Order(Long id, Car car,
                 String problemDescription,
                 LocalDateTime startDate,
                 List<ServiceType> serviceTypes,
                 List<SparePart> spareParts,
                 OrderStatus orderStatus,
                 BigDecimal totalPrice,
                 LocalDateTime finishDate) {
        this.id = id;
        this.car = car;
        this.problemDescription = problemDescription;
        this.startDate = startDate;
        this.serviceTypes = serviceTypes;
        this.spareParts = spareParts;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
        this.finishDate = finishDate;
    }

    public Order(Long id) {
        this.id = id;
    }
}
