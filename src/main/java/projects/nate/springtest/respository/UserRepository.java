package projects.nate.springtest.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import projects.nate.springtest.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u from User u WHERE u.username = ?1")
    User findByUsername(String username);

}
