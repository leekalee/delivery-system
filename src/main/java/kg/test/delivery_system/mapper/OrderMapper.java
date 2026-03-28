package kg.test.delivery_system.mapper;

import kg.test.delivery_system.dto.OrderRequestDTO;
import kg.test.delivery_system.dto.OrderResponseDTO;
import kg.test.delivery_system.entity.Courier;
import kg.test.delivery_system.entity.Order;
import org.springframework.stereotype.Component;
//переводчик между слоями
@Component  //Создай этот класс и используй его как бин
public class OrderMapper {

    public OrderResponseDTO toResponse(Order order) { //превращает Entity → DTO
        OrderResponseDTO response = new OrderResponseDTO();
        response.setId(order.getId());
        response.setCustomerName(order.getCustomerName());
        response.setAddress(order.getAddress());
        response.setStatus(order.getStatus());
        return response;
    }

    public Order toEntity(OrderRequestDTO request) {
        Order order = new Order();
        order.setCustomerName(request.getCustomerName());
        order.setAddress(request.getAddress());
        order.setStatus(request.getStatus());
        return order;
    }
}