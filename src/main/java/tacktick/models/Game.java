package tacktick.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "game")
public class Game {

    public Game() {

    }

    public Game(String name) {
        this.name = name;
        this.status = GameStatus.Created;
        this.round = 0l;
        this.moves = 0l;
    }

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;

    @JsonProperty("name")
    @NotBlank(message = "Name is mandatory")
    @Column(name = "name", nullable = false)
    public String name;

    @JsonProperty("status")
    @Column(name = "status", nullable = false)
    public GameStatus status;

    @JsonProperty("round")
    @Column(name = "round", nullable = false)
    public long round;

    @JsonProperty("moves")
    @Column(name = "moves", nullable = false)
    public long moves;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "arena_id")
    public Arena arena;
}
