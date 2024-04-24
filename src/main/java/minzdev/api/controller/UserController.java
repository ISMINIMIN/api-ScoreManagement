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

    private static final InputRestriction instance = InputRestriction.getInstance();
    private final UserService userService;

    @PostMapping("/user")
    public ApiResponse<User> add(@RequestBody User user) {
        int score = user.getScore();
        int minScore = instance.getMinScore();
        int maxScore = instance.getMaxScore();

        if(score > maxScore || score < minScore) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "점수는 " + minScore + "점 이상 " + maxScore + "점 이하로 입력하세요.", instance);
        }

        userService.addUser(user);
        return makeApiResponse(user);
    }

    @PostMapping("/settings")
    public ApiResponse<InputRestriction> setScore(@RequestBody InputRestriction inputRestriction) {
        if(!userService.findAllOrderByScore().isEmpty()) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "사용자 정보가 입력되어 있어 점수 범위 변경이 불가능합니다.");
        }

        instance.setScore(inputRestriction.getMinScore(), inputRestriction.getMaxScore());
        return makeApiResponse(instance);
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
