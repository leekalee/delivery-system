package kg.test.delivery_system.service;

import kg.test.delivery_system.dto.OrderRequestDTO;
import kg.test.delivery_system.dto.OrderResponseDTO;
import kg.test.delivery_system.entity.Courier;
import kg.test.delivery_system.entity.Order;
import kg.test.delivery_system.mapper.OrderMapper;
import kg.test.delivery_system.repository.CourierRepository;
import kg.test.delivery_system.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CourierRepository courierRepository;

    public OrderResponseDTO getById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return orderMapper.toResponse(order);
    }

    public OrderResponseDTO update(Long id, OrderRequestDTO request) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setCustomerName(request.getCustomerName());
        order.setAddress(request.getAddress());
        order.setStatus(request.getStatus());

        return orderMapper.toResponse(orderRepository.save(order));
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    public OrderResponseDTO create(OrderRequestDTO request) {
        Courier courier = courierRepository.findById(request.getCourierId())
                .orElseThrow(() -> new RuntimeException("Courier not found"));

        Order order = orderMapper.toEntity(request);
        return orderMapper.toResponse(orderRepository.save(order));
    }

    public Page<OrderResponseDTO> getAll(Pageable pageable) {
        return orderRepository.findAll(pageable)
                .map(orderMapper::toResponse);
    }

    public Order update(Long id, Order updatedOrder) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setCustomerName(updatedOrder.getCustomerName());
        order.setAddress(updatedOrder.getAddress());
        order.setStatus(updatedOrder.getStatus());

        return orderRepository.save(order);
    }
}