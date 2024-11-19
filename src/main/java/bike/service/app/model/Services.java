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
@Table(name = "Services")
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "serviceId")
    private int serviceId;
    @Column(name = "smallservice")
    private int smallService;
    @Column(name = "fullservice")
    private int fullService;
    @Column(name = "repair")
    private int repair;
    @Column(name = "repairType")
    private String repairType;

    @OneToOne
    @JoinColumn(name = "orderId")
    private Order orderId;

    @Override
    public String toString() {
        return "Services{" +
                "serviceId=" + serviceId +
                ", smallService=" + smallService +
                ", fullService=" + fullService +
                ", repair=" + repair +
                ", repairType='" + repairType + '\'' +
                ", orderId=" + orderId +
                '}';
    }
}