package kg.test.delivery_system.controller;

import kg.test.delivery_system.entity.Courier;
import kg.test.delivery_system.repository.CourierRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/couriers")
public class CourierController {

    private final CourierRepository courierRepository;

    public CourierController(CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
    }

    @GetMapping
    public List<Courier> getAll() {
        return courierRepository.findAll();
    }

    @PostMapping
    public Courier create(@RequestBody Courier courier) {
        return courierRepository.save(courier);
    }

    @PutMapping("/{id}")
    public Courier update(@PathVariable Long id, @RequestBody Courier courier) {
        courier.setId(id);
        return courierRepository.save(courier);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        courierRepository.deleteById(id);
    }
}