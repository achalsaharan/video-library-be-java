package com.example.videolibrarybe.dto;

public record NoteCreationRequestDTO(
        String heading,
        String content,
        String videoId
) {}
