package tacktick.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tacktick.models.GameModel;

@Repository
public interface GameRepository extends JpaRepository<GameModel, Long> {

}
