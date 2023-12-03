package tacktick.models;

import java.util.Collection;
import javax.persistence.*;

@Entity
@Table(name = "game")
public class GameModel {

    public enum Status {
        CREATED,
        STARTED,
        FINISHED
    }

    public GameModel() {

    }

    public GameModel(String name) {
        this.name = name;
        this.status = Status.CREATED;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "status", nullable = false)
    public Status status;

    @Column(name = "round", nullable = false)
    public long round;

    @Column(name = "ticks", nullable = false)
    public long ticks;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "arena_id")
    public ArenaModel arena;

    @OneToMany()
    @JoinColumn(name = "game_id")
    private Collection<PlayerModel> players;
}
