package com.example.videolibrarybe.service.Implementation;

import com.example.videolibrarybe.dto.UserDTO;
import com.example.videolibrarybe.mapper.SimpleMapper;
import com.example.videolibrarybe.model.User;
import com.example.videolibrarybe.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class UserServiceImpl implements com.example.videolibrarybe.service.UserService {

    private final UserRepository userRepository;

    private final SimpleMapper simpleMapper;

    public UserServiceImpl(UserRepository userRepository, SimpleMapper simpleMapper) {
        this.userRepository = userRepository;
        this.simpleMapper = simpleMapper;
    }

//    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User createUser() {
        return null;
    }

    // todo is there any advantages to returning Iterable<UserDTO>?
    @Override
    public List<UserDTO> getAllUsers() {
        List<User> userEntities = (List<User>) userRepository.findAll();
        log.info(userEntities.toString());
        List<UserDTO> userDTOS = userEntities
                .stream()
                .map(simpleMapper::userEntityToDTO)
                .toList();

        return userDTOS;
    }
}
