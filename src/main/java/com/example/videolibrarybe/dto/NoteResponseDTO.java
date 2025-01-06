package com.example.videolibrarybe.dto;

public record NoteResponseDTO(
        int noteId,
        String heading,
        String content,
        String userId,
        String videoId
) {
}
