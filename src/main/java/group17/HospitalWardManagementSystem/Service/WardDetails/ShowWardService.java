package group17.HospitalWardManagementSystem.Service.WardDetails;

import group17.HospitalWardManagementSystem.Model.Domain.Staff;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import group17.HospitalWardManagementSystem.Model.Dto.WardDto.ShowWardDto;
import group17.HospitalWardManagementSystem.Repository.StaffRepository;
import group17.HospitalWardManagementSystem.Repository.UserRepository;
import group17.HospitalWardManagementSystem.Repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowWardService {

    @Autowired
    private WardRepository wardRepository;

    @Autowired
    private UserRepository userRepository;



    public ShowWardDto showWardDetails(String wardNo){
        Ward ward=new Ward();
        User user=new User();
        Staff staff=new Staff();

        ShowWardDto wardDto=new ShowWardDto();

        Ward targetWard=findWard(wardNo);

        wardDto.setWardNo(targetWard.getWardNo());
        wardDto.setWardName(targetWard.getWardName());
        wardDto.setNumberOfNurses(targetWard.getNumberOfNurses());
        wardDto.setNic(findUser(targetWard));

        return wardDto;

    }

    public Ward findWard(String wardNo){
        return wardRepository.findByWardNo(wardNo);
    }

    public String findUser(Ward wardNo){
        return userRepository.findBy(wardNo);
    }

}
