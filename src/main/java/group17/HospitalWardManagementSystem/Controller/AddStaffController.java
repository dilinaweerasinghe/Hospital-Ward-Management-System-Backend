package group17.HospitalWardManagementSystem.Controller;

import group17.HospitalWardManagementSystem.Model.Dto.AddStaffDto.AddStaffDto;
import group17.HospitalWardManagementSystem.Service.AddStaff.AddStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddStaffController {

    @Autowired
    public AddStaffService addStaffService;

    @RequestMapping("/add-staff")
    public String addStaff(@RequestBody AddStaffDto addStaffDto){

        String fullN=addStaffService.staffSave(addStaffDto);
        return fullN;
    }
}
