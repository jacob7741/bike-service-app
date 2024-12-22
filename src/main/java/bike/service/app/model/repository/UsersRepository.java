package bike.service.app.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bike.service.app.model.Users;


@Repository
public interface UsersRepository extends JpaRepository <Users, Integer> {
    //getting user name from repository tzw konwencja nazewnictwa tak dziala w springBoot
    public Users findByUserName(String userName);
}