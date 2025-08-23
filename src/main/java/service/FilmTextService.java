package service;

import entity.FilmText;
import repository.entity_rep.FilmTextRep;

public class FilmTextService {
    private final FilmTextRep filmTextRep;

    public FilmTextService(FilmTextRep filmTextRep) {
        this.filmTextRep = filmTextRep;
    }

    public FilmText getFilmText(Long id) {
        return filmTextRep.getFilmText(id);
    }

    public void addFilmText(FilmText filmText) {
        filmTextRep.addFilmText(filmText);
    }
}
