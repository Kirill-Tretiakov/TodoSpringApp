package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.exceptions.UserNotFoundExceptions;
import com.example.demo.exceptions.userAlreadyExistException;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService userService;


    @PostMapping("/save")
    public ResponseEntity postUsers(@RequestBody UserEntity user) {
        try {
            userService.registration(user);
            return ResponseEntity.ok("User successfully added");
        } catch (userAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Server throw an unknown error");
        }
    }

    @GetMapping("/all")
    public List<UserEntity> getUsers() {
        return userService.getAll();
    }

    @GetMapping()
    public ResponseEntity getUser(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(userService.getOne(id));
        } catch (UserNotFoundExceptions e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Server throw an unknown error");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Server throw an unknown error");
        }
    }

}

