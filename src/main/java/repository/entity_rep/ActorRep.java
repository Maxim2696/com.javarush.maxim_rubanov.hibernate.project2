package repository.entity_rep;

import entity.Actor;

public interface ActorRep {
    Actor getActor(String firstName, String lastName);
    Actor addActor(Actor actor);
    Actor getById(long id);
}
