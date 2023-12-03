package tacktick.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tacktick.models.PropModel;

@Repository
public interface PropRepository extends JpaRepository<PropModel, Long> {

}
