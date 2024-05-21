package com.example.videolibrarybe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "notes")
public class Note {
    @Id
    @Column(name = "note_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int noteId;

    @Column(name = "heading")
    private String heading;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private Video video;
}
