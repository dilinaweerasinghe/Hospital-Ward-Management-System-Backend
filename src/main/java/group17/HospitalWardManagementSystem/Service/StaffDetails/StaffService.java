package group17.HospitalWardManagementSystem.Service.StaffDetails;

import group17.HospitalWardManagementSystem.Model.Domain.Staff;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import group17.HospitalWardManagementSystem.Repository.StaffRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class StaffService {
    private final StaffRepository staffRepository;

    @Autowired
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Transactional
    public void deleteStaffMember(String nic){
        Staff staff = staffRepository.findById(nic).orElseThrow(()-> new EntityNotFoundException("Cannot find the user with nic: " + nic + " to delete. Please contact admin!"));
        Ward ward = staff.getWardNo();
        ward.setNumberOfNurses(ward.getNumberOfNurses() - 1);
        staffRepository.deleteById(nic);

    }
}
