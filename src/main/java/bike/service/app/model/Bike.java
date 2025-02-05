package bike.service.app.model;

import javax.validation.constraints.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "orderId")
    private Order order;

}
