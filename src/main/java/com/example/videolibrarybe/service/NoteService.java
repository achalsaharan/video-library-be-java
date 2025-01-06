package com.example.videolibrarybe.service;

import com.example.videolibrarybe.dto.NoteCreationRequestDTO;
import com.example.videolibrarybe.dto.NoteResponseDTO;
import com.example.videolibrarybe.model.Note;

import java.util.List;

public interface NoteService {
    NoteResponseDTO createNote(NoteCreationRequestDTO noteCreationRequestDTO);
    NoteResponseDTO getNote(String noteId);
    List<NoteResponseDTO> getNotesByVideo(String videoId);
}
