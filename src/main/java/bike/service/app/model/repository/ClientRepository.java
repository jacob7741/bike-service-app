package bike.service.app.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bike.service.app.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

}
