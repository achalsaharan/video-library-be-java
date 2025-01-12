package com.example.videolibrarybe.service;

import com.example.videolibrarybe.dto.NoteCreationRequestDTO;
import com.example.videolibrarybe.dto.NoteResponseDTO;
import com.example.videolibrarybe.mapper.SimpleMapper;
import com.example.videolibrarybe.mapper.SimpleMapperImpl;
import com.example.videolibrarybe.model.Note;
import com.example.videolibrarybe.model.User;
import com.example.videolibrarybe.model.Video;
import com.example.videolibrarybe.repository.NoteRepository;
import com.example.videolibrarybe.repository.VideoRepository;
import com.example.videolibrarybe.service.Implementation.NoteServiceImpl;
import com.example.videolibrarybe.utils.SecurityUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class NoteServiceImplTest {

    @Mock
    private NoteRepository noteRepository;

    @Mock
    private VideoRepository videoRepository;

    private MockedStatic<SecurityUtils> securityUtilsMockedStatic;

    private final SimpleMapper simpleMapper = new SimpleMapperImpl();

    @InjectMocks
    private NoteServiceImpl noteService;

    @BeforeEach
    void setup() {
        noteService = new NoteServiceImpl(noteRepository, videoRepository, simpleMapper);
    }

    @BeforeEach
    void staticMockSetup() {
        User mockUser = new User();
        mockUser.setUserId(123);
        securityUtilsMockedStatic = Mockito.mockStatic(SecurityUtils.class);
        Mockito.when(SecurityUtils.getUserInfo()).thenReturn(mockUser);
    }

    @AfterEach
    void teardown() {
        securityUtilsMockedStatic.close();
    }

    @Test
    void createNote_shouldCreateNoteSuccessfully() {
        // Arrange
        NoteCreationRequestDTO requestDTO = new NoteCreationRequestDTO("Note Heading", "Note content", "test_video_id");

        User mockUser = new User();
        mockUser.setUserId(123);

        Video mockVideo = new Video();
        mockVideo.setVideoId("test_video_id");

        Note savedNote = new Note();
        savedNote.setHeading("Note Heading");
        savedNote.setContent("Note content");
        savedNote.setVideo(mockVideo);
        savedNote.setUser(mockUser);
        savedNote.setNoteId(123);

        Mockito.when(SecurityUtils.getUserInfo()).thenReturn(mockUser);
        Mockito.when(videoRepository.findById(requestDTO.videoId())).thenReturn(Optional.of(mockVideo));
        Mockito.when(noteRepository.save(Mockito.any(Note.class))).thenReturn(savedNote);

        // Act
        NoteResponseDTO result = noteService.createNote(requestDTO);

        // Assert
        assertNotNull(result);
        assertEquals(123, result.noteId());
        assertEquals("Note content", result.content());

    }

    @Test
    void createNode_videoIdDoesNotExists() {
        // Arrange
        NoteCreationRequestDTO requestDTO = new NoteCreationRequestDTO("Note Heading", "Note content", "test_video_id");

        User mockUser = new User();
        mockUser.setUserId(123);

        Video mockVideo = new Video();
        mockVideo.setVideoId("test_video_id");

        Mockito.when(SecurityUtils.getUserInfo()).thenReturn(mockUser);
        Mockito.when(videoRepository.findById(requestDTO.videoId())).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            noteService.createNote(requestDTO);
        });

        // Assert the exception message
        assertEquals("videoId does not exist", exception.getMessage());
    }

}

