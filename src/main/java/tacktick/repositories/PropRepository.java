package tacktick.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tacktick.models.Prop;

@Repository
public interface PropRepository extends JpaRepository<Prop, Long> {

}
