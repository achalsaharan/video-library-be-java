package com.example.videolibrarybe.mapper;

import com.example.videolibrarybe.dto.UserDTO;
import com.example.videolibrarybe.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SimpleMapper {
    UserDTO userEntityToDTO (User user);
}
