package com.example.videolibrarybe.repository;

import com.example.videolibrarybe.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {}
