package com.example.videolibrarybe.service.Implementation;

import com.example.videolibrarybe.dto.VideoResponseDTO;
import com.example.videolibrarybe.mapper.SimpleMapper;
import com.example.videolibrarybe.model.Video;
import com.example.videolibrarybe.repository.VideoRepository;
import com.example.videolibrarybe.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;
    private final SimpleMapper simpleMapper;


    public VideoServiceImpl(VideoRepository videoRepository, SimpleMapper simpleMapper) {
        this.videoRepository = videoRepository;
        this.simpleMapper = simpleMapper;
    }

    @Override
    public List<VideoResponseDTO> getAllVideos() {
        List<Video> videoEntities = (List<Video>) videoRepository.findAll();
        log.info("videos: {}", videoEntities);

        return videoEntities
                .stream()
                .map(simpleMapper::videoEntityToVideoResponseDTO)
                .toList();
    }
}
