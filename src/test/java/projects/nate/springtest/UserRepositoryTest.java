package projects.nate.springtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import projects.nate.springtest.entities.User;
import projects.nate.springtest.respository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static projects.nate.springtest.services.Debug.printError;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser() {
        try {
            User user = new User();
            user.setUsername("test");
            user.setPassword("user");

            User savedUser = userRepository.save(user);

            User userExists = entityManager.find(User.class, savedUser.getId());

            assertThat(userExists.getUsername()).isEqualTo(user.getUsername());
        } catch (DataIntegrityViolationException e) {
            printError("Row already exists");
        }


    }

    @Test
    public void testFindUserByUsername() {
        String username = "paulpughLOL";

        User user = userRepository.findByUsername(username);

        assertThat(user).isNotNull();
    }
}
