package group17.HospitalWardManagementSystem.Service.WardDetails;

import group17.HospitalWardManagementSystem.Model.Domain.Staff;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import group17.HospitalWardManagementSystem.Repository.MatronRepository;
import group17.HospitalWardManagementSystem.Repository.StaffRepository;
import group17.HospitalWardManagementSystem.Repository.WardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WardService {

    private final WardRepository wardRepository;
    private final StaffRepository staffRepository;
    private final MatronRepository matronRepository;

    public WardService(WardRepository wardRepository, StaffRepository staffRepository, MatronRepository matronRepository) {
        this.wardRepository = wardRepository;
        this.staffRepository = staffRepository;
        this.matronRepository = matronRepository;
    }

    public List<String> wardListByMatron(String nic){
        List<String> wards = wardRepository.findWardNoByMatronId(nic);
        if(wards.isEmpty()){
            throw new IllegalArgumentException("You are not a matron in any ward!");
        }
        return wards;
    }
}
