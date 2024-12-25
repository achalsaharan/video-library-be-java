package com.example.videolibrarybe.repository;

import com.example.videolibrarybe.model.Note;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends CrudRepository<Note, Integer> {
}
