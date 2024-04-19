package group17.HospitalWardManagementSystem.Controller.WardDetails;

import group17.HospitalWardManagementSystem.Model.Dto.WardDto.AllWardNamesDto;
import group17.HospitalWardManagementSystem.Service.WardDetails.getAllWardNamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AllWardController {

    @Autowired
    private getAllWardNamesService getAllWardNamesService;

    @GetMapping("/get-ward-names/{username}")
    public AllWardNamesDto getAllWardNames(@PathVariable String username){
        return new AllWardNamesDto(getAllWardNamesService.allWardsName(username));
    }
}
