package group17.HospitalWardManagementSystem.Service.StaffDetails;

import group17.HospitalWardManagementSystem.Model.Dto.StaffDto.MailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromMail;

    public String sendMail(String email, MailDto mailDto){
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();

        simpleMailMessage.setFrom(fromMail);
        simpleMailMessage.setSubject(mailDto.getUsername());
        simpleMailMessage.setText(mailDto.getPassword());
        simpleMailMessage.setTo(email);

        javaMailSender.send(simpleMailMessage);
        return "Mail send successfully";
    }
}
