package tacktick.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tacktick.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

}
