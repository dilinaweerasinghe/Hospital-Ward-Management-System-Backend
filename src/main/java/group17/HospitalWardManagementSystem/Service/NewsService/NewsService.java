package group17.HospitalWardManagementSystem.Service.NewsService;

import group17.HospitalWardManagementSystem.Model.Domain.News;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Repository.NewsRepository;
import group17.HospitalWardManagementSystem.Repository.UserRepository;
import group17.HospitalWardManagementSystem.Service.Amazon.AmazonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AmazonService amazonService;
    public void addNews(String newsHeader, String newsDescription, String newsAdderId,String comment,String imageUrl){

        News news=new News();
        news.setNewsHeader(newsHeader);
        news.setNewsAdder(findUser(newsAdderId));
        news.setNewsDescription(newsDescription);
        news.setPushedDate(LocalDate.now());
        news.setComment(comment);
        news.setImgUrl(imageUrl);

        newsRepository.save(news);

    }

    public User findUser(String id){
        return userRepository.findByNic(id).get();
    }

    public String uploadImageToS3(MultipartFile image) throws IOException {
        return amazonService.uploadFile(image);
    }


}
