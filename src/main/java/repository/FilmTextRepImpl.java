package repository;

import datasource.MySessionFactory;
import entity.FilmText;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.entity_rep.FilmTextRep;

public class FilmTextRepImpl implements FilmTextRep {
    @Override
    public void addFilmText(FilmText filmText) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(filmText);
            session.getTransaction().commit();
        }
    }

    @Override
    public FilmText getFilmText(Long id) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Query<FilmText>  query = session.createQuery("select a from Actor a where filmId=:id", FilmText.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        }
    }
}
