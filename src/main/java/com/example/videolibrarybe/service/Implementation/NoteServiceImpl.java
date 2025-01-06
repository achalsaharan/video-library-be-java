package com.example.videolibrarybe.service.Implementation;

import com.example.videolibrarybe.dto.NoteCreationRequestDTO;
import com.example.videolibrarybe.dto.NoteResponseDTO;
import com.example.videolibrarybe.mapper.SimpleMapper;
import com.example.videolibrarybe.model.Note;
import com.example.videolibrarybe.model.User;
import com.example.videolibrarybe.model.Video;
import com.example.videolibrarybe.repository.NoteRepository;
import com.example.videolibrarybe.repository.VideoRepository;
import com.example.videolibrarybe.service.NoteService;
import com.example.videolibrarybe.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final VideoRepository videoRepository;
    private final SimpleMapper simpleMapper;


    public NoteServiceImpl(NoteRepository noteRepository, VideoRepository videoRepository, SimpleMapper simpleMapper) {
        this.noteRepository = noteRepository;
        this.videoRepository = videoRepository;
        this.simpleMapper = simpleMapper;
    }

    @Override
    public NoteResponseDTO createNote(NoteCreationRequestDTO noteCreationRequestDTO) {
        User user = SecurityUtils.getUserInfo();
        log.info("user details: {}", user);

        Video video = videoRepository.findById(noteCreationRequestDTO.videoId())
                .orElseThrow(() -> new IllegalArgumentException("videoId does not exist"));

        Note note = simpleMapper.noteCreationRequestDTOToNoteEntity(noteCreationRequestDTO);
        note.setVideo(video);
        note.setUser(user);

        Note savedNote = noteRepository.save(note);
        log.info("save note: {}", savedNote);

        return simpleMapper.noteEntityToNoteResponseDTO(savedNote);
    }
}
