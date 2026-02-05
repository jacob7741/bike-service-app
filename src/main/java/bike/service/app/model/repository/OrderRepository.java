package bike.service.app.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bike.service.app.model.Order;
import bike.service.app.model.Order.Status;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findOrderByUserId(Long userId);
    List<Order> findByStatus(Status status);
    List<Order> findByUserIdAndStatus(Long id, Status status);
}
