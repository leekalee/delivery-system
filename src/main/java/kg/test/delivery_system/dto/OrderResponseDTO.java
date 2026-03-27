package kg.test.delivery_system.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderResponseDTO {

    private Long id;
    private String customerName;
    private String address;
    private String status;

    public OrderResponseDTO() {
    }

}