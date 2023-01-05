package fr.laraformation.spring.cinema.acteurs.dto;

import fr.laraformation.spring.cinema.films.dto.FilmReduitDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ActeurCompletDto {
    private Integer id;
    private String nom;
    private String prenom;
    private List<FilmReduitDto> films = new ArrayList<>();
}
