package group17.HospitalWardManagementSystem.Controller.Admin;

import group17.HospitalWardManagementSystem.Model.Dto.Matron.GetMatronDto;
import group17.HospitalWardManagementSystem.Model.Dto.Matron.MatronDto;
import group17.HospitalWardManagementSystem.Service.AdminServices.MatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class MatronController {
    @Autowired
    private MatronService matronService;
    @PostMapping("/matron/add")
    public ResponseEntity<String> AddMatron(@RequestBody MatronDto matronDto){
        return matronService.AddMatron(matronDto) ? ResponseEntity.ok("Added Successfully") : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed Adding Matron! Try again later.");
    }

    @GetMapping("/matron/get")
    public List<GetMatronDto> getMatronDetails(){
        return matronService.getMatronDetailService();
    }
}
