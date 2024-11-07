package bike.service.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

    @Column(name = "mechanic")
    private String mechanic;
    @Column(name = "service")
    private String service;
    @Column(name = "bikeModel")
    private String bikeModel;
    @Column(name = "uniqId")
    private int uniqId;

    @Override
    public String toString() {
        return "Order{" +
                "mechanic='" + mechanic + '\'' +
                ", service='" + service + '\'' +
                ", bikeModel='" + bikeModel + '\'' +
                ", uniqId=" + uniqId +
                '}';
    }
}
