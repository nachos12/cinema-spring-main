package fr.laraformation.spring.cinema.tickets;

import fr.laraformation.spring.cinema.seances.SeanceRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tickets")
@CrossOrigin
public class TicketController {

    private final TicketService service;
    private final SeanceRepository seanceRepository;

    public TicketController(TicketService service,
                            SeanceRepository seanceRepository) {
        this.service = service;
        this.seanceRepository = seanceRepository;
    }

    @PostMapping
    public Ticket save(@RequestBody Ticket entity) {
        return service.save(entity);
    }

    @GetMapping("{id}")
    public Ticket findById(@PathVariable Integer integer) {
        return service.findById(integer);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer integer) {
        service.deleteById(integer);
    }

    @GetMapping
    public Iterable<Ticket> findAll() {
        return service.findAll();
    }

    @PostMapping("/reservation")
    public Ticket reservation(@RequestBody Ticket ticket) {
        return  service.reservation(ticket);
    }

}
