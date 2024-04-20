package group17.HospitalWardManagementSystem.Controller.StaffDetails;

import group17.HospitalWardManagementSystem.Model.Dto.StaffDto.FullStaffMmeberDetailsDto;
import group17.HospitalWardManagementSystem.Model.Dto.StaffDto.ShowToUpdateDto;
import group17.HospitalWardManagementSystem.Service.StaffDetails.EditStaffMemberDetailsService;
import group17.HospitalWardManagementSystem.Service.StaffDetails.ShowToUpdateStaffMemberDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StaffMemeberShowEditController {

    @Autowired
    EditStaffMemberDetailsService editStaffMemberDetailsService;

    @Autowired
    ShowToUpdateStaffMemberDetailsService showToUpdateStaffMemberDetailsService;

    @GetMapping("/get-sister-details-matron/{wardNo}")
    public FullStaffMmeberDetailsDto getSisterDetails(@PathVariable String wardNo){
        return editStaffMemberDetailsService.findSister(wardNo);
    }

    @PutMapping("/update-sister-details-matron")
    public boolean setSisterDetails(@RequestBody FullStaffMmeberDetailsDto sisterDtoForMatron){
        System.out.println(sisterDtoForMatron);
        return editStaffMemberDetailsService.saveStaffMemberEditedDetails(sisterDtoForMatron);
    }

    @GetMapping("/get-nurse-details/{nic}")
    public ShowToUpdateDto getNurseDetails(@PathVariable String nic){
        return showToUpdateStaffMemberDetailsService.showNurse(nic);
    }

    @PutMapping("/update-nurse-details")
    public boolean setSisterDetails(@RequestBody ShowToUpdateDto sisterDtoForMatron){
        System.out.println(sisterDtoForMatron);
        return editStaffMemberDetailsService.saveNurseEditedDetails(sisterDtoForMatron);
    }
}
