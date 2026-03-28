package kg.test.delivery_system.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    private String address;

    private String status;

    @ManyToOne
    @JoinColumn(name = "courier_id", nullable = true)
    private Courier courier;
}