package group17.HospitalWardManagementSystem.Model.Dto.NewsDto;


import group17.HospitalWardManagementSystem.Model.Domain.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class NewsDto {


    private String newsHeader;

    private String newsDescription;

    private String newsAdder;

    private String position;

    private LocalDate pushedDate;

    private String comment;

    private String imgUrl;
}
