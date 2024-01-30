package group17.HospitalWardManagementSystem.Controller;

import group17.HospitalWardManagementSystem.Model.Dto.AddStaffDto.AddStaffDto;
import group17.HospitalWardManagementSystem.Service.AddStaff.AddStaffSave;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddStaffController {
    private AddStaffSave addStaffSave;

    @RequestMapping("/add-staff")
    public String addStaff(@RequestBody AddStaffDto addStaffDto){
        String firstN;
        firstN = addStaffSave.saveAddStaff(addStaffDto);

        return firstN;
    }
}
