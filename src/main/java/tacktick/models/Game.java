package tacktick.models;

import java.util.Collection;
import javax.persistence.*;

@Entity
@Table(name = "game")
public class Game {

    public Game() {

    }

    public Game(String name) {
        this.name = name;
        this.status = GameStatus.Created;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "status", nullable = false)
    public GameStatus status;

    @Column(name = "round", nullable = false)
    public long round;

    @Column(name = "ticks", nullable = false)
    public long ticks;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "arena_id")
    public Arena arena;

    @OneToMany()
    @JoinColumn(name = "game_id")
    private Collection<Player> players;
}
