package tacktick.models;

import java.util.Collection;
import javax.persistence.*;

@Entity
@Table(name = "arena")
public class ArenaModel {

    public ArenaModel() {

    }

    public ArenaModel(String name, long height, long width) {
        this.name = name;
        this.height = height;
        this.width = width;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "height", nullable = false)
    public long height;

    @Column(name = "width", nullable = false)
    public long width;

    @OneToMany()
    @JoinColumn(name = "arena_id")
    public Collection<PropModel> props;
}
