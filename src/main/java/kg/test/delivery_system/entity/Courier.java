package kg.test.delivery_system.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity //Это таблица в базе данных
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder //удобно создавать объект
public class Courier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
}