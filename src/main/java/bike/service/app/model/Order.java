package bike.service.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "RequestOrder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private int orderId;
    @Column(name = "mechanic")
    private String mechanic;
    @Column(name = "service")
    private String service;
    @Column(name = "bikeModel")
    private String bikeModel;
    @Column(name = "client")
    private String client;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Services> services;
}