package service;

import entity.Store;
import repository.entity_rep.StoreRep;

public class StoreService {
    private final StoreRep storeRep;

    public StoreService(StoreRep storeRep) {
        this.storeRep = storeRep;
    }

    public Store getStore(Long id) {
        return storeRep.getById(id);
    }
}
