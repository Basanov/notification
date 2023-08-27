package ru.basanov.notification.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.basanov.notification.model.User;

import java.util.Optional;


@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    @Query("select u FROM User u WHERE u.id =: id")
    Optional<User> findById(@Param("id") Long id);

    @Query("select u FROM User u WHERE u.id =: id")
    public Page<User> findUserByFIO(@Param("FIO") String fio, Pageable pageable);

    public User save(User user);

    void deleteUserById(Long id);

    Iterable<User> findAll();
}
