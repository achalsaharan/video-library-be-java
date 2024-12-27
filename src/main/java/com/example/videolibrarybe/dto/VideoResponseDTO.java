package com.example.videolibrarybe.dto;

public record VideoResponseDTO(
        String videoId,
        String name,
        String thumbnailUrl,
        String views
) {}
