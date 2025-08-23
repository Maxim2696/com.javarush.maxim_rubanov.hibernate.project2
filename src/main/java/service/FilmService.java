package service;

import entity.Film;
import repository.entity_rep.FilmRep;

public class FilmService {
    private final FilmRep repFilm;

    public FilmService(FilmRep repFilm) {
        this.repFilm = repFilm;
    }

    public Film getFilm(Long id) {
        return repFilm.getFilm(id);
    }

    public Film addFilm(Film film) {
        return repFilm.addFilm(film);
    }
}
