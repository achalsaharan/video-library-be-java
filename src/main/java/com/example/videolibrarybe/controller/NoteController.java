package com.example.videolibrarybe.controller;

import com.example.videolibrarybe.dto.NoteCreationRequestDTO;
import com.example.videolibrarybe.dto.NoteResponseDTO;
import com.example.videolibrarybe.service.NoteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
