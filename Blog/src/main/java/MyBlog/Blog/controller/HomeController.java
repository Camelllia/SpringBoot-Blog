package MyBlog.Blog.controller;

import MyBlog.Blog.model.board;
import MyBlog.Blog.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Random;

@Controller
public class HomeController {

    @Autowired
    BoardRepository boardRepository;

    // 블로그 홈
    @GetMapping()
    public String index(Model model,  @RequestParam(required = false) Long randomId) {

        // 랜덤으로 추천 글 생성
        Random random = new Random();
        int pivot = 0;
        board recommendedBoard;
        List<board> allBoards = boardRepository.findAll();

        for (board allBoard : allBoards) {
            pivot = (int) Math.max(pivot, allBoard.getId());
        }

        while (true) {
            randomId = (long) (Math.random() * pivot + 1);

            board tempBoard = boardRepository.findById(randomId).orElse(null);

            if(tempBoard != null) {
                recommendedBoard = boardRepository.findById(randomId).orElse(null);
                break;
            }
        }

        model.addAttribute("recommendedBoard", recommendedBoard);

        return "index";
    }
}