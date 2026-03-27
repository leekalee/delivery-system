package kg.test.delivery_system.controller;

import kg.test.delivery_system.dto.OrderRequestDTO;
import kg.test.delivery_system.dto.OrderResponseDTO;
import kg.test.delivery_system.entity.Order;
import kg.test.delivery_system.mapper.OrderMapper;
import kg.test.delivery_system.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private OrderMapper orderMapper;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // GET с пагинацией
    @GetMapping
    public Page<OrderResponseDTO> getAll(Pageable pageable) {
        return orderService.getAll(pageable);
    }

    // POST
    @PostMapping
    public OrderResponseDTO create(@RequestBody OrderRequestDTO request) {
        return orderService.create(request);
    }

    @PutMapping("/{id}")
    public OrderResponseDTO update(@PathVariable Long id,
                                   @RequestBody OrderRequestDTO request) {

        Order order = orderMapper.toEntity(request);
        Order updated = orderService.update(id, order);

        return orderMapper.toResponse(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }
}