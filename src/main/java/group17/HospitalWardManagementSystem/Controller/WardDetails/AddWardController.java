package group17.HospitalWardManagementSystem.Controller.WardDetails;

import group17.HospitalWardManagementSystem.Model.Dto.WardDto.AddWardDto;
import group17.HospitalWardManagementSystem.Service.WardDetails.AddWardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ward")
public class AddWardController {

    @Autowired
    public AddWardService addWardService;

    @PostMapping("/add-ward")
    public String addWard(@RequestBody AddWardDto addWardDto){

        return addWardService.saveWard(addWardDto);
    }

    @GetMapping("/get-all-matrons")
    public List<String> getAllMatrons(){
        System.out.println(addWardService.allMatrons());
        return addWardService.allMatrons();
    }
}
