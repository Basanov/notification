package ru.basanov.notification.service;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.basanov.notification.model.Event;
import ru.basanov.notification.repository.EventRepository;

import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Transactional
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Transactional
    public Page<Event> getAll(Pageable pageable) {
        Page<Event> events = eventRepository.findAll(pageable);
        return events;
    }

    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    public void deleteEventById(Long id) {
        eventRepository.deleteEventById(id);
    }

    public Iterable<Event> findAll() {
        return eventRepository.findAll();
    }
}
