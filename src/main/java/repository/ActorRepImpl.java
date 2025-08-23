package repository;

import datasource.MySessionFactory;
import entity.Actor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.entity_rep.ActorRep;

public class ActorRepImpl implements ActorRep {
    @Override
    public Actor getActor(String firstName, String lastName) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Query<Actor> query = session.createQuery("select a from Actor a where firstName =:first and lastName = :last", Actor.class);
            query.setParameter("first", firstName);
            query.setParameter("last", lastName);
            return query.getSingleResult();
        }
    }

    @Override
    public Actor addActor(Actor actor) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            long id = (long) session.save(actor);
            session.getTransaction().commit();
            return getActor(actor.getFirstName(), actor.getLastName());
        }
    }

    @Override
    public Actor getById(long id) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Query<Actor> query = session.createQuery("select a from Actor a where actorId=:id", Actor.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        }
    }
}
