package bike.service.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @OneToOne(mappedBy = "orderId", cascade = CascadeType.ALL)
    private Services services;
    @Column(name = "mechanic")
    private String mechanic;
    @Column(name = "service")
    private String service;
    @Column(name = "bikeModel")
    private String bikeModel;
    @Column(name = "client")
    private String client;

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", services=" + services +
                ", mechanic='" + mechanic + '\'' +
                ", service='" + service + '\'' +
                ", bikeModel='" + bikeModel + '\'' +
                ", client='" + client + '\'' +
                '}';
    }
}