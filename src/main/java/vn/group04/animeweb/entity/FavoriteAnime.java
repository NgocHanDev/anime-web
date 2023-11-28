package vn.group04.animeweb.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "favorite_anime")
@Data
public class FavoriteAnime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "anime_id")
    private Anime anime;
    @ManyToOne(cascade =  {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;
}
