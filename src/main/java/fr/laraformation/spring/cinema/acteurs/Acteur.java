package fr.laraformation.spring.cinema.acteurs;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import fr.laraformation.spring.cinema.films.Film;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "acteurs")
@Getter
@Setter
@JsonIdentityInfo(generator = com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Acteur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String nom;

    private String prenom;


    @ManyToMany(mappedBy = "acteurs")

    //@JsonBackReference // pour eviter la boucle infinie
    private List<Film> films = new ArrayList<>();
}
