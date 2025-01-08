package com.example.videolibrarybe.controller;

import com.example.videolibrarybe.dto.NoteCreationRequestDTO;
import com.example.videolibrarybe.dto.NoteResponseDTO;
import com.example.videolibrarybe.rest.RestResponseSuccessBody;
import com.example.videolibrarybe.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/notes")
    public ResponseEntity<RestResponseSuccessBody<NoteResponseDTO>> createNote(@RequestBody NoteCreationRequestDTO noteCreationRequestDTO) {
        return ResponseEntity.ok(new RestResponseSuccessBody<>(noteService.createNote(noteCreationRequestDTO)));

    }

    @GetMapping("/notes/{noteId}")
    public ResponseEntity<RestResponseSuccessBody<NoteResponseDTO>> getNote(@PathVariable String noteId) {
        return ResponseEntity.ok(new RestResponseSuccessBody<>(noteService.getNote(noteId)));
    }

    // Get notes for a video id for the logged-in user
    @GetMapping("/notes")
    public ResponseEntity<RestResponseSuccessBody<List<NoteResponseDTO>>> getNotesByVideo(@RequestParam String videoId) {
        return ResponseEntity.ok(new RestResponseSuccessBody<>(noteService.getNotesByVideo(videoId)));
    }

}
