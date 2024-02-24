package group17.HospitalWardManagementSystem.Controller.ResetPassword;

import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Dto.PassWord.PasswordResetRequestDto;
import group17.HospitalWardManagementSystem.Service.PasswordResetTokenService;
import group17.HospitalWardManagementSystem.Service.StaffDetails.MailService;
import group17.HospitalWardManagementSystem.Service.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class ResetPasswordController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Autowired
    private PasswordResetTokenService passwordResetTokenService;

    @PostMapping("/password-reset-request")
    public String resetPasswordRequest(@RequestBody PasswordResetRequestDto passwordResetRequestDto,
                                       final HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        Optional<User> user=userService.findByEmail(passwordResetRequestDto.getEmail());
        String passwordResetURL="";

        String passwordResetToken= UUID.randomUUID().toString();

        if(user.isPresent()){

            userService.createPasswordResetTokenForUser(user.get(),passwordResetToken);
            passwordResetURL=passwordResetEmailLink(user.get(),applicationURL(request),passwordResetToken);
            return passwordResetURL;
        }else {
            userService.findUserByPasswordTokenToDelete(passwordResetToken);
            return "Enter your correct recovery email";
        }
    }

    private String passwordResetEmailLink(User user, String applicationURL, String passwordResetToken) throws MessagingException, UnsupportedEncodingException {
        String url = applicationURL+"/register/reset-password?token="+passwordResetToken;
        mailService.sendPasswordResetVerificationEmail(url,user);
        log.info("Click the link to reset your password :  {}", url);
        return  url;
    }

    public String applicationURL(HttpServletRequest request){
        return "http://"+request.getServerName()+":"+
                request.getServerPort()+request.getContextPath();
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestBody PasswordResetRequestDto passwordResetRequestDto,
                                @RequestParam("token") String passwordResetToken){
        String validatePasswordResetTokenResult=passwordResetTokenService.validatePasswordResetToken(passwordResetToken);

        if(validatePasswordResetTokenResult.equalsIgnoreCase("valid")){
            Optional<User> theUser = Optional.ofNullable(userService.findUserByPasswordToken(passwordResetToken));
            userService.findUserByPasswordTokenToDelete(passwordResetToken);
            if(theUser.isPresent()){
                userService.changePassword(theUser.get(), passwordResetRequestDto.getNewPassword());

                return "Password has reset successfully!";
            }else{
                return "Token Expired";
            }
        }



        return "Invalid Password reset token";
    }
}
