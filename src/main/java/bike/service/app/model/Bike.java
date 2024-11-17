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
@Table(name = "Bike")
public class  Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bikeId")
    private int bikeId;
    @Column(name = "brand")
    private String brand;
    @Column(name = "modelType")
    private String modelType;
    @Column(name = "serialNumber")
    private int serialNumber;

//    @OneToOne
//    @JoinColumn(name = "orderId")
//    private Order orderId;

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
