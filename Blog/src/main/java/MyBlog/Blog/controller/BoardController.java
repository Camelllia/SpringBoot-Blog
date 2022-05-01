package MyBlog.Blog.controller;

import MyBlog.Blog.model.board;
import MyBlog.Blog.repository.BoardRepository;
import MyBlog.Blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;

    // 전체 글 모음
    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size=10) Pageable pageable, @RequestParam(required = false, defaultValue = "") String searchText) {

        //페이징 처리
        Page<board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(boards.getPageable().getPageNumber() - 4, 1);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        model.addAttribute("boards", boards);
        return "board/list";
    }

    // 카테고리 - 여행
    @GetMapping("/Trip")
    public String trip(Model model) {

        List<board> tripBoards = boardRepository.findByCategory("여행");
        model.addAttribute("tripBoards", tripBoards);
        return "board/Trip";
    }

    // 카테고리 - 공부
    @GetMapping("/Study")
    public String study(Model model) {

        List<board> studyBoards = boardRepository.findByCategory("공부");
        model.addAttribute("studyBoards", studyBoards);
        return "board/Study";
    }

    // 카테고리 - 잡담
    @GetMapping("/Gossip")
    public String gossip(Model model) {

        List<board> gossipBoards = boardRepository.findByCategory("잡담");
        model.addAttribute("gossipBoards", gossipBoards);
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
    public String formSubmit(@Valid board board, BindingResult bindingResult, Authentication authentication) {

        if(bindingResult.hasErrors()) {
            return "board/form";
        }
        String username = authentication.getName();
        boardService.save(username, board);
        return "redirect:/board/list";
    }

}