package kg.test.delivery_system.controller;

import kg.test.delivery_system.entity.Courier;
import kg.test.delivery_system.repository.CourierRepository;
import kg.test.delivery_system.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/couriers")
@RequiredArgsConstructor
public class CourierController {

    private final CourierRepository courierRepository;
    private final CourierService courierService;

    @GetMapping
    public List<Courier> getAll() {
        return courierRepository.findAll();
    }

    @PostMapping
    public Courier create(@RequestBody Courier courier) {
        return courierRepository.save(courier);
    }

    @PutMapping("/{id}")
    public Courier update(@PathVariable Long id,
                          @RequestBody Courier courier) {
        return courierService.update(id, courier);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        courierService.deleteCourier(id);
    }
}