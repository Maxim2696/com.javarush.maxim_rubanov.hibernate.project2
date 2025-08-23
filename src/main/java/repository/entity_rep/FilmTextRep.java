package repository.entity_rep;

import entity.FilmText;

public interface FilmTextRep {
    void addFilmText(FilmText filmText);
    FilmText getFilmText(Long id);
}
