package group17.HospitalWardManagementSystem.Controller.StaffDetails;

import group17.HospitalWardManagementSystem.Model.Dto.StaffDto.UpdateDto;
import group17.HospitalWardManagementSystem.Service.StaffDetails.UpdateStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UpdateStaffDetailsController {

    @Autowired
    public UpdateStaffService updateStaffService;

    @PutMapping("/update")
    public String updateStaffDetails(@RequestBody UpdateDto updateDto){

        return updateStaffService.updateCustomer(updateDto);
    }
}
