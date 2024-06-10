package com.example.videolibrarybe.mapper;

import com.example.videolibrarybe.dto.AuthenticationUserDTO;
import com.example.videolibrarybe.dto.UserCreationRequestDTO;
import com.example.videolibrarybe.dto.UserDTO;
import com.example.videolibrarybe.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SimpleMapper {

    @Mapping(source = "firstName", target = "firstName")
    UserDTO userEntityToDTO (User user);

    @Mapping(source = "firstName", target = "firstName")
    User userCreationRequestDTOToUserEntity(UserCreationRequestDTO userCreationRequestDTO);

    @Mapping(source = "firstName", target = "firstName")
    AuthenticationUserDTO userEntityToAuthenticationUserDTO(User user);
}
