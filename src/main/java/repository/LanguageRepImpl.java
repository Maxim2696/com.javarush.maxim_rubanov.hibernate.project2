package repository;

import datasource.MySessionFactory;
import entity.Language;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.entity_rep.LanguageRep;

public class LanguageRepImpl implements LanguageRep {
    @Override
    public Language getLanguage(String lang) throws NoResultException {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Query<Language> query = session.createQuery("select l from Language l where name = :lang", Language.class);
            query.setParameter("lang", lang);
            return query.getSingleResult();
        }
    }

    @Override
    public Language addLanguage(Language language) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(language);
            session.getTransaction().commit();
            return getLanguage(language.getName());
        }
    }
}
