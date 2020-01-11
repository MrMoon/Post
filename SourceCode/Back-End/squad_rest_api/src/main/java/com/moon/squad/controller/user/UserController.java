package com.moon.squad.controller.user;

import com.moon.squad.model.user.User;
import com.moon.squad.service.user.UserService;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;

import lombok.Data;

import static com.moon.squad.shared.ApplicationConstants.ADDED_SUCCESSFULLY;
import static com.moon.squad.shared.ApplicationConstants.DELETED_SUCCESSFULLY;
import static com.moon.squad.shared.ApplicationConstants.ID;
import static com.moon.squad.shared.ApplicationConstants.ID_MAPPING;
import static com.moon.squad.shared.ApplicationConstants.LOCALHOST_4200;
import static com.moon.squad.shared.ApplicationConstants.SLASH;
import static com.moon.squad.shared.ApplicationConstants.USER_MAPPING;

@Data
class Two {
    private String idUser;
    private String idFriend;
}


@RestController
@RequestMapping (USER_MAPPING)
@CrossOrigin (origins = LOCALHOST_4200)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping (value = "helloWorld")
    public String helloWorld() {
        return "Hello World From " + UserController.class.getSimpleName();
    }

    @CrossOrigin (origins = LOCALHOST_4200)
    @GetMapping (value = SLASH)
    public List<User> getAllUsers() {
        return userService.findAllByOrderByName();
    }

    @CrossOrigin (origins = LOCALHOST_4200)
    @GetMapping (value = ID_MAPPING)
    public @NotNull Optional<User> getUserById(@PathVariable (ID) String id) {
        return userService.findById(id);
    }

    @CrossOrigin (origins = LOCALHOST_4200)
    @GetMapping (value = "{id}/friends")
    public List<User> getUserFriends(@PathVariable (ID) @NotNull @NotBlank String id) {
        return userService.findAllFriends(id);
    }

    @CrossOrigin (origins = LOCALHOST_4200)
    @PostMapping (value = "/add")
    public ResponseEntity<?> addFriend(@NotNull @RequestBody Two two) {
        userService.addFriend(two.getIdUser(), two.getIdFriend());
        return new ResponseEntity<>(two.toString() + '\n' + ' ' + two.getClass().getSimpleName() + ' ' + ADDED_SUCCESSFULLY, HttpStatus.OK);
    }

    @GetMapping(value = {"email"})
    public String getUserId(@PathVariable("email") String email){
        return userService.findUserIdByEmail(email);
    }

    @PostMapping (value = SLASH)
    public ResponseEntity<?> saveOrUpdateUser(@RequestBody User user) {
        userService.saveOrUpdate(user);
        return new ResponseEntity<>(user.toString() + '\n' + ' ' + user.getClass().getSimpleName() + ' ' + ADDED_SUCCESSFULLY, HttpStatus.OK);
    }

    @DeleteMapping (value = ID_MAPPING)
    public ResponseEntity<?> deleteUserById(@PathVariable String id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(id + '\n' + ' ' + User.class.getSimpleName()
                + DELETED_SUCCESSFULLY, HttpStatus.OK);
    }
}
