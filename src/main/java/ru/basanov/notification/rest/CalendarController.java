package ru.basanov.notification.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.basanov.notification.service.CalendarMessageService;

@RestController
public class CalendarController {

    private CalendarMessageService calendarMessageService;

    public CalendarController(CalendarMessageService calendarMessageService) {
        this.calendarMessageService = calendarMessageService;
    }


    public void sendMessage() {

    }


}
