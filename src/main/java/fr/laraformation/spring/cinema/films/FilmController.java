package fr.laraformation.spring.cinema.films;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.laraformation.spring.cinema.acteurs.Acteur;
import fr.laraformation.spring.cinema.exceptions.BadRequestException;
import fr.laraformation.spring.cinema.films.dto.FilmCompletDto;
import fr.laraformation.spring.cinema.films.dto.FilmReduitDto;
import fr.laraformation.spring.cinema.acteurs.ActeurController;
import fr.laraformation.spring.cinema.realisateurs.Realisateur;
import org.hibernate.PropertyValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("films")
@CrossOrigin
public class FilmController {

    private final FilmService service;
    private final ObjectMapper mapper;

    private final Logger logger = LoggerFactory.getLogger(FilmController.class);


    public FilmController(FilmService service, ObjectMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FilmCompletDto save(@RequestBody Film film) {
        try {
            Film entity = service.save(film);
            return mapper.convertValue(entity, FilmCompletDto.class);
        } catch (PropertyValueException | DataIntegrityViolationException e) {
            logger.warn("Le film doit posséder un titre et une date de sortie. "+film);
            throw new BadRequestException("Le film doit posséder un titre et une date de sortie.");
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName());
            throw new RuntimeException("Le film n'a pas pu être sauvegardé.", e);
        }

    }


    @GetMapping
    public List<FilmReduitDto> findAll() {
        return service.findAll()
                .stream()
                    .map(film -> mapper.convertValue(film, FilmReduitDto.class))
                .toList();
    }


    @GetMapping("{id}")
    public FilmCompletDto findById(@PathVariable Integer id) {
        Film entity = service.findById(id);
        return mapper.convertValue(entity, FilmCompletDto.class);
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id) {
        service.deleteById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FilmCompletDto update(@RequestBody Film film){
        return this.save(film);
    }

    @GetMapping("titre/{titre}")
    public List<FilmReduitDto> findByTitre(@PathVariable String titre){
        List<Film> entities = this.service.findByTitreContaining(titre);
        return entities.stream().map(film -> mapper.convertValue(film, FilmReduitDto.class)).toList();
    }

    @PostMapping("{id}/acteurs/{idActeur}")
    public void addActeurById(@PathVariable Integer id, @PathVariable Integer idActeur){
        this.service.addActeurById(id, idActeur);
    }

    @PostMapping("{id}/acteurs")

    public void addActeur(@PathVariable Integer id, @RequestBody Acteur acteur){
        this.service.addActeur(id, acteur);
    }

    @DeleteMapping("{id}/acteurs/{idActeur}")
    public void deleteActeur(@PathVariable Integer id, @PathVariable Integer idActeur){
        this.service.deleteActeurById(id, idActeur);
    }


    @PostMapping("{id}/realisateurs/{idRealisateur}")
    public void addRealisateur(@PathVariable Integer id, @PathVariable Integer idRealisateur){
        this.service.addRealisateurById(id, idRealisateur);
    }


    @DeleteMapping("{id}/realisateurs/{idRealisateur}")
    public void deleteRealisateur(@PathVariable Integer id, @PathVariable Integer idRealisateur){
        this.service.deleteRealisateurById(id, idRealisateur);
    }

}
