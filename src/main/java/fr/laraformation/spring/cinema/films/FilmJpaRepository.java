package fr.laraformation.spring.cinema.films;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface FilmJpaRepository extends JpaRepository<Film, Integer> {
    List<Film> findByTitre(String titre);
    List<Film> findByTitreContaining(String titre);

    List<Film> findByTitreContainingIgnoreCaseAndDureeBetween(String titre, int dureeStart, int dureeEnd);
    List<Film> findFilmsByDateSortieAfter(LocalDate Date);

 }
