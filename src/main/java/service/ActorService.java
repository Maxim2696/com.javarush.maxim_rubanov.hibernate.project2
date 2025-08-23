package service;

import entity.Actor;
import repository.entity_rep.ActorRep;

public class ActorService {
    private final ActorRep actorRep;

    public ActorService(ActorRep actorRep) {
        this.actorRep = actorRep;
    }

    public Actor getByFirstNameAndLastName(String firstName, String lastName) {
        return actorRep.getActor(firstName, lastName);
    }

    public Actor getById(Long id) {
        return actorRep.getById(id);
    }

    public Actor addActor(Actor actor) {
        return actorRep.addActor(actor);
    }
}
