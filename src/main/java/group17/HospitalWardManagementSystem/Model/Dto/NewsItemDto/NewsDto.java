package group17.HospitalWardManagementSystem.Model.Dto.NewsItemDto;


import group17.HospitalWardManagementSystem.Model.UserRole;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto {


    private String newsHeader;

    private String newsDescription;

    private String fullName;

    private UserRole Position;

    private LocalDate pushedDate;

    private String comment;

    private String imgUrl;

    private String proImgUrl;
}
