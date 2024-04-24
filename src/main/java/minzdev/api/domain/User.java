package minzdev.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User implements Comparable<User> {

    private String name;
    private int score;

    @Override
    public int compareTo(User user) {
        return user.score - this.score;
    }

}
