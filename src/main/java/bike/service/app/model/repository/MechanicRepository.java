package bike.service.app.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bike.service.app.model.Mechanic;

@Repository
public interface MechanicRepository extends JpaRepository <Mechanic, Integer> {
    //getting user name from repository tzw konwencja nazewnictwa tak dziala w springBoot
    public Mechanic findByUserName(String user_name);
}