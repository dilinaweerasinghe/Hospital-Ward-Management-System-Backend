package group17.HospitalWardManagementSystem.Model.Domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String newsHeader;
    @Column(nullable = false)
    private String newsDescription;
    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "news_adder", referencedColumnName = "nic")
    private User newsAdder;
    @Column(nullable = false)
    private LocalDate pushedDate;
    private String comment;
    //



}
