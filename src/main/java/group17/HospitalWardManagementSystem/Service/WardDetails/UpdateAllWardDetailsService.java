package group17.HospitalWardManagementSystem.Service.WardDetails;

import group17.HospitalWardManagementSystem.Model.Domain.Staff;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import group17.HospitalWardManagementSystem.Model.Dto.StaffDto.WardNumbersDto;
import group17.HospitalWardManagementSystem.Model.Dto.WardDto.UpdateAllWardDetailsDto;
import group17.HospitalWardManagementSystem.Repository.StaffRepository;
import group17.HospitalWardManagementSystem.Repository.UserRepository;
import group17.HospitalWardManagementSystem.Repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateAllWardDetailsService {

    @Autowired
    private WardRepository wardRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private UserRepository userRepository;


    public String UpdateAllWardDetails(UpdateAllWardDetailsDto updateWardBySisterDto){

        Ward targetWard=findWard(updateWardBySisterDto.getWardNo());

        setCurrentSisterWardNull(targetWard);

        targetWard.setWardName(updateWardBySisterDto.getWardName());
        targetWard.setWardNo(updateWardBySisterDto.getWardNo());
        targetWard.setNumberOfNurses(updateWardBySisterDto.getNumberOfNurses());
        targetWard.setNightShift(updateWardBySisterDto.getNightShift());
        targetWard.setEveningShift(updateWardBySisterDto.getEveningShift());
        targetWard.setMorningShift(updateWardBySisterDto.getMorningShift());

        setNewSisterWard(updateWardBySisterDto.getSisterNic(), updateWardBySisterDto.getWardNo());

        //setNewSisterWard(updateWardBySisterDto.getSisterNic(),targetWard);

        wardRepository.save(targetWard);

        return "Successfully updated the ward details";
    }

    public Ward findWard(String wardNo){
        return wardRepository.findByWardNo(wardNo);
    }

    public void setCurrentSisterWardNull(Ward wardNo){
        Staff staff = staffRepository.findSisterByWard(wardNo);

        if (staff != null) {
            staff.setWardNo(null);
        } else {
            System.out.println("FindSister error");
        }
    }

    public void setNewSisterWard(String newSisterNic,String wardNo){
        Staff staff = staffRepository.findByNic(newSisterNic);
        Ward ward = wardRepository.findByWardNo(wardNo);
        staff.setWardNo(ward);

    }

//    public String setCurrentSisterWardNull(String nic){
//        Optional<User> user = userRepository.findByNic(nic);
//        if(user.isPresent()){
//            User user1 = user.get();
//            return user1.getFullName();
//        }
//        else{
//            System.out.println("user is null");
//            return null;
//
//        }
//    }

}
