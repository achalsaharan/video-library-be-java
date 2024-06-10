package com.example.videolibrarybe.repository;

import com.example.videolibrarybe.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> getUserByEmailId(String emailId);
}
