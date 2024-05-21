package com.example.videolibrarybe.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@Table(name = "videos")
public class Video {
    @Id
    @Column(name = "video_id")
    private String videoId;

    @Column(name = "name")
    private String name;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "views")
    private int views;

    @OneToMany(mappedBy = "video")
    private Set<Note> notes;

}
