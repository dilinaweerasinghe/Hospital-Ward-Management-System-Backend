package group17.HospitalWardManagementSystem.Service.GeneralServices;

import org.springframework.stereotype.Service;

@Service
public class UsernameGenerateService {
    public String generateUsername(String email){
        int atIndex=email.indexOf("@");

        if (atIndex != -1) {
            return email.substring(0, atIndex);
        } else {
            return null;
        }
    }
}
