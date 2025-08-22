package service;

import entity.Film;
import entity.Inventory;
import entity.Store;
import repository.entity_rep.InventoryRep;

import java.util.List;

public class InventoryService {
    private final InventoryRep inventoryRep;

    public InventoryService(InventoryRep inventoryRep) {
        this.inventoryRep = inventoryRep;
    }

    public List<Inventory> findInventoryByFilmId(Long id) {
        return inventoryRep.findListInventoryByFilmId(id);
    }

    public Inventory createInventory(Film film, Store store) {
        Inventory inventory = new Inventory();
        inventory.setFilm(film);
        inventory.setStore(store);
        return inventoryRep.saveInventory(inventory);
    }
}
