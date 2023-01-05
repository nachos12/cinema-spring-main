package fr.laraformation.spring.cinema.seances;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SeanceRepository extends JpaRepository<Seance, Integer> {

    List<Seance> findByDate(LocalDate date);
}
