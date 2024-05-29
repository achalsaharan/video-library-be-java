package com.example.videolibrarybe.mapper;

import com.example.videolibrarybe.dto.UserDTO;
import com.example.videolibrarybe.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SimpleMapper {

    @Mapping(source = "userId", target = "userId")
    UserDTO userEntityToDTO (User user);
}
