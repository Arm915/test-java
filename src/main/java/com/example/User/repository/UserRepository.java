package com.example.User.repository;

import com.example.User.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByFirstNameContaining(String firstName);

    List<User> findOneByEmail(String email);

    List<User> findByFirstNameAndLastName(String firstName, String lastName);
    List<User> findByFirstNameAndLastNameAndEmail(String firstName, String lastName, String email);

    List<User> findByFirstNameOrLastName(String firstName, String lastName);
    List<User> findByFirstNameOrLastNameOrEmail(String firstName, String lastName, String email);

    List<User> findByFirstNameIs(String firstName);

    List<User> findByFirstNameEquals(String firstName);

    List<User> findByBirthdayBetween(LocalDate startDate, LocalDate endDate);

    List<User> findByBirthdayAfter(LocalDate date);

    List<User> findByBirthdayBefore(LocalDate date);

    List<User> findByFirstNameNotLike(String firstName);

    List<User> findByFirstNameIn(List<String> firstName);

    List<User> findByUseridLessThan(Long userid);

    long countByFirstNameOrLastNameOrEmail(String firstName, String lastName, String email);

}
