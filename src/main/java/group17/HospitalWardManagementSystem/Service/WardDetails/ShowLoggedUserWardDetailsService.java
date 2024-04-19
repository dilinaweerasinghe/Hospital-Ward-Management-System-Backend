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
public class ShowLoggedUserWardDetailsService {
    @Autowired
    private WardRepository wardRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private UserRepository userRepository;

    public ShowWardDto getLoggedUserWardDetails(String username){
        User user = userRepository.findByUsername(username);
        String nic = user.getNic();
        Staff staff = staffRepository.findByNic(nic);
        Ward ward = staff.getWardNo();

        ShowWardDto showWardDto = new ShowWardDto();

        showWardDto.setWardNo(ward.getWardNo());
        showWardDto.setWardName(ward.getWardName());
        showWardDto.setNumberOfNurses(ward.getNumberOfNurses());
        showWardDto.setSisterName(user.getFullName());

        return showWardDto;
    }

}
