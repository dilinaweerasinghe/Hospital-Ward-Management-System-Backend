package group17.HospitalWardManagementSystem.Service.Profile;

import group17.HospitalWardManagementSystem.Model.Domain.ProPicture;
import group17.HospitalWardManagementSystem.Model.Domain.Staff;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Dto.Profile.ProDetailsLoadDto;
import group17.HospitalWardManagementSystem.Model.UserRole;
import group17.HospitalWardManagementSystem.Repository.ProPictureRepository;
import group17.HospitalWardManagementSystem.Repository.StaffRepository;
import group17.HospitalWardManagementSystem.Repository.UserRepository;
import group17.HospitalWardManagementSystem.Service.Amazon.AmazonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProPictureRepository proPictureRepository;

    @Autowired
    public AmazonService amazonService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StaffRepository staffRepository;

   //@Transactional
    public void addProfilePicture(MultipartFile file,String nic){
        try{
            ProPicture proPicture=new ProPicture();
            User user=findUser(nic);
            if(findProPicture(user).isPresent()){
                amazonService.deleteFile(findProPicture(user).get().getImgUrl());
                proPictureRepository.delete(findProPicture(user).get());
                proPicture.setImgUrl(uploadImageToS3(file));
                proPicture.setUser(user);
                proPictureRepository.save(proPicture);
            }else{
                proPicture.setImgUrl(uploadImageToS3(file));
                proPicture.setUser(user);
                proPictureRepository.save(proPicture);
            }

        }catch(Exception e){
            throw new RuntimeException("Profile picture Updatio failed");
        }

    }

    public String retrieveProPicture(String nic){
        if(findProPicture(findUser(nic)).isPresent()){
            ProPicture proPicture=findProPicture(findUser(nic)).get();
            return proPicture.getImgUrl();
        }else{
            throw new RuntimeException("Not profile picture Uploaded");
        }
    }

    public ProDetailsLoadDto retrieveProDetails(String nic){
        ProDetailsLoadDto proDetailsLoadDto=new ProDetailsLoadDto();
        User user=findUser(nic);
        Staff staff=findStaff(nic);

        proDetailsLoadDto.setNic(user.getNic());
        proDetailsLoadDto.setFullName(user.getFullName());
        proDetailsLoadDto.setUsername(user.getUsername());
        proDetailsLoadDto.setDob(user.getDob());
        proDetailsLoadDto.setEmail(user.getEmail());
        proDetailsLoadDto.setMobileNo(user.getMobileNo());
        if(user.getPosition().equals(UserRole.Admin)){
            proDetailsLoadDto.setPosition("Admin");
        } else if (user.getPosition().equals(UserRole.Matron)) {
            proDetailsLoadDto.setPosition("Matron");
        } else if (user.getPosition().equals(UserRole.Sister)) {
            proDetailsLoadDto.setPosition("Sister");
        } else if (user.getPosition().equals(UserRole.Nurse)) {
            proDetailsLoadDto.setPosition("Nurse");
        }else{
            proDetailsLoadDto.setPosition("Currently No confirm");
        }
        proDetailsLoadDto.setServiceStartedDate(staff.getServiceStartedDate());
        proDetailsLoadDto.setRemainingCasualLeaves(staff.getRemainingCasualLeaves());
        proDetailsLoadDto.setRemainingVacationLeave(staff.getRemainingVacationLeave());

        return proDetailsLoadDto;
    }

    public String uploadImageToS3(MultipartFile file){
        return amazonService.uploadFile(file);
    }
    public User findUser(String nic){
        return userRepository.findByNic(nic).get();
    }

    public Optional<ProPicture> findProPicture(User user){
        return proPictureRepository.findByUser(user);
    }

    public Staff findStaff(String nic){
        return staffRepository.findByNic(nic);
    }
}
