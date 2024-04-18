package group17.HospitalWardManagementSystem.Service.WardDetails;

import group17.HospitalWardManagementSystem.Model.Domain.Staff;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import group17.HospitalWardManagementSystem.Model.Dto.WardDto.ShowFullWardDto;
import group17.HospitalWardManagementSystem.Model.Dto.WardDto.ShowWardDto;
import group17.HospitalWardManagementSystem.Repository.MatronRepository;
import group17.HospitalWardManagementSystem.Repository.StaffRepository;
import group17.HospitalWardManagementSystem.Repository.UserRepository;
import group17.HospitalWardManagementSystem.Repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowFullWardService {
    @Autowired
    public WardRepository wardRepository;
    @Autowired
    public MatronRepository matronRepository;

    @Autowired
    public StaffRepository staffRepository;

    @Autowired
    public UserRepository userRepository;
    public ShowFullWardDto showFullWard(String wardName){

        //map the domain to the dto
        ShowFullWardDto showFullWardDto=new ShowFullWardDto();

        Ward ward=wardRepository.findByWardName(wardName);

        showFullWardDto.setWardNo(ward.getWardNo());
        showFullWardDto.setWardName(ward.getWardName());
        showFullWardDto.setSisterNic(findSister(ward));
        showFullWardDto.setNumberOfNurses(ward.getNumberOfNurses());
        showFullWardDto.setMorningShift(ward.getMorningShift());
        showFullWardDto.setEveningShift(ward.getEveningShift());
        showFullWardDto.setNightShift(ward.getNightShift());

        return showFullWardDto;
    }


    public String findSister(Ward wardNo){
        Staff staff = staffRepository.findSisterByWard(wardNo);
        if (staff != null) {
            return staff.getNic();
        } else {
            System.out.println("FindSister error");
            return null;
        }
    }

    public ShowFullWardDto getAllWardDetailsSister(String username){
        Staff staff = findSister(username);
        Ward ward = staff.getWardNo();

        ShowFullWardDto showFullWardDto = new ShowFullWardDto();

        showFullWardDto.setWardName(ward.getWardName());
        showFullWardDto.setWardNo(ward.getWardNo());
        showFullWardDto.setSisterNic(staff.getNic());
        showFullWardDto.setNumberOfNurses(ward.getNumberOfNurses());
        showFullWardDto.setMorningShift(ward.getMorningShift());
        showFullWardDto.setEveningShift(ward.getEveningShift());
        showFullWardDto.setNightShift(ward.getNightShift());

        return showFullWardDto;

    }

    public Staff findSister(String username){
        User user=userRepository.findByUsername(username);
        String nic = user.getNic();
        Staff staff = staffRepository.findByNic(nic);
        return staff;
    }

    public List<String> findAllSisters(){
        return userRepository.findAllSisterNics();
    }
}
