package minzdev.api.controller;

import lombok.AllArgsConstructor;
import minzdev.api.ApiResponse;
import minzdev.api.domain.User;
import minzdev.api.exception.CustomException;
import minzdev.api.exception.ErrorCode;
import minzdev.api.exception.restriction.InputRestriction;
import minzdev.api.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController extends BaseController {

    private final UserService userService;

    @GetMapping("/user/{name}/{score}")
    public ApiResponse<User> add(@PathVariable String name, @PathVariable int score) {
        if(score > 100 || score < 0) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "점수는 0점 이상 100점 이하로 입력하세요.", new InputRestriction(0, 100));
        }

        User user = userService.addUser(name, score);
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
