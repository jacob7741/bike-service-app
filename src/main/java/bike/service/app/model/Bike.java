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
@Table(name = "Bike")
public class Bike {
    //!!!zmienić nazwę w bazie danychj na brand!!!
    @Column(name = "bike")
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
