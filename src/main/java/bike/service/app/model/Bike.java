package bike.service.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Bike")
public class  Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bikeId")
    private int bikeId;
    @NotNull
    @Column(name = "brand")
    private String brand;
    @NotNull
    @Column(name = "modelType")
    private String modelType;
    @NotNull
    @Column(name = "serialNumber")
    private int serialNumber;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

    @Override
    public String toString() {
        return "Bike{" +
                "bikeId=" + bikeId +
                ", brand='" + brand + '\'' +
                ", modelType='" + modelType + '\'' +
                ", serialNumber=" + serialNumber +
                '}';
    }
}
