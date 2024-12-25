package com.example.videolibrarybe.service.Implementation;

import com.example.videolibrarybe.dto.AuthenticationUserDTO;
import com.example.videolibrarybe.dto.UserCreationRequestDTO;
import com.example.videolibrarybe.dto.UserDTO;
import com.example.videolibrarybe.mapper.SimpleMapper;
import com.example.videolibrarybe.model.User;
import com.example.videolibrarybe.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class UserServiceImpl implements com.example.videolibrarybe.service.UserService {

    private final UserRepository userRepository;

    private final SimpleMapper simpleMapper;

    // should the password encoder bean created in security config be used? causes circular dependency
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository userRepository, SimpleMapper simpleMapper) {
        this.userRepository = userRepository;
        this.simpleMapper = simpleMapper;
    }

    @Override
    public UserDTO createUser(UserCreationRequestDTO userCreationRequestDTO) {
        User user = simpleMapper.userCreationRequestDTOToUserEntity(userCreationRequestDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return simpleMapper.userEntityToDTO(user);
    }

    @Override
    public UserDTO getUser(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            //todo exception handling
            throw new RuntimeException("user not found");
        }
        return simpleMapper.userEntityToDTO(user.get());
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> userEntities = (List<User>) userRepository.findAll();
        log.info(userEntities.toString());

        return userEntities
                .stream()
                .map(simpleMapper::userEntityToDTO)
                .toList();
    }

    @Override
    public AuthenticationUserDTO getUserByEmailId(String emailId){
        Optional<User> user = userRepository.getUserByEmailId(emailId);
        if(user.isEmpty()) {
            throw new RuntimeException("user not found");
        }

        return simpleMapper.userEntityToAuthenticationUserDTO(user.get());
    }
}
