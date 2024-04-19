package group17.HospitalWardManagementSystem.Controller.StaffDetails;

import group17.HospitalWardManagementSystem.Model.Dto.StaffDto.AddStaffDto;
import group17.HospitalWardManagementSystem.Model.Dto.StaffDto.WardNumbersDto;
import group17.HospitalWardManagementSystem.Service.StaffDetails.AddStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddStaffController {

    @Autowired
    public AddStaffService addStaffService;

    @RequestMapping("/add-staff")
    public String addStaff(@RequestBody  AddStaffDto addStaffDto){

        String fullN=addStaffService.staffSave(addStaffDto);
        return fullN;
    }

    @GetMapping("/get-ward-numbers")
    public WardNumbersDto getWardNumbers(){
        return new WardNumbersDto(addStaffService.findAllWardNumbers());
    }

    @GetMapping("/get-existing-user/{nic}")
    public AddStaffDto getExistingUser(@PathVariable String nic){
        return addStaffService.getExistingUser(nic);
    }

    @GetMapping("/get-all-users-nics")
    public List<String> getAllUserNics(){
        return addStaffService.retrieveAllUserNics();
    }
}
