package com.example.videolibrarybe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@Table(name = "videos")
@AllArgsConstructor @NoArgsConstructor @Getter
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
