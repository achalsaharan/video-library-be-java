package com.example.videolibrarybe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "play_lists")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class PlayList {
    @Id
    @Column(name = "play_list_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int playListId;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "play_list_video",
            joinColumns = @JoinColumn(name = "play_list_id"),
            inverseJoinColumns = @JoinColumn(name = "video_id"))
    private Set<Video> videos;

    private String visibility;
}
