package tacktick.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tacktick.models.Arena;

@Repository
public interface ArenaRepository extends JpaRepository<Arena, Long> {

}
