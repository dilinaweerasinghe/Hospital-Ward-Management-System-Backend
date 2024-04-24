package group17.HospitalWardManagementSystem.Model.Domain;

import group17.HospitalWardManagementSystem.Model.LeaveStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApprovedLeaves {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leaveId;

    @ManyToOne
    @JoinColumn(name = "staff_nic",referencedColumnName = "nic")
    private Staff staff;

    @Column(nullable = false)
    private LocalDate leaveBeginDate;

    @Column(nullable = false)
    private LocalDate leaveEndDate;

    @Column(nullable = false)
    private LocalDateTime requestedDateAndTime;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LeaveStatus status;
}
