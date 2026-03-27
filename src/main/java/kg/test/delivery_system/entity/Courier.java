package kg.test.delivery_system.entity;

import jakarta.persistence.*;
import kg.test.delivery_system.repository.CourierRepository;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Courier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}