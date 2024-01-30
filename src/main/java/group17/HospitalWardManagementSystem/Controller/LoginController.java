package group17.HospitalWardManagementSystem.Controller;


import group17.HospitalWardManagementSystem.Model.Dto.JwtRequest;
import group17.HospitalWardManagementSystem.Model.Dto.JwtResponse;
import group17.HospitalWardManagementSystem.Service.Login.JwtService;
import group17.HospitalWardManagementSystem.Service.RegistrationService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {


    @Autowired
    private JwtService jwtService;

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/authenticate")
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception{
        return jwtService.CreateJwtToken(jwtRequest);
    }

    @PostMapping("/registerNewUser")
    public String RegisterUser(){
        return "Correct";
    }
    @PostConstruct
    public void initAdmin(){
        registrationService.initUser();
    }

    @GetMapping("/send")
    public String Send(){
        return "Correct";
    }


}
