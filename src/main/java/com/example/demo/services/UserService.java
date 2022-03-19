package com.example.demo.services;

import com.example.demo.entity.UserEntity;
import com.example.demo.exceptions.UserNotFoundExceptions;
import com.example.demo.exceptions.userAlreadyExistException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserEntity registration(UserEntity user) throws userAlreadyExistException {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            throw new userAlreadyExistException("User with this name is already exist");
        }
        return userRepo.save(user);
    }

    public User getOne(Long id) throws UserNotFoundExceptions {
        UserEntity user = userRepo.findById(id).get();
        if (userRepo.findById(id).get() == null) {
            throw new UserNotFoundExceptions("User not found");
        }
        return User.toModel(user);
    }

    public List<UserEntity> getAll() {
        return userRepo.findAll();
    }

    public Long delete(Long id) {
        userRepo.deleteById(id);
        return id;
    }

}
