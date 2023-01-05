package fr.laraformation.spring.cinema.seances;

import fr.laraformation.spring.cinema.films.Film;
import fr.laraformation.spring.cinema.salles.Salle;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "sceances")
@Getter
@Setter
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    private LocalDate date;

    private int nombrePlace;

    private float prix;


    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;


    @ManyToOne
    @JoinColumn(name = "salle_id")
    private Salle salle;
}
