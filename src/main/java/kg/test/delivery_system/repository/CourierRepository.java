package kg.test.delivery_system.repository;

import kg.test.delivery_system.entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourierRepository extends JpaRepository<Courier, Long> {
}