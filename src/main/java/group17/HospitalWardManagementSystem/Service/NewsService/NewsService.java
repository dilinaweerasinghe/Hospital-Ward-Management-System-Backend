package group17.HospitalWardManagementSystem.Service.NewsService;

import group17.HospitalWardManagementSystem.Model.Domain.*;
import group17.HospitalWardManagementSystem.Model.Dto.NewsItemDto.NewsDto;
import group17.HospitalWardManagementSystem.Model.UserRole;
import group17.HospitalWardManagementSystem.Repository.*;
import group17.HospitalWardManagementSystem.Service.Amazon.AmazonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NewsService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AmazonService amazonService;

    @Autowired
    private ProPictureRepository proPictureRepository;

    @Autowired
    private MatronRepository matronRepository;

    public void addNews(String newsHeader, String newsDescription, String newsAdderId,String comment,String imageUrl){

        News news=new News();
        news.setNewsHeader(newsHeader);
        news.setNewsAdder(findUser(newsAdderId));
        news.setNewsDescription(newsDescription);
        news.setPushedDate(LocalDate.now());
        news.setComment(comment);
        news.setImgUrl(imageUrl);
        news.setProImgUrl(findProPicture(findUser(newsAdderId)).getImgUrl());

        newsRepository.save(news);

    }

    public String deleteNews(Long id) throws NoSuchFieldException {
        if(findNews(id).isPresent()){
            News news=findNews(id).get();
            amazonService.deleteFile(news.getImgUrl());
            newsRepository.delete(news);
            return "Successfully Deleted the News";
        }else{
           throw new NoSuchFieldException("No such news find");
        }
    }

    public Optional<News> findNews(Long id){
        return newsRepository.findById(id);
    }

    public List<NewsDto> findNewsList(String nic){
        Staff staff=findStaff(nic);
        User user=findUser(nic);
        Matron matron=matronRepository.findByNic(nic);
        if(user.getPosition().equals(UserRole.Matron)){
            return newsRepository.getNewsByNewsAdderOrWard(nic);
        }else{
            return newsRepository.findBy(staff.getWardNo());
        }

    }

    public User findUser(String id){
        return userRepository.findByNic(id).get();
    }

    public String uploadImageToS3(MultipartFile image) throws IOException {
        return amazonService.uploadFile(image);
    }

    public Staff findStaff(String nic){
        return staffRepository.findByNic(nic);
    }

    public ProPicture findProPicture(User user){
        return proPictureRepository.findByUser(user).get();
    }


}
