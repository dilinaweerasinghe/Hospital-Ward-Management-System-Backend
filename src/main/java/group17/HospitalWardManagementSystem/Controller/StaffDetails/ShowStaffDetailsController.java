package group17.HospitalWardManagementSystem.Controller.StaffDetails;

import group17.HospitalWardManagementSystem.Model.Dto.StaffDto.ShowStaffDto;
import group17.HospitalWardManagementSystem.Service.StaffDetails.ShowStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShowStaffDetailsController {
    @Autowired
    public ShowStaffService showStaffService;

    @RequestMapping("/show-staff/{username}")
    public ShowStaffDto retriveStaffData(@PathVariable String username){

        return showStaffService.getStaff(username);
    }

}
