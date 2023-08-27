package ru.basanov.notification.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.basanov.notification.model.CalendarMessage;

import java.util.Optional;

@Repository
public interface CalendarMessageRepository extends PagingAndSortingRepository<CalendarMessage, Long> {

    public CalendarMessageRepository save(CalendarMessage calendarMessage);

    void deleteCalendarMessageById(Long id);

    Page<CalendarMessage> findAll();

    Optional<CalendarMessage> findById(Long id);
}
