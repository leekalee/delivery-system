package kg.test.delivery_system.service;

import kg.test.delivery_system.entity.Courier;
import kg.test.delivery_system.entity.Order;
import kg.test.delivery_system.repository.CourierRepository;
import kg.test.delivery_system.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourierService {

    private final CourierRepository courierRepository;
    private final OrderRepository orderRepository;

    public CourierService(CourierRepository courierRepository,
                          OrderRepository orderRepository) {
        this.courierRepository = courierRepository;
        this.orderRepository = orderRepository;
    }

    public void deleteCourier(Long id) {

        List<Order> orders = orderRepository.findByCourierId(id);

        for (Order order : orders) {
            order.setCourier(null); // 🔥 отвязываем
        }

        orderRepository.saveAll(orders);

        courierRepository.deleteById(id);
    }

    public Courier update(Long id, Courier updatedCourier) {

        Courier courier = courierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Courier not found"));

        courier.setName(updatedCourier.getName());

        return courierRepository.save(courier);
    }
}