package com.moon.squad.controller.auth;

import com.moon.squad.configuration.security.jwt.JwtUtil;
import com.moon.squad.model.user.User;
import com.moon.squad.repository.user.UserRepository;
import com.moon.squad.service.user.implementation.UserServiceImp;
import com.moon.squad.shared.LoggingConstants;
import com.moon.squad.shared.exception.RoleException;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.moon.squad.shared.ApplicationConstants.ADDED_SUCCESSFULLY;
import static com.moon.squad.shared.ApplicationConstants.AUTH_MAPPING;
import static com.moon.squad.shared.ApplicationConstants.LOCALHOST_4200;
import static com.moon.squad.shared.ApplicationConstants.EMAIL;
import static com.moon.squad.shared.ApplicationConstants.MESSAGE;
import static com.moon.squad.shared.ApplicationConstants.SIGN_IN;
import static com.moon.squad.shared.ApplicationConstants.SIGN_UP;
import static com.moon.squad.shared.ApplicationConstants.TOKEN;
import static com.moon.squad.shared.ApplicationConstants.alreadyExist;

@RestController
@RequestMapping (AUTH_MAPPING)
@CrossOrigin (origins = LOCALHOST_4200)
public class AuthController {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserServiceImp userServiceImp;

    public AuthController(UserRepository userRepository, AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserServiceImp userServiceImp) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userServiceImp = userServiceImp;
    }

    @PostMapping (SIGN_IN)
    public ResponseEntity<?> login(@NotNull @RequestBody com.moon.squad.model.user.User user) {
        try {
            String email = user.getEmail();
            Optional<User> u = userRepository.findByEmail(email);
            if(u.isPresent()){
                String id = u.get().getId();
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, user.getPassword()));
                String token = jwtUtil.createToken(email , id , userRepository.findByEmail(email).get().getRoles());
                Map<Object, Object> map = new HashMap<>();
                map.put(EMAIL, email);
                map.put(TOKEN, token);
                return ResponseEntity.ok(map);
            }
            throw new BadCredentialsException("Invalid email/password supplied");
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email/password supplied");
        }
    }

    @PostMapping (SIGN_UP)
    public ResponseEntity<?> register(@NotNull @RequestBody com.moon.squad.model.user.User user) throws RoleException {
        Optional<com.moon.squad.model.user.User> isExisting = userRepository.findByEmail(user.getEmail());
        if (isExisting.isPresent())
            throw new BadCredentialsException(alreadyExist(isExisting.get().getEmail()));
        userServiceImp.saveOrUpdate(user);
        Map<Object, Object> map = new HashMap<>();
        map.put(MESSAGE, LoggingConstants.saving(user.toString()) + ' ' + ADDED_SUCCESSFULLY);
        return ResponseEntity.ok(map);
    }
}
