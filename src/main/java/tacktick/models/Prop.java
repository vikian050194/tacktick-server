package tacktick.models;

import javax.persistence.*;

@Entity
@Table(name = "prop")
public class Prop {

    public Prop() {

    }

    public Prop(PropType type, long x, long y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;

    @Column(name = "type", nullable = false)
    public PropType type;

    @Column(name = "x", nullable = false)
    public long x;

    @Column(name = "y", nullable = false)
    public long y;
}
