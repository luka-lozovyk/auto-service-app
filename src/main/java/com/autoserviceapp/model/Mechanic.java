package com.autoserviceapp.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "mechanics")
public class Mechanic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;

    @OneToMany
    @JoinTable(name = "mechanics_completedOrders",
            joinColumns = @JoinColumn(name = "mechanic_id"),
            inverseJoinColumns = @JoinColumn(name = "completedOrder_id"))
    private List<Order> completedOrders;

    public Mechanic(Long id, String fullName, List<Order> completedOrders) {
        this.id = id;
        this.fullName = fullName;
        this.completedOrders = completedOrders;
    }

    public Mechanic(Long id) {
        this.id = id;
    }
}
