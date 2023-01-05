package fr.laraformation.spring.cinema.films;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class FullFilmRepository {

    private final EntityManager entityManager;

    public FullFilmRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public Film save(Film film){
        entityManager.persist(film);
        entityManager.flush(); // synchro l'instance film avec la base de données. Ajoute donc l'id.
        return film;
    }


    public Film findById(Integer id){
        // SELECT * FROM films WHERE films.id == {id}
        return entityManager.find(Film.class, id);
    }


    public List<Film> findAll(){
        TypedQuery<Film> query = entityManager.createQuery("SELECT f FROM films f", Film.class);
        return query.getResultList();
    }


    public Film update(Film film){
        entityManager.merge(film);
        return film;
    }


    public void deleteById(Integer id){
        Film film = this.findById(id);
        entityManager.remove(film);
    }


}
