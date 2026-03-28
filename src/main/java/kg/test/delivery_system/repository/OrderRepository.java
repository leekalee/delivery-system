package kg.test.delivery_system.repository;

import kg.test.delivery_system.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
//чтобы работать с базой данных без написания SQL
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCourierId(Long courierId);
}
