package tacktick.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "arena")
public class Arena {

    public Arena() {

    }

    public Arena(String name) {
        this.name = name;
    }

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;

    @JsonProperty("name")
    @NotBlank(message = "Name is mandatory")
    @Column(name = "name", nullable = false)
    public String name;
}
