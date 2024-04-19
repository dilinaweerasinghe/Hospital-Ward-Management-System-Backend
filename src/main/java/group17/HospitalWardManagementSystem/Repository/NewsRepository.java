package group17.HospitalWardManagementSystem.Repository;

import group17.HospitalWardManagementSystem.Model.Domain.News;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import group17.HospitalWardManagementSystem.Model.Dto.NewsDto.NewsDto;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News,Long> {

    @Query("select A.newsHeader,A.newsDescription,B.newsAdder,B.position,A.pushedDate,A.comment,A.imgUrl from News A inner join ")
    List<NewsDto> findBy(@Param("wardNo")Ward wardNo);
}
