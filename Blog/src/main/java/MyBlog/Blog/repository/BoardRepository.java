package MyBlog.Blog.repository;

import MyBlog.Blog.model.board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<board, Long> {
}
