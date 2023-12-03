package tacktick.models;

import java.util.Collection;
import javax.persistence.*;

@Entity
@Table(name = "user")
public class UserModel {

    public UserModel() {

    }

    public UserModel(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;

    @Column(name = "name", nullable = false)
    public String name;

    @OneToMany()
    @JoinColumn(name = "user_id")
    private Collection<PlayerModel> players;
}
