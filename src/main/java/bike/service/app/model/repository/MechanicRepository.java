package bike.service.app.model.repository;

import bike.service.app.model.Mechanic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MechanicRepository extends JpaRepository <Mechanic, Integer> {

}
