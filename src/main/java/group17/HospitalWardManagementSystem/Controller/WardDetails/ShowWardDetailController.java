package group17.HospitalWardManagementSystem.Controller.WardDetails;

import group17.HospitalWardManagementSystem.Model.Dto.WardDto.ShowWardDto;
import group17.HospitalWardManagementSystem.Service.WardDetails.ShowLoggedUserWardDetailsService;
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

    @Autowired
    private ShowLoggedUserWardDetailsService showLoggedUserWardDetailsService;

    @GetMapping("/show-ward/{wardName}")
    public ShowWardDto showWardDetails(@PathVariable String wardName){
        return showWardService.showWardDetails(wardName);
    }

    @GetMapping("/show-logged-user-ward/{username}")
    public ShowWardDto showLoggedUserWard(@PathVariable String username){
        return showLoggedUserWardDetailsService.getLoggedUserWardDetails(username);
    }
}
