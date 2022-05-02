package MyBlog.Blog.controller;

import MyBlog.Blog.model.User;
import MyBlog.Blog.model.User;
import MyBlog.Blog.model.board;
import MyBlog.Blog.repository.UserRepository;
import MyBlog.Blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RestController
@RequestMapping("/api")
class UserApiController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    List<User> all() {
        List<User> users = userRepository.findAll();
        return users;
    }


    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping("/users/{id}")
    User one(@PathVariable Long id) {

        return userRepository.findById(id).orElse(null);
    }

    @PutMapping("/users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

        return userRepository.findById(id)
                .map(User -> {
                    // 기존 데이터 삭제
                    User.getBoards().clear();
                    // 새로운 값으로 바꿔주기
                    User.getBoards().addAll(newUser.getBoards());
                    for (board board : User.getBoards()) {
                        board.setUser(User);
                    }
                    return userRepository.save(User);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return userRepository.save(newUser);
                });
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
