package group17.HospitalWardManagementSystem.Controller;


import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Dto.JwtRequest;
import group17.HospitalWardManagementSystem.Model.Dto.JwtResponse;
import group17.HospitalWardManagementSystem.Repository.ProPictureRepository;
import group17.HospitalWardManagementSystem.Service.Login.JwtService;
import group17.HospitalWardManagementSystem.Service.MatronDetails.RegisterMatronService;
import group17.HospitalWardManagementSystem.Service.RegistrationService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import group17.HospitalWardManagementSystem.Repository.UserRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@RestController
public class LoginController {


    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RegistrationService registrationService;
    private final UserRepository userRepository;
    private final ProPictureRepository proPictureRepository;

    private final RegisterMatronService registerMatronService;

    @Autowired
    public LoginController(JwtService jwtService, AuthenticationManager authenticationManager, RegistrationService registrationService, UserRepository userRepository, ProPictureRepository proPictureRepository, RegisterMatronService registerMatronService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.registrationService = registrationService;
        this.userRepository = userRepository;
        this.proPictureRepository = proPictureRepository;
        this.registerMatronService = registerMatronService;
    }


    @PostMapping("/authenticate")
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception{
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        if(authenticate.isAuthenticated()){
            System.out.println("Here");
            String token =  jwtService.generateToken(jwtRequest.getUsername());
            User user =  userRepository.findByUsername(jwtRequest.getUsername());
            String url = proPictureRepository.findByUser(user).isPresent() ? proPictureRepository.findByUser(user).get().getImgUrl() : null;
            return new JwtResponse(user, token, url);
        }else {
            throw new UsernameNotFoundException("Invalid user request");
        }
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
