package group17.HospitalWardManagementSystem.Service.Amazon;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.S3Object;
import group17.HospitalWardManagementSystem.Config.AWSConfiguration;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

@Service
@Slf4j
public class AmazonService {

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Autowired
    private AmazonS3 amazonS3Client;


    public String uploadFile(MultipartFile file){
        File fileObj=convertMultipartfileToFile(file);
        String fileName= System.currentTimeMillis()+"-"+file.getOriginalFilename();
        amazonS3Client.putObject(bucketName,fileName,fileObj);


        String fileUrl = amazonS3Client.getUrl(bucketName, fileName).toString();
        return fileUrl;

    }

//

    public String deleteFile(String imageUrl){
        String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
        amazonS3Client.deleteObject(bucketName,fileName);
        return "File was deleted";
    }

    public File convertMultipartfileToFile(MultipartFile file){
        File convertedFile=new File(file.getOriginalFilename());
        try(FileOutputStream fos=new FileOutputStream(convertedFile)){
            fos.write(file.getBytes());
        }catch (IOException e){
            System.out.println(e);
        }
        return  convertedFile;
    }
}
