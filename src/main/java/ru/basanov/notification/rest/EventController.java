package ru.basanov.notification.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.basanov.notification.exception.EventException;
import ru.basanov.notification.model.Event;
import ru.basanov.notification.service.EventService;

@RestController
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    Iterable<Event> all() {
        return eventService.findAll();
    }

    @PostMapping("/events")
    Event newEvent(@RequestBody Event newEvent) {
        return eventService.save(newEvent);
    }

    @GetMapping("/event/{id}")
    Event one(@PathVariable Long id) {
        return eventService.findById(id).orElseThrow(() -> new EventException(id));
    }

    @PutMapping("/event/{id}")
    Event replaceEvent(@RequestBody Event newEvent, @PathVariable Long id) {

        return eventService.findById(newEvent.getId())
                .map(event -> {
                    event.setId(newEvent.getId());
                    event.setTextMessage(newEvent.getTextMessage());
                    return eventService.save(event);
                })
                .orElseGet(()-> {
                    newEvent.setId(id);
                    return eventService.save(newEvent);
                });
    }

}
