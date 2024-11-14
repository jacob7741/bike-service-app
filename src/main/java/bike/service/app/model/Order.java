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
    @Column(name = "id")
    private int id;
    @OneToOne(mappedBy = "orderId", cascade = CascadeType.MERGE)
    private Services services;
    @OneToOne(mappedBy = "orderId", cascade = CascadeType.MERGE)
    private Bike bike;
    @Column(name = "mechanic")
    private String mechanic;
    @Column(name = "service")
    private String service;
    @Column(name = "bikeModel")
    private String bikeModel;

    @Override
    public String toString() {
        return "Order{" +
                "mechanic='" + mechanic + '\'' +
                ", service='" + service + '\'' +
                ", bikeModel='" + bikeModel + '\'' +
                ", id=" + id +
                '}';
    }
}