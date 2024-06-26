package minzdev.api.service;

import lombok.AllArgsConstructor;
import minzdev.api.domain.User;
import minzdev.api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void addUser(User user) {
        userRepository.add(user);
    }

    public List<User> findAllOrderByScore() {
        return userRepository.findAllOrderByScore();
    }

    public List<User> findByScore(int score) {
        return userRepository.findByScore(score);
    }

}
