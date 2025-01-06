package com.example.videolibrarybe.service.Implementation;

import com.example.videolibrarybe.dto.DetailedVideoResponseDTO;
import com.example.videolibrarybe.dto.VideoResponseDTO;
import com.example.videolibrarybe.mapper.SimpleMapper;
import com.example.videolibrarybe.model.Video;
import com.example.videolibrarybe.repository.VideoRepository;
import com.example.videolibrarybe.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public DetailedVideoResponseDTO getVideo(String videoId) {
        Optional<Video> videoOptional = videoRepository.findById(videoId);

        if(videoOptional.isPresent()) {
            Video video = videoOptional.get();
            return simpleMapper.videoEntityToDetailedVideoResponseDTO(video);
        } else {
            // todo is this the correct type of exception to be thrown
            throw new RuntimeException("video id does not exist");
        }
    }

}
