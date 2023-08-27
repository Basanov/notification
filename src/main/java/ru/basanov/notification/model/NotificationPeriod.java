package ru.basanov.notification.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "notification_period")
@Getter
@Setter
@NoArgsConstructor
public class NotificationPeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "day_of_week")
    private DayOfWeek dayOfWeek;

    @Column(name = "start_date")
    private LocalTime startTime;

    @Column(name = "end_date")
    private LocalTime endTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",
                nullable = false,
                foreignKey = @ForeignKey(name = "FK_USER_ID")
    )
    private User user;

}
