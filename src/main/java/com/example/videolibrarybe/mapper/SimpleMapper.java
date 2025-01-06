package com.example.videolibrarybe.mapper;

import com.example.videolibrarybe.dto.*;
import com.example.videolibrarybe.model.Note;
import com.example.videolibrarybe.model.User;
import com.example.videolibrarybe.model.Video;
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

    VideoResponseDTO videoEntityToVideoResponseDTO (Video video);

    DetailedVideoResponseDTO videoEntityToDetailedVideoResponseDTO (Video video);

    @Mapping(source = "heading", target = "heading")
    Note noteCreationRequestDTOToNoteEntity (NoteCreationRequestDTO noteCreationRequestDTO);

    @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "video.videoId", target = "videoId")
    NoteResponseDTO noteEntityToNoteResponseDTO (Note note);
}
