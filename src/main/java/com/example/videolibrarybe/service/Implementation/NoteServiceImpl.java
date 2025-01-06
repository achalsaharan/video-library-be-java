package com.example.videolibrarybe.service.Implementation;

import com.example.videolibrarybe.dto.NoteCreationRequestDTO;
import com.example.videolibrarybe.dto.NoteResponseDTO;
import com.example.videolibrarybe.mapper.SimpleMapper;
import com.example.videolibrarybe.model.Note;
import com.example.videolibrarybe.model.User;
import com.example.videolibrarybe.model.Video;
import com.example.videolibrarybe.repository.NoteRepository;
import com.example.videolibrarybe.repository.UserRepository;
import com.example.videolibrarybe.repository.VideoRepository;
import com.example.videolibrarybe.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final VideoRepository videoRepository;
    private final UserRepository userRepository;
    private final SimpleMapper simpleMapper;


    public NoteServiceImpl(NoteRepository noteRepository, VideoRepository videoRepository, UserRepository userRepository, SimpleMapper simpleMapper) {
        this.noteRepository = noteRepository;
        this.videoRepository = videoRepository;
        this.userRepository = userRepository;
        this.simpleMapper = simpleMapper;
    }

    @Override
    public NoteResponseDTO createNote(NoteCreationRequestDTO noteCreationRequestDTO) {
        Video video = videoRepository.findById(noteCreationRequestDTO.videoId())
                .orElseThrow(() -> new IllegalArgumentException("videoId does not exist"));

        User user = userRepository.findById(Integer.parseInt(noteCreationRequestDTO.userId()))
                .orElseThrow(() -> new IllegalArgumentException("userId does not exist"));

        Note note = simpleMapper.noteCreationRequestDTOToNoteEntity(noteCreationRequestDTO);
        note.setVideo(video);
        note.setUser(user);

        Note savedNote = noteRepository.save(note);
        log.info("save note: {}", savedNote);

        return simpleMapper.noteEntityToNoteResponseDTO(savedNote);
    }
}
