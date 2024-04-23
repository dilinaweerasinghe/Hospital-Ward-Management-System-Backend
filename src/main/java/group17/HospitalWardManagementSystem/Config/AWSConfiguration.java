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

    @Value("${aws.accessKeyId}")  //access key id>replace this in application.property
    private String accessKey;

    @Value("${aws.secretKey}")  //secret key ->replace this in application.property
    private String secretKey;

    @Value("${aws.region}")  //region->replace this in application.property
    private String awsRegion;


    @Bean
    public AmazonS3 generateS3Client(){
        AWSCredentials credentials=new BasicAWSCredentials(accessKey,secretKey);
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(awsRegion).build();
    }
}
