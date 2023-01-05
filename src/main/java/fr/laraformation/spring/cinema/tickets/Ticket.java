package fr.laraformation.spring.cinema.tickets;

import fr.laraformation.spring.cinema.seances.Seance;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tickets")
@Getter
@Setter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String nomClient;

    private int nombrePlace;

    @ManyToOne
    @JoinColumn(name = "seance_id")
    private Seance seance;

}
