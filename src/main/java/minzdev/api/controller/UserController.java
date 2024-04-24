package minzdev.api.controller;

import lombok.AllArgsConstructor;
import minzdev.api.ApiResponse;
import minzdev.api.domain.User;
import minzdev.api.exception.CustomException;
import minzdev.api.exception.ErrorCode;
import minzdev.api.exception.restriction.InputRestriction;
import minzdev.api.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController extends BaseController {

    private final UserService userService;

    @PostMapping("/user")
    public ApiResponse<User> add(@RequestBody User user) {
        int score = user.getScore();

        if(score > 100 || score < 0) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "점수는 0점 이상 100점 이하로 입력하세요.", new InputRestriction(0, 100));
        }

        userService.addUser(user);
        return makeApiResponse(user);
    }

    @GetMapping("/users")
    public ApiResponse<User> findAllOrderByScore() {
        List<User> users = userService.findAllOrderByScore();
        return makeApiResponse(users);
    }

    @GetMapping("/users/{score}")
    public ApiResponse<User> findByScore(@PathVariable int score) {
        List<User> users = userService.findByScore(score);
        return makeApiResponse(users);
    }

}
