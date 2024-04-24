package minzdev.api.repository;

import minzdev.api.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryTest {

    UserRepository userRepository = new UserRepository();

    @AfterEach
    void afterEach() {
        userRepository.users.clear();
    }

    @Test
    void addUserTest() {
        User user01 = new User("user01", 100);
        User user02 = new User("user02", 90);

        userRepository.add(user01);
        userRepository.add(user02);

        assertThat(userRepository.users.size()).isEqualTo(2);
    }

}
