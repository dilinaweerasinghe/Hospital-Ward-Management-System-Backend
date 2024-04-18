package group17.HospitalWardManagementSystem.Controller.WardDetails;

import group17.HospitalWardManagementSystem.Model.Dto.WardDto.ShowFullWardDto;
import group17.HospitalWardManagementSystem.Service.WardDetails.ShowFullWardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShowFullWardController {

    @Autowired
    public ShowFullWardService showFullWardService;

    @GetMapping("/show-fullward/{wardName}")
    public ShowFullWardDto showFullWardDetails(@PathVariable String wardName){
        return showFullWardService.showFullWard(wardName);
    }

    @GetMapping("/show-fullward-By-Sister/{username}")
    public ShowFullWardDto showFullWardDetailsSister(@PathVariable String username){
        return showFullWardService.getAllWardDetailsSister(username);
    }

    @GetMapping("/show-available-sisters")
    public List<String> GetAllSisters(){
        return showFullWardService.findAllSisters();
    }
}


