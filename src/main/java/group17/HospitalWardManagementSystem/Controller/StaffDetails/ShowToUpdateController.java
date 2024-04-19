package group17.HospitalWardManagementSystem.Controller.StaffDetails;

import group17.HospitalWardManagementSystem.Model.Dto.StaffDto.ShowToUpdateDto;
import group17.HospitalWardManagementSystem.Service.StaffDetails.ShowToUpdateService;
import group17.HospitalWardManagementSystem.Service.WardDetails.ShowWardService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShowToUpdateController {


    public final ShowToUpdateService showToUpdateService;
    @Autowired
    public ShowToUpdateController(ShowToUpdateService showToUpdateService) {
        this.showToUpdateService = showToUpdateService;
    }

    @GetMapping("/update/{wardNo}")
    public ShowToUpdateDto showStaffFull(@PathVariable String wardNo){
        return showToUpdateService.showfullStaff(wardNo);
    }
}
