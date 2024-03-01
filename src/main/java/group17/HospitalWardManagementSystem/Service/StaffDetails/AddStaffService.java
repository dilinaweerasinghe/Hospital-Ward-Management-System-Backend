package group17.HospitalWardManagementSystem.Service.StaffDetails;

import group17.HospitalWardManagementSystem.Model.Domain.Staff;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import group17.HospitalWardManagementSystem.Model.Dto.StaffDto.AddStaffDto;
import group17.HospitalWardManagementSystem.Model.Dto.StaffDto.MailDto;
import group17.HospitalWardManagementSystem.Model.UserRole;
import group17.HospitalWardManagementSystem.Repository.AddStaffRepository;
import group17.HospitalWardManagementSystem.Repository.AddUserRepository;
import group17.HospitalWardManagementSystem.Repository.WardRepository;
import group17.HospitalWardManagementSystem.Service.GeneralServices.MailService;
import group17.HospitalWardManagementSystem.Service.GeneralServices.PasswordGenerateService;
import group17.HospitalWardManagementSystem.Service.GeneralServices.UsernameGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AddStaffService {

    @Autowired
    public AddStaffRepository addStaffRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AddUserRepository addUserRepository;

    @Autowired
    public WardRepository wardRepository;

    @Autowired
    public MailService mailService;

    @Autowired
    private PasswordGenerateService passwordGenerateService;

    @Autowired
    private UsernameGenerateService usernameGenerateService;

    public String staffSave(AddStaffDto addStaffDto){

        User user=new User();
        Staff staff=new Staff();
        Ward ward=new Ward();


        String password=passwordGenerateService.passwordGenerate();

        String username=usernameGenerateService.generateUsername(addStaffDto.getEmail());


        user.setNic(addStaffDto.getNic());
        user.setFullName(addStaffDto.getFullName());
        user.setFirstName(addStaffDto.getFirstName());
        user.setLastName(addStaffDto.getLastName());
        user.setUsername(username);
        user.setPassword(getEncodedPassword(password));
        user.setDob(addStaffDto.getDob());
        user.setEmail(addStaffDto.getEmail());
        if(addStaffDto.getPosition().equals("Admin")){
            user.setPosition(UserRole.Admin);
        }else if(addStaffDto.getPosition().equals("Matron")){
            user.setPosition(UserRole.Matron);
        }else if(addStaffDto.getPosition().equals("Sister")){
            user.setPosition(UserRole.Sister);
        }else if(addStaffDto.getPosition().equals("Nurse")){
            user.setPosition(UserRole.Nurse);
        }
        user.setMobileNo(addStaffDto.getMobileNo());
        staff.setNic(addStaffDto.getNic());
        staff.setWardNo(findWard(addStaffDto));
        staff.setServiceStartedDate(addStaffDto.getServiceStartedDate());
        staff.setLeaveNum(addStaffDto.getLeaveNum());
        staff.setRemainingCasualLeaves(addStaffDto.getRemainingCasualLeaves());
        staff.setRemainingVacationLeave(addStaffDto.getRemainingVacationLeave());

         //new add
        addUserRepository.save(user);
        addStaffRepository.save(staff);

        MailDto mailDto=new MailDto();

        mailDto.setUsername(username);
        mailDto.setPassword(password);

        mailService.sendMail(user.getEmail(),mailDto);

        return "Successfully added ward Details";
    }
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public Ward findWard(AddStaffDto addStaffDto){
        return wardRepository.findByWardNo(addStaffDto.getWardNo());
    }

//    public String passwordGenerate(){
//        String upper="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//        String lower="abcdefghijklmnopqrstuvwxyz";
//        String num="0123456789";
//        String specialChars="<>,/?}{][*%^#@&";
//        String combination=upper+lower+num+specialChars;
//        int len=8;
//
//        char[] password=new char[len];
//
//        Random r=new Random();
//
//        for (int i=0;i<8;i++){
//            password[i]=combination.charAt(r.nextInt(combination.length()));
//        }
//
//        return new String(password);
//    }

//    public String generateUsername(String email){
//        int atIndex=email.indexOf("@");
//
//        if (atIndex != -1) {
//            return email.substring(0, atIndex);
//        } else {
//            return null;
//        }
//    }
}



