package group17.HospitalWardManagementSystem.Controller.WardDetails;

import group17.HospitalWardManagementSystem.Model.Dto.WardDto.ShowFullWardDto;
import group17.HospitalWardManagementSystem.Service.WardDetails.ShowFullWardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShowFullWardController {

    @Autowired
    public ShowFullWardService showFullWardService;

    @GetMapping("/show-fullward/{wardNo}")
    public ShowFullWardDto showFullWardDetails(@PathVariable String wardNo){
        return showFullWardService.showFullWard(wardNo);
    }
}
