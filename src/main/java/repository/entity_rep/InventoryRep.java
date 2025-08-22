package repository.entity_rep;

import entity.Inventory;

import java.util.List;

public interface InventoryRep {
    List<Inventory> findListInventoryByFilmId(Long id);
    Inventory saveInventory(Inventory inventory);
    Inventory get(Long id);
}
