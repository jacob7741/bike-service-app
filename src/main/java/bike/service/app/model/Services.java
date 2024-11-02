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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceId;

    @Column(name = "smallservice")
    private final int smallService = 50;
    @Column(name = "fullservice")
    private final int fullService = 200;
    @Column(name = "repair")
    private int repair;
}