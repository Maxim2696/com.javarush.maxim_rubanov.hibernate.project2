package repository;

import datasource.MySessionFactory;
import entity.Film;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.entity_rep.FilmRep;

public class FilmRepImpl implements FilmRep {
    @Override
    public Film getFilm(Long id) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Query<Film> query = session.createQuery("select f from Film f where filmId=:id", Film.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        }
    }

    @Override
    public Film addFilm(Film film) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            long id = (Long) session.save(film);
            session.getTransaction().commit();
            return getFilm(id);
        }
    }
}
