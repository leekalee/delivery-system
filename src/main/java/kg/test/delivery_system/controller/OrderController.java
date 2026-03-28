package kg.test.delivery_system.controller;

import kg.test.delivery_system.dto.OrderRequestDTO;
import kg.test.delivery_system.dto.OrderResponseDTO;
import kg.test.delivery_system.mapper.OrderMapper;
import kg.test.delivery_system.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping
    public Page<OrderResponseDTO> getAll(Pageable pageable) {
        return orderService.getAll(pageable);
    }

    @PostMapping
    public OrderResponseDTO create(@RequestBody OrderRequestDTO request) {
        return orderService.create(request);
    }

    @PutMapping("/{id}")
    public OrderResponseDTO update(@PathVariable Long id,
                                   @RequestBody OrderRequestDTO request) {
        return orderService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderService.deleteCourier(id);
    }
}