package group17.HospitalWardManagementSystem.Service.Profile;

import group17.HospitalWardManagementSystem.Model.Domain.ProPicture;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Repository.ProPictureRepository;
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

    public String uploadImageToS3(MultipartFile file){
        return amazonService.uploadFile(file);
    }
    public User findUser(String nic){
        return userRepository.findByNic(nic).get();
    }

    public Optional<ProPicture> findProPicture(User user){
        return proPictureRepository.findByUser(user);
    }
}
