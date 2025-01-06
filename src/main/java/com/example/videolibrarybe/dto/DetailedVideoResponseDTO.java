package com.example.videolibrarybe.dto;

import com.example.videolibrarybe.model.Note;

import java.util.List;

public record DetailedVideoResponseDTO(
        String videoId,
        String name,
        String thumbnailUrl,
        String views,
        List<Note> notes
) {}
