package com.example.videolibrarybe.repository;

import com.example.videolibrarybe.model.Video;
import org.springframework.data.repository.CrudRepository;

public interface VideoRepository extends CrudRepository<Video, String> {
}
