package com.example.videolibrarybe.controller;

import com.example.videolibrarybe.dto.NoteCreationRequestDTO;
import com.example.videolibrarybe.dto.NoteResponseDTO;
import com.example.videolibrarybe.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/notes")
    public NoteResponseDTO createNote(@RequestBody NoteCreationRequestDTO noteCreationRequestDTO) {
        return noteService.createNote(noteCreationRequestDTO);
    }

    @GetMapping("/notes/{noteId}")
    public NoteResponseDTO getNote(@PathVariable String noteId) {
        return noteService.getNote(noteId);
    }

    @GetMapping("/notes")
    public List<NoteResponseDTO> getNotesByVideo(@RequestParam String videoId) {
        return noteService.getNotesByVideo(videoId);
    }

}
