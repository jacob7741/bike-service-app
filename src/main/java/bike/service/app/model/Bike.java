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
public class Bike {
    //!!!zmienić nazwę w bazie danychj na brand!!!
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "brand")
    private String brand;
    @Column(name = "modelType")
    private String modelType;
    @Column(name = "serialNumber")
    private int serialNumber;

    @Override
    public String toString() {
        return "Bike{" +
                "brand='" + brand + '\'' +
                ", modelType='" + modelType + '\'' +
                ", serialNumber=" + serialNumber +
                '}';
    }
}
