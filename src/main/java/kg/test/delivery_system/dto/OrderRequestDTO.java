package kg.test.delivery_system.dto;

import lombok.Data;

@Data
public class OrderRequestDTO {
    private String customerName;
    private String address;
    private String status;
    private Long courierId;
}