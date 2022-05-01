package MyBlog.Blog.service;

import MyBlog.Blog.model.User;
import MyBlog.Blog.model.board;
import MyBlog.Blog.repository.BoardRepository;
import MyBlog.Blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    public board save(String username, board board) {
        User user = userRepository.findByUsername(username);
        board.setUser(user);
        return boardRepository.save(board);
    }
}
