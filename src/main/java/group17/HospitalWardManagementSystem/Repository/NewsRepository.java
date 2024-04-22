package group17.HospitalWardManagementSystem.Repository;

import group17.HospitalWardManagementSystem.Model.Domain.News;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import group17.HospitalWardManagementSystem.Model.Dto.NewsItemDto.NewsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News,Long> {

    @Query("select new group17.HospitalWardManagementSystem.Model.Dto.NewsItemDto.NewsDto(A.newsHeader,A.newsDescription,B.fullName,B.Position,A.pushedDate,A.comment,A.imgUrl) from News A inner join A.newsAdder B inner  join Staff C on B.nic=C.nic where C.wardNo=:wardNo order by A.pushedDate limit 5")
    List<NewsDto> findBy(@Param("wardNo")Ward wardNo);
}
