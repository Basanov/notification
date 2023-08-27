package ru.basanov.notification.service;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.basanov.notification.model.CalendarMessage;
import ru.basanov.notification.repository.CalendarMessageRepository;

import java.util.Optional;

@Service
public class CalendarMessageService {

    private final CalendarMessageRepository calendarMessageRepository;

    public CalendarMessageService(CalendarMessageRepository calendarMessageRepository) {
        this.calendarMessageRepository = calendarMessageRepository;
    }

    @Transactional
    public CalendarMessageRepository save(CalendarMessage calendarMessage) {
        return calendarMessageRepository.save(calendarMessage);
    }

    @Transactional
    public Page<CalendarMessage> getAll(Pageable pageable) {
        Page<CalendarMessage> calendarMessages = calendarMessageRepository.findAll();
        return calendarMessages;
    }

    public Optional<CalendarMessage> findById(Long id) {
        return calendarMessageRepository.findById(id);
    }

    public void deleteCalendarById(Long id) {
        calendarMessageRepository.deleteCalendarMessageById(id);
    }

    public Iterable<CalendarMessage> findAll() {
        return calendarMessageRepository.findAll();
    }

}
