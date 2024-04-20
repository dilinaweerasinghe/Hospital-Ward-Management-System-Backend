package group17.HospitalWardManagementSystem.Config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfiguration {

    @Value("${aws.accessKeyId}")  //AKIAU6GD2S3ME4CP5MCM->replace this in application.property
    private String accessKey;

    @Value("${aws.secretKey}")  //npUGtPUUAV55zPfu2pjjUYPan32R4KyflQ5CVfak->replace this in application.property
    private String secretKey;

    @Value("${aws.region}")  //eu-north-1->replace this in application.property
    private String awsRegion;


    @Bean
    public AmazonS3 generateS3Client(){
        AWSCredentials credentials=new BasicAWSCredentials(accessKey,secretKey);
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(awsRegion).build();
    }
}
