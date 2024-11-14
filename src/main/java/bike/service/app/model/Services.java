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

    /*TODO: do bazy dodać kolumnę "other" gdzie bedzie się wpisywać nazwę innego
     *  oraz pamiętać aby zaktualizować metody*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serviceId")
    private int serviceId;
    @Column(name = "smallservice")
    private int smallService;
    @Column(name = "fullservice")
    private int fullService;
    @Column(name = "repair")
    private int repair;
    @OneToOne()
    @JoinColumn(name = "orderId")
    private Order orderId;

    @Override
    public String toString() {
        return "Services{" +
                "serviceId=" + serviceId +
                ", smallService=" + smallService +
                ", fullService=" + fullService +
                ", repair=" + repair +
                '}';
    }
}