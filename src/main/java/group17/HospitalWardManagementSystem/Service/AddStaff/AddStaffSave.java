package group17.HospitalWardManagementSystem.Service.AddStaff;

import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Dto.AddStaffDto.AddStaffDto;
import group17.HospitalWardManagementSystem.Repository.AddStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AddStaffSave {

    @Autowired
    public AddStaffRepository addStaffRepository;

    public String saveAddStaff(AddStaffDto addStaffDto){
        User user=new User();

        user.setNic(addStaffDto.getNic());
        user.setFullName(addStaffDto.getFullName());
        user.setFirstName(addStaffDto.getFirstName());
        user.setLastName(addStaffDto.getLastName());
        user.setUsername(addStaffDto.getUsername());
        user.setPassword(addStaffDto.getPassword());
        user.setDob(addStaffDto.getDob());
        user.setEmail(addStaffDto.getEmail());
        user.setPosition(addStaffDto.getPosition());
        user.setMobileNo(addStaffDto.getMobileNo());

        addStaffRepository.save(user);
        return null;
    }

}
