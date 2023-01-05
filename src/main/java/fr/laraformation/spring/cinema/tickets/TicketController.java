package fr.laraformation.spring.cinema.tickets;

import fr.laraformation.spring.cinema.seances.SeanceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Ticket reservation(@RequestBody Ticket ticket) {
        return  service.reservation(ticket);
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

    @GetMapping("seance/{id}")
    public List<Ticket> findTicketBySeance(@PathVariable Integer id){
        return service.findTicketBySeance(id);
    }

}
