package group17.HospitalWardManagementSystem.Controller.ResetPassword;

import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Dto.PassWord.NewPwdDto;
import group17.HospitalWardManagementSystem.Model.Dto.PassWord.PasswordResetRequestDto;
import group17.HospitalWardManagementSystem.Service.GeneralServices.PasswordGenerateService;
import group17.HospitalWardManagementSystem.Service.PasswordResetTokenService;
import group17.HospitalWardManagementSystem.Service.GeneralServices.MailService;
import group17.HospitalWardManagementSystem.Service.UserService;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private PasswordGenerateService passwordGenerateService;

    @Autowired
    private NewPwdDto newPwdDto;

    @PostMapping("/password-reset-request")
    public ResponseEntity<String> resetPasswordRequest(@RequestBody PasswordResetRequestDto passwordResetRequestDto,
                                               final HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {

        String passwordResetToken=passwordGenerateService.passwordGenerate();
        try{
            User user = userService.findByEmail(passwordResetRequestDto.getEmail()).orElseThrow(()-> (new EntityNotFoundException("Recovery email not existing in the System!")));
            String passwordResetURL="";

            //String passwordResetToken= UUID.randomUUID().toString()

                userService.createPasswordResetTokenForUser(user,passwordResetToken);
                passwordResetURL=passwordResetEmailLink(user,applicationURL(request),passwordResetToken);
                return ResponseEntity.ok(passwordResetURL);
        }catch (EntityNotFoundException e) {
            userService.findUserByPasswordTokenToDelete(passwordResetToken);
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch(IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Recovery email not existing in the System!");
        }catch (DataAccessException e) {
            return ResponseEntity.internalServerError().body("Database error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An unexpected error occurred: " + e.getMessage());
        }

    }

    private String passwordResetEmailLink(User user, String applicationURL, String passwordResetToken) throws MessagingException, UnsupportedEncodingException {
        String url = applicationURL+"/register/reset-password?token="+passwordResetToken;
        mailService.sendPasswordResetVerificationEmail(passwordResetToken,user);
        log.info("Click the link to reset your password :  {}", url);
        return  url;
    }

    public String applicationURL(HttpServletRequest request){
        return "http://"+request.getServerName()+":"+
                request.getServerPort()+request.getContextPath();
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestBody NewPwdDto newPwdDto
                                ){
        String passwordResetToken=newPwdDto.getOtp();
        String validatePasswordResetTokenResult=passwordResetTokenService.validatePasswordResetToken(passwordResetToken);

        if(validatePasswordResetTokenResult.equalsIgnoreCase("valid")){
            Optional<User> theUser = Optional.ofNullable(userService.findUserByPasswordToken(passwordResetToken));
            userService.findUserByPasswordTokenToDelete(passwordResetToken);
            if(theUser.isPresent()){
                userService.changePassword(theUser.get(), newPwdDto.getNewpassword());

                return "Password has reset successfully!";
            }else{
                return "Token Expired";
            }
        }



        return "Invalid Password reset token";
    }
}
