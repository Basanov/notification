package ru.basanov.notification.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name = "calendar_message")
@Setter
@Getter
public class CalendarMessage {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "planed_date_time")
    private LocalDateTime planedDateTime;

    @Column(name = "is_send")
    private boolean isSend;

    @OneToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public CalendarMessage(LocalDateTime planedDateTime) {
        this.planedDateTime = planedDateTime;
    }

}
