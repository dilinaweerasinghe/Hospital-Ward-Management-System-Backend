package group17.HospitalWardManagementSystem.Controller.Admin;

import group17.HospitalWardManagementSystem.Model.Dto.StaffDto.AddMatronDto;
import group17.HospitalWardManagementSystem.Service.AdminServices.MatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class MatronController {
    @Autowired
    private MatronService matronService;
    @PostMapping("/matron/add")
    public ResponseEntity<String> AddMatron(@RequestBody AddMatronDto addMatronDto){
        return matronService.AddMatron(addMatronDto) ? ResponseEntity.ok("Added Successfully") : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed Adding Matron! Try again later.");
    }
}
