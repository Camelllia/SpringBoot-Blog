package MyBlog.Blog.controller;

import MyBlog.Blog.model.board;
import MyBlog.Blog.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/WriteForm")
    public String WriteForm(Model model) {
        List<board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "board/WriteForm";
    }

    // 카테고리 - 여행
    @GetMapping("/Trip")
    public String trip() {
        return "board/Trip";
    }

    // 카테고리 - 공부
    @GetMapping("/Study")
    public String study() {
        return "board/Study";
    }

    // 카테고리 - 잡담
    @GetMapping("/Gossip")
    public String gossip() {
        return "board/Gossip";
    }
}