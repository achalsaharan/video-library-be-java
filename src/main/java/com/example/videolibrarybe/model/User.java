package com.example.videolibrarybe.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private Set<PlayList> playLists;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private Set<Note> notes;

    @ToString.Include
    public String playListIds() {
        StringBuilder playListIds = new StringBuilder();
        for (PlayList playList : playLists) {
            playListIds.append(playList.getPlayListId()).append(",");
        }

        return playListIds.toString();
    }
}
