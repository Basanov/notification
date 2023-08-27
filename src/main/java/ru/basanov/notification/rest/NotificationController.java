package ru.basanov.notification.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.basanov.notification.model.CalendarMessage;
import ru.basanov.notification.model.Event;
import ru.basanov.notification.model.NotificationPeriod;
import ru.basanov.notification.model.User;
import ru.basanov.notification.service.CalendarMessageService;
import ru.basanov.notification.service.EventService;
import ru.basanov.notification.service.UserService;

import java.lang.reflect.Type;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@RestController
public class NotificationController {

    private final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    private final EventService eventService;

    private final UserService userService;

    private final CalendarMessageService calendarMessageService;

    public NotificationController(EventService eventService, UserService userService, CalendarMessageService calendarMessageService) {
        this.eventService = eventService;
        this.userService = userService;
        this.calendarMessageService = calendarMessageService;
    }

    @PostMapping("/notification/send")
    public void sendNotification(@RequestBody long eventId) {

        /**
         * Сравниваем дату возникновения события с периодом оповещения пользователя
         * Если дата возникновения события попадает в период оповещения пользователя, то записываем событие в лог
         * Если дата возникновения события раньше даты начала оповещения пользователя, то сохраняем дату возникновения
         * события датой началой оповещения пользователя в БД
         */
        Optional<Event> eventOptional = eventService.findById(eventId);
        if (eventOptional.get() instanceof Event) {
            Event event = eventOptional.get();
            Set<User> users = event.getUsers();
            Iterator<User> iterator = users.iterator();
            while (iterator.hasNext()) {
                User user = iterator.next();
                List<NotificationPeriod> notificationPeriods = user.getNotificationPeriods();
                Iterator<NotificationPeriod> periodIterator = notificationPeriods.listIterator();
                DayOfWeek dayOfWeek = event.getOriginDate().getDayOfWeek();
                LocalTime localTime = event.getOriginDate().toLocalTime();

                while (periodIterator.hasNext()) {
                    var notificationPeriod = periodIterator.next();
                    var periodWeekDay = notificationPeriod.getDayOfWeek();
                    if (periodWeekDay == dayOfWeek) {
                        if (notificationPeriod.getStartTime().isAfter(localTime) && notificationPeriod.getEndTime().isBefore(localTime)) {
                            var CalendarMessage = new CalendarMessage(event.getOriginDate());
                            calendarMessageService.save(CalendarMessage);
                        } else {
                            var log = new StringBuilder();
                            log.append(event.getOriginDate());
                            log.append(user.toString());
                            log.append(event.getTextMessage());
                            logger.info(String.valueOf(log));
                        }
                    }
                }
            }
        }
    }
}
