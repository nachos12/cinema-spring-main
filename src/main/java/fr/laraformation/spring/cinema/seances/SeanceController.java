package fr.laraformation.spring.cinema.seances;

import fr.laraformation.spring.cinema.tickets.Ticket;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("seances")
public class SeanceController {

    private final SeanceService service;

    public SeanceController(SeanceService service) {
        this.service = service;
    }

    @GetMapping
    public List<Seance> findAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public Seance findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public Seance save(@RequestBody Seance seance) {
        return service.save(seance);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id) {
        service.deleteById(id);
    }


}
