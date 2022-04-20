package MyBlog.Blog.controller;

import MyBlog.Blog.model.board;
import MyBlog.Blog.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    // 전체 글 모음
    @GetMapping("/list")
    public String WriteForm(Model model) {
        List<board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "board/list";
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

    // 글 작성 및 수정 폼
    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id) {

        // id가 없다 -> 원본 글이 없기 때문에 새 값 넘겨주기
        if(id == null) {
            model.addAttribute("board", new board());
        } else {
            // id가 있다 -> 원본 글이 있기 때문에 id를 통해 글 조회해서 보여줌
            board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }
        return "board/form";
    }

    // 입력 받은 데이터 저장
    @PostMapping("/form")
    public String formSubmit(@ModelAttribute board board) {
        boardRepository.save(board);
        return "redirect:/board/list";
    }
}