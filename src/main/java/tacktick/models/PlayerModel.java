package tacktick.models;

import javax.persistence.*;

@Entity
@Table(name = "player")
public class PlayerModel {

    public enum Status {
        DRAFT,
        ALIVE,
        DEAD,
    }

    public enum Action {
        EMPTY,
        LEFT,
        RIGHT,
        UP,
        DOWN
    }

    public PlayerModel() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;

    @Column(name = "round", nullable = false)
    public long round;

    @Column(name = "tick", nullable = false)
    public long tick;

    @Column(name = "health", nullable = false)
    public long health;

    @Column(name = "x", nullable = false)
    public long x;

    @Column(name = "y", nullable = false)
    public long y;

    @Column(name = "user_id", nullable = false)
    public long userId;

    @Column(name = "status", nullable = false)
    public Status status;

    @Column(name = "action", nullable = false)
    public Action action;
}
