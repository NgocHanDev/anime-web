package vn.group04.animeweb.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "episode")
@Data
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "episode_number")
    private int episodeNumber;
    @Column(name = "source")
    private String source;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "anime_id", nullable = false)
    private Anime anime;
}
