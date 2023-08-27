package ru.basanov.notification.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.basanov.notification.model.Event;

import java.util.Optional;

@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

    @Query("select e FROM Event e WHERE e.id =: id")
    Optional<Event> findById(@Param("id") Long id);

    @Query("select e FROM Event e WHERE e.textMessage =: textMessage")
    public Page<Event> findEventByTextMessage(@Param("textMessage)") String textMessage, Pageable pageable);

    public Event save(Event event);

    void deleteEventById(Long id);

    Iterable<Event> findAll();
}
