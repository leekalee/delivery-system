package kg.test.delivery_system.repository;

import kg.test.delivery_system.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//чтобы работать с базой данных без написания SQL
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCourierId(Long courierId);
}
