package com.example.User.Controller;

import com.example.User.Model.User;
import com.example.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin( origins = "http://localhost:4200")
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createUser(@RequestBody User User_id) {
        boolean createdUser = userService.saveUser(User_id);
        Map<String, String> response = new HashMap<>();
        if (createdUser) {
            response.put("status", "succeed");
            response.put("message", "User created successfully.");
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "unsuccessful");
            response.put("message", "Failed to create user.");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/getCount")
    public ResponseEntity<Object> getCount() {
        long users = userService.getCount();
        return new ResponseEntity<>(users ,HttpStatus.OK);
    }

    @GetMapping("/getCount/{firstName}&{lastName}&{email}")
    public ResponseEntity<Object> countByFirstNameOrLastNameOrEmail(@PathVariable("firstName") String firstName,
                                                                    @PathVariable("lastName") String lastName,
                                                                    @PathVariable("email") String email) {
        long users = userService.countByFirstNameOrLastNameOrEmail(firstName, lastName, email);
        return new ResponseEntity<>(users ,HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUsersById(@PathVariable("id") Long User_id) {
        User user = userService.getUsersById(User_id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usersEmail/{email}")
    public ResponseEntity<Object> getUsersEmail(@PathVariable("email") String email) {
        List<User> users = userService.getUsersEmail(email);
        if (!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("unsuccessful",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usersFirstName/{firstName}")
    public ResponseEntity<List<User>> findByFirstNameContaining(@PathVariable("firstName") String firstName) {
        List<User> users = userService.findByFirstNameContaining(firstName);
        if (!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editUser(@PathVariable Long id, @RequestBody User editedUser) {
    boolean edited = userService.editUser(id, editedUser);
    if (edited) {
        return new ResponseEntity<>("succeed", HttpStatus.OK);
    } else {
        return new ResponseEntity<>("unsuccessful", HttpStatus.NOT_FOUND);
    }
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Long id) {
        boolean delete = userService.deleteUser(id);
        if (delete) {
            return new ResponseEntity<>("succeed", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("unsuccessful", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usersFirstnameAndLastname/{firstName}&{lastName}")
    public ResponseEntity<List<User>> findByFirstNameAndLastName(@PathVariable("firstName") String firstName,
                                                                 @PathVariable("lastName") String lastName) {
        List<User> users = userService.findByFirstNameAndLastName(firstName , lastName);
        if (!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/usersFirstnameAndLastnameAndEmail/{firstName}&{lastName}&{email}")
    public ResponseEntity<List<User>> findByFirstNameAndLastNameAndEmail(@PathVariable("firstName") String firstName,
                                                                         @PathVariable("lastName") String lastName,
                                                                         @PathVariable("email") String email) {
        List<User> users = userService.findByFirstNameAndLastNameAndEmail(firstName , lastName, email);
        if (!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usersFirstNameOrLastName/{firstName}&{lastName}")
    public ResponseEntity<List<User>> findByFirstNameOrLastName(@PathVariable("firstName") String firstName,
                                                                @PathVariable("lastName") String lastName) {
        List<User> users = userService.findByFirstNameOrLastName(firstName , lastName);
        if (!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/usersFirstNameOrLastNameOrEmail/{firstName}&{lastName}&{email}")
    public ResponseEntity<List<User>> findByFirstNameOrLastNameOrEmail(@PathVariable("firstName") String firstName,
                                                                       @PathVariable("lastName") String lastName,
                                                                       @PathVariable("email") String email) {
        List<User> users = userService.findByFirstNameOrLastNameOrEmail(firstName , lastName, email);
        if (!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usersEmailxxx/{firstName}&{lastName}&{email}")
    public ResponseEntity<Object> getUsersInfoWithUseridLessThan(@PathVariable("firstName") String firstName,
                                                                 @PathVariable("lastName") String lastName,
                                                                 @PathVariable("email") String email) {
        List<User> users = userService.findByFirstNameOrLastNameOrEmail(firstName, lastName, email);
        long count = userService.countByFirstNameOrLastNameOrEmail(firstName, lastName, email);
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("count", count);
        responseData.put("users", users);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/usersFirstNameIs/{firstName}")
    public ResponseEntity<List<User>> findByFirstNameIs(@PathVariable("firstName") String firstName) {
        List<User> users = userService.findByFirstNameIs(firstName);
        if (!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usersFirstNameEquals/{firstName}")
    public ResponseEntity<List<User>> findByFirstNameEquals(@PathVariable("firstName") String firstName) {
        List<User> users = userService.findByFirstNameEquals(firstName);
        if (!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usersBirthdayBetween/{startDate}/{endDate}")
    public ResponseEntity<List<User>> findByBirthdayBetween(@PathVariable("startDate") LocalDate startDate,
                                                            @PathVariable("endDate") LocalDate endDate) {
        List<User> users = userService.findByBirthdayBetween(startDate, endDate);
        if (!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usersUserIdLessThan/{userid}")
    public ResponseEntity<List<User>> findByUseridLessThan(@PathVariable("userid") Long userid) {
        List<User> users = userService.findByUseridLessThan(userid);
        if (!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usersBirthdayAfter/{date}")
    public ResponseEntity<List<User>> findByBirthdayAfter(@PathVariable("date") LocalDate date) {
        List<User> users = userService.findByBirthdayAfter(date);
        if (!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usersBirthdayBefore/{date}")
    public ResponseEntity<List<User>> findByBirthdayBefore(@PathVariable("date") LocalDate date) {
        List<User> users = userService.findByBirthdayBefore(date);
        if (!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usersFirstNameNotLike/{firstName}")
    public ResponseEntity<List<User>> findByFirstNameNotLike(@PathVariable("firstName") String firstName) {
        List<User> users = userService.findByFirstNameNotLike(firstName);
        if (!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usersFirstNameIn")
    public ResponseEntity<List<User>> findByFirstNameIn(@RequestParam("firstName") List<String> firstNames) {
        List<User> users = userService.findByFirstNameIn(firstNames);
        if (!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

