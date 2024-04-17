package group17.HospitalWardManagementSystem.Service.StaffDetails;

import group17.HospitalWardManagementSystem.Model.Domain.Staff;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import group17.HospitalWardManagementSystem.Model.Dto.StaffDto.SisterDtoForMatron;
import group17.HospitalWardManagementSystem.Repository.StaffRepository;
import group17.HospitalWardManagementSystem.Repository.UserRepository;
import group17.HospitalWardManagementSystem.Repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SisterService {
    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WardRepository wardRepository;


    public SisterDtoForMatron findSisterForMatron(String wardNo){

        SisterDtoForMatron showSisterDto = new SisterDtoForMatron();

        Ward ward = wardRepository.findByWardNo(wardNo);
        String nic = staffRepository.findByWard(ward);

        Staff staff = staffRepository.findByNic(nic);

        Optional<User> user = userRepository.findByNic(nic);

        if(user.isEmpty()){
            System.out.println("User empty");
            return null;
        }
        else{
            User sister = user.get();

            showSisterDto.setNic(sister.getNic());
            showSisterDto.setPosition(sister.getPosition());
            showSisterDto.setLeaveNo(staff.getLeaveNum());
            showSisterDto.setBirthdate(sister.getDob());
            showSisterDto.setServiceStartDate(staff.getServiceStartedDate());
            showSisterDto.setFullName(sister.getFullName());
            showSisterDto.setFirstName(sister.getFirstName());
            showSisterDto.setLastName(sister.getLastName());
            showSisterDto.setEmail(sister.getEmail());
            showSisterDto.setMobileNo(sister.getMobileNo());

            return showSisterDto;
        }
    }

    public String saveSisterEditedDetailsForMatron(SisterDtoForMatron sisterDto){

        Optional<User> sister = userRepository.findByNic(sisterDto.getNic());

        if(sister.isPresent()){

            User editedSister =sister.get();
            Staff staff = staffRepository.findByNic(sisterDto.getNic());

            editedSister.setFirstName(sisterDto.getFirstName());
            editedSister.setLastName(sisterDto.getLastName());
            editedSister.setFullName(sisterDto.getFullName());
            editedSister.setEmail(sisterDto.getEmail());
            editedSister.setMobileNo(sisterDto.getMobileNo());
            editedSister.setNic(sisterDto.getNic());
            editedSister.setDob(sisterDto.getBirthdate());
            editedSister.setPosition(sisterDto.getPosition());

            staff.setLeaveNum(sisterDto.getLeaveNo());
            staff.setServiceStartedDate(sisterDto.getServiceStartDate());

            userRepository.save(editedSister);
            staffRepository.save(staff);

            return "Successfully updated";
        }
        else{
            return null;
        }


    }
}
