package bike.service.app.model;

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

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "orderId")
    private Order order;
    
}