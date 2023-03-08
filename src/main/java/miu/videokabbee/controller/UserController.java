package miu.videokabbee.controller;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import miu.videokabbee.ExceptionHandling.ExceptionHandling;
import miu.videokabbee.domain.Users;
import miu.videokabbee.service.UserServiceImpl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Validated
public class UserController {

   private final UserServiceImpl userInterfaceService;
   private final PasswordEncoder passwordEncoder;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserByID(@PathVariable("id") Long id ){
        var user=userInterfaceService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(new ExceptionHandling("not available"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>( user, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Users users){
        String encodedPassword=passwordEncoder.encode(users.getPassword());
        users.setPassword(encodedPassword);
     var userRegistered =   userInterfaceService.register(users);

        if (userRegistered.equals("Username-taken") ||userRegistered.equals("Email-taken")  ) {
            return new ResponseEntity<>(new ExceptionHandling(userRegistered), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userRegistered,HttpStatus.OK);
    }

    @GetMapping("/logIn")
    public String login(){
        return "LoggedIn";
    }

}
