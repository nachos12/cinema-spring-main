package fr.laraformation.spring.cinema.films;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fr.laraformation.spring.cinema.acteurs.Acteur;
import fr.laraformation.spring.cinema.realisateurs.Realisateur;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Ici on utilise la norme JEE JPA implémenté par l'ORM hibernate
 * JEE => ensemble des normes Java pour les applications d'entreprises
 * JPA => norme JEE pour la gestion de la persistance des données
 * Hibernate => Implémentation la plus courante de JPA pour faire un ORM
 */
@Entity
@Table(name="films")
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "titre", nullable = false)
    private String titre;

    @Column(name="date_sortie")
    private LocalDate dateSortie;

    private int duree;

    @Column(length = 500)
    private String resume;

    @ManyToMany()
    @JoinTable(
            name = "acteur_film",
            joinColumns = @JoinColumn(name = "id_film"),
            inverseJoinColumns = @JoinColumn(name="id_acteur")
    )
    private List<Acteur> acteurs = new ArrayList<>();

    @ManyToMany()
    @JoinTable(
            name = "realisateur_film",
            inverseJoinColumns = @JoinColumn(name = "id_realisateur"),
            joinColumns = @JoinColumn(name = "id_film")
    )
    private List<Realisateur> realisateurs = new ArrayList<>();

}
