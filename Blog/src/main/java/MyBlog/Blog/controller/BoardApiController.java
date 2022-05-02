package MyBlog.Blog.controller;

import MyBlog.Blog.model.board;
import MyBlog.Blog.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RestController
@RequestMapping("/api")
class BoardApiController {

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/boards")
    List<board> all(@RequestParam(required = false, defaultValue = "") String title, @RequestParam(required = false, defaultValue = "") String category) {

        if(StringUtils.isEmpty(title) && StringUtils.isEmpty(category)) {
            return boardRepository.findAll();
        } else if(StringUtils.isEmpty(title) && !StringUtils.isEmpty(category)) {
            return boardRepository.findByCategory(category);
        } else if(!StringUtils.isEmpty(title) && StringUtils.isEmpty(category)) {
            return boardRepository.findByTitle(title);
        } else {
            return boardRepository.findByTitleAndCategory(title, category);
        }
    }

    @PostMapping("/boards")
    board newboard(@RequestBody board newboard) {
        return boardRepository.save(newboard);
    }

    @GetMapping("/boards/{id}")
    board one(@PathVariable Long id) {

       return boardRepository.findById(id).orElse(null);
    }

    @PutMapping("/boards/{id}")
    board replaceboard(@RequestBody board newboard, @PathVariable Long id) {

        return boardRepository.findById(id)
                .map(board -> {
                    board.setTitle(newboard.getTitle());
                    board.setContent(newboard.getContent());
                    board.setCategory(newboard.getCategory());
                    return boardRepository.save(board);
                })
                .orElseGet(() -> {
                    newboard.setId(id);
                    return boardRepository.save(newboard);
                });
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/boards/{id}")
    void deleteboard(@PathVariable Long id) {
        boardRepository.deleteById(id);
    }
}
