package com.example.videolibrarybe.service;

import com.example.videolibrarybe.dto.DetailedVideoResponseDTO;
import com.example.videolibrarybe.dto.VideoResponseDTO;

import java.util.List;

public interface VideoService {
    List<VideoResponseDTO> getAllVideos();
    DetailedVideoResponseDTO getVideo(String videoId);
}
