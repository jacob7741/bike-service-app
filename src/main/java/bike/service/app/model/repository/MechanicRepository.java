package bike.service.app.model.repository;

import bike.service.app.model.Mechanic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public interface MechanicRepository extends JpaRepository <Mechanic, Integer> {
    //getting user name from repository tzw konwencja nazewnuictwa tak dziala w springBoot
    public Mechanic findByUserName(String user_name);
}
