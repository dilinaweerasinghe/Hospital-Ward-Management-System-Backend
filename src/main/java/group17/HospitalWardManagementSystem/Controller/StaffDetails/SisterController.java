package group17.HospitalWardManagementSystem.Controller.StaffDetails;

import group17.HospitalWardManagementSystem.Model.Dto.StaffDto.SisterDtoForMatron;
import group17.HospitalWardManagementSystem.Service.StaffDetails.SisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SisterController {

    @Autowired
    SisterService sisterService;

    @GetMapping("/get-sister-details-matron/{wardNo}")
    public SisterDtoForMatron getSisterForMatron(@PathVariable String wardNo){
        return sisterService.findSisterForMatron(wardNo);
    }

    @PutMapping("/update-sister-details-matron")
    public String setSisterForMatron(@RequestBody SisterDtoForMatron sisterDtoForMatron){
        System.out.println(sisterDtoForMatron);
        return sisterService.saveSisterEditedDetailsForMatron(sisterDtoForMatron);
    }
}
