package minzdev.api.repository;

import minzdev.api.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

    Set<User> users = new HashSet<>();

    public void add(User user) {
        users.add(user);
    }

    public List<User> findAllOrderByScore() {
        return users.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<User> findByScore(int score) {
        return users.stream()
                .filter(user -> user.getScore() == score)
                .collect(Collectors.toList());
    }

}
