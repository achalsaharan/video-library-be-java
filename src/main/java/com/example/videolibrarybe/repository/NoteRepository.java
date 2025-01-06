package com.example.videolibrarybe.repository;

import com.example.videolibrarybe.model.Note;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoteRepository extends CrudRepository<Note, Integer> {
    List<Note> findAllByUser_UserIdAndVideo_VideoId(Integer user_id, String video_id);
}
