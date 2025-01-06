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

    /**
     * In a one-to-many relationship, the "many" side typically maps and manages the relationship.
     * This is because:
     * <p>
     * 1. **Foreign Key Ownership**: The "many" side contains the foreign key that links it
     *    to the "one" side. For example, in a `User` ↔ `Videos` relationship, the `videos` table
     *    (the "many" side) will have a `user_id` foreign key referencing the `users` table.
     * <p>
     * 2. **Efficiency**: Since the foreign key is stored on the "many" side, managing the
     *    relationship from there simplifies querying and updates.
     * <p>
     * 3. **JPA Configuration**:
     *    - The "many" side uses the `@ManyToOne` annotation with `@JoinColumn` to define the relationship
     *      and specify the foreign key column.
     *    - The "one" side uses `@OneToMany` with the `mappedBy` attribute to indicate that the relationship
     *      is mapped by the "many" side.
     * <p>
     * Example:
     * - The "many" side (`Video`):
     *   {@code
     *   @ManyToOne
     *   @JoinColumn(name = "user_id") // Foreign key in the 'videos' table
     *   private User user;
     *   }
     *
     * - The "one" side (`User`):
     *   {@code
     *   @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
     *   private List<Video> videos;
     *   }
     *
     * This configuration ensures that the "many" side owns the relationship and the "one" side
     * serves as the inverse side, maintaining clarity and consistency in the mapping.
     */

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Configures the many-to-many relationship between the PlayList and Video entities.
     * This side of the relationship is the owning side, and it specifies the join table
     * `play_list_video` that connects the two entities.
     * <p>
     * The `@JoinTable` annotation is used to define:
     * - The name of the join table (`play_list_video`).
     * - `joinColumns`: The foreign key column (`play_list_id`) that references the PlayList entity.
     * - `inverseJoinColumns`: The foreign key column (`video_id`) that references the Video entity.
     * <p>
     * This setup allows a playlist to contain multiple videos and a video to belong to multiple playlists,
     * implementing a many-to-many relationship.
     */
    @ManyToMany
    @JoinTable(
            name = "play_list_video",
            joinColumns = @JoinColumn(name = "play_list_id"),
            inverseJoinColumns = @JoinColumn(name = "video_id"))
    private Set<Video> videos;

    private String visibility;
}
