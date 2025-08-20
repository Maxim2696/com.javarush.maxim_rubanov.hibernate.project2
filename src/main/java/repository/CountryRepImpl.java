package repository;

import datasource.MySessionFactory;
import entity.Country;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.entity_rep.CountryRep;

public class CountryRepImpl implements CountryRep {
    @Override
    public Country get(String nameCountry) throws NoResultException {
        try (Session session = MySessionFactory.getSessionFactory().openSession()){
            Query<Country> query = session.createQuery("select c from Country c where countryName = :nameCountry", Country.class);
            query.setParameter("nameCountry", nameCountry);
            return query.getSingleResult();
        }
    }

    @Override
    public Country add(String countryName) {
        Country result;
        try (Session session = MySessionFactory.getSessionFactory().openSession()){
            session.beginTransaction();
            result = new Country();
            result.setCountryName(countryName);
            session.save(result);
            session.getTransaction().commit();
        }
        return get(countryName);
    }
}
