package group17.HospitalWardManagementSystem.Controller.WardDetails;

import group17.HospitalWardManagementSystem.Model.Dto.WardDto.ShowWardDto;
import group17.HospitalWardManagementSystem.Service.WardDetails.ShowWardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShowWardDetailController {
    @Autowired
    private ShowWardService showWardService;

    @GetMapping("/show-ward/{wardNo}")
    public ShowWardDto showWardDetails(@PathVariable String wardNo){
        return showWardService.showWardDetails(wardNo);
    }

}
