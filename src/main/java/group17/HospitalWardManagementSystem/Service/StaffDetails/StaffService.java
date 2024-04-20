package group17.HospitalWardManagementSystem.Service.StaffDetails;

import group17.HospitalWardManagementSystem.Repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class StaffService {
    private final StaffRepository staffRepository;

    @Autowired
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public void deleteStaffMember(String nic){
        staffRepository.deleteById(nic);
    }
}
