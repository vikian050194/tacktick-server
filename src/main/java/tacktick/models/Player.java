package tacktick.models;

import javax.persistence.*;

@Entity
@Table(name = "player")
public class Player {

    public Player() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;

    @Column(name = "round", nullable = false)
    public long round;

    @Column(name = "tick", nullable = false)
    public long tick;

    @Column(name = "hp", nullable = false)
    public long hp;

    @Column(name = "x", nullable = false)
    public long x;

    @Column(name = "y", nullable = false)
    public long y;
}
