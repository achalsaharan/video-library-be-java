package com.example.videolibrarybe.controller;

import com.example.videolibrarybe.dto.DetailedVideoResponseDTO;
import com.example.videolibrarybe.dto.VideoResponseDTO;
import com.example.videolibrarybe.rest.RestResponseSuccessBody;
import com.example.videolibrarybe.service.VideoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/videos")
    public ResponseEntity<RestResponseSuccessBody<List<VideoResponseDTO>>> getVideos() {
        return new ResponseEntity<>(new RestResponseSuccessBody<>(videoService.getAllVideos()), HttpStatus.OK);
    }

    @GetMapping("/videos/{videoId}")
    public ResponseEntity<RestResponseSuccessBody<DetailedVideoResponseDTO>> getVideo(@PathVariable String videoId) {
        return new ResponseEntity<>(new RestResponseSuccessBody<>(videoService.getVideo(videoId)), HttpStatus.OK);
    }
}
