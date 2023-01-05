package fr.laraformation.spring.cinema.films;

import fr.laraformation.spring.cinema.acteurs.Acteur;
import fr.laraformation.spring.cinema.acteurs.ActeurService;
import fr.laraformation.spring.cinema.exceptions.NotFoundException;
import fr.laraformation.spring.cinema.realisateurs.Realisateur;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class FilmService {


    private final FilmJpaRepository jpaRepository;

    private final ActeurService acteurService;


    public FilmService(FilmJpaRepository jpaRepository, ActeurService acteurService) {
        this.jpaRepository = jpaRepository;
        this.acteurService = acteurService;
    }


    public Film save(Film film){
        return jpaRepository.save(film);

    }


    public List<Film> findAll(){

        return this.jpaRepository.findAll();
    }


    public Film findById(Integer id) {
        return jpaRepository.findById(id).orElseThrow(()->new NotFoundException("Aucun film ne porte l'id "+id));
    }


    public void deleteById(Integer id) {
        jpaRepository.deleteById(id);
    }


    public List<Film> findByTitreContaining(String titre){
        return jpaRepository.findByTitreContaining(titre);
    }


    public void addActeurById(Integer id, Integer idActeur) {
        Acteur acteur = new Acteur();
        acteur.setId(idActeur);
        addActeur(id, acteur);
    }


    public void addActeur(Integer id, Acteur acteur) {
        Film film = jpaRepository.findById(id).orElseThrow();
        Acteur acteurAAjouter = this.acteurService.findOrInsert(acteur);
        System.out.println("Acteur a ajouter : " + acteurAAjouter.getId());
        System.out.println("Film" + film.getId());
        film.getActeurs().add(acteurAAjouter);
        jpaRepository.save(film);
    }


    public void deleteActeurById(Integer id, Integer idActeur) {
        Film film = jpaRepository.findById(id).orElseThrow();
        film.getActeurs().removeIf(acteur -> acteur.getId().equals(idActeur));
        jpaRepository.save(film);
    }


    public void deleteRealisateurById(Integer id, Integer idRealisateur) {
        Film film = jpaRepository.findById(id).orElseThrow();
        film.getRealisateurs().removeIf(realisateur -> realisateur.getId().equals(idRealisateur));
        jpaRepository.save(film);
    }

    public void addRealisateurById(Integer id, Integer idRealisateur) {
        Realisateur realisateur = new Realisateur();
        realisateur.setId(idRealisateur);
        Film film = jpaRepository.findById(id).orElseThrow();
        film.getRealisateurs().add(realisateur);
        jpaRepository.save(film);
    }
}
