package group17.HospitalWardManagementSystem.Controller.News;

import group17.HospitalWardManagementSystem.Model.Dto.NewsItemDto.NewsDto;
import group17.HospitalWardManagementSystem.Service.NewsService.NewsService;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/news")
public class NewController {

    @Autowired
    private NewsService newsService;

    @PostMapping("/add")
    @Transactional
    public ResponseEntity<String> addNews(@RequestParam("newsHeader") String newsHeader,
                                          @RequestParam("newsDescription") String newsDescription,
                                          @RequestParam("newsAdderId") String newsAdderId,
                                          @RequestParam("comment") String comment,
                                          @RequestParam("image") MultipartFile image){

        try {
            String imageUrl = newsService.uploadImageToS3(image);
            newsService.addNews(newsHeader, newsDescription, newsAdderId,comment,imageUrl);
            return ResponseEntity.ok("Successfully Posted News...!");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Failed to add news");
        }
    }

    @GetMapping("/retrieve/{nic}")
    public ResponseEntity<?> retrieveNews(@PathVariable String nic){
        try{
            List<NewsDto> newsList=newsService.findNewsList(nic);
            return ResponseEntity.ok(newsList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Failed to add news");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteNews(@PathVariable Long id){
        try{
           return ResponseEntity.ok(newsService.deleteNews(id));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Failed to delete news");
        }
    }
}
