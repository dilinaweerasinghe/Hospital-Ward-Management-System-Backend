package group17.HospitalWardManagementSystem.Controller.News;

import group17.HospitalWardManagementSystem.Service.NewsService.NewsService;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/news")
public class NewController {

    @Autowired
    private NewsService newsService;

    @PostMapping("/add")
    public ResponseEntity<String> addNews(@RequestParam("newsHeader") String newsHeader,
                                          @RequestParam("newsDescription") String newsDescription,
                                          @RequestParam("newsAdderId") String newsAdderId,
                                          @RequestParam("comment") String comment,
                                          @RequestParam("image") MultipartFile image){

        try {
            String imageUrl = newsService.uploadImageToS3(image);
            newsService.addNews(newsHeader, newsDescription, newsAdderId,comment,imageUrl);
            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Failed to add news");
        }
    }
}
