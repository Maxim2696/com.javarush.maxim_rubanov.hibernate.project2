package service;

import entity.Staff;
import repository.entity_rep.StaffRep;

public class StaffService {
    private final StaffRep staffRep;

    public StaffService(StaffRep staffRep) {
        this.staffRep = staffRep;
    }

    public Staff getStaff(Long id) {
        return staffRep.getStaff(id);
    }
}
