package com.example.videolibrarybe.service;

import com.example.videolibrarybe.dto.NoteCreationRequestDTO;
import com.example.videolibrarybe.dto.NoteResponseDTO;

public interface NoteService {
    NoteResponseDTO createNote(NoteCreationRequestDTO noteCreationRequestDTO);
}
