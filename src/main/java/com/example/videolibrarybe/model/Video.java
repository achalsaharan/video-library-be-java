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

    /**
     * Defines the inverse side of the many-to-many relationship with the PlayList entity.
     * This side of the relationship is not responsible for managing the join table
     * and uses the `mappedBy` attribute to indicate that the `videos` field in the PlayList
     * entity manages the relationship.
     * <p>
     * The owning side (PlayList) specifies the join table (`play_list_video`) and the join columns.
     * By delegating the join table management to the owning side, we avoid redundancy
     * and ensure consistency in the relationship mapping.
     */
    @ManyToMany(mappedBy = "videos")
    private List<PlayList> playLists;

}
