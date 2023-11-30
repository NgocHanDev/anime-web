package vn.group04.animeweb.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "animes")
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "release_year")
    private int releaseYear;
    @Column(name = "view")
    private int view;
    @Column(name = "description")
    private String description;
    @Column(name = "cover_image", columnDefinition =  "TEXT")
    @Lob
    private String coverImage;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "anime")
    private List<Episode> episodeList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "anime")
    private List<Comment> commentList;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "anime_category",
            joinColumns = @JoinColumn(name = "anime_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categoryList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "anime")
    private List<FavoriteAnime> favoriteAnimes;
}
