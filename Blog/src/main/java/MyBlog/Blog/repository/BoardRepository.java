package MyBlog.Blog.repository;

import MyBlog.Blog.model.board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<board, Long> {

    // 제목 단위 조회용
    List<board> findByTitle(String title);

    // 카테고리 단위 조회용
    List<board> findByCategory(String category);

    // 제목 + 카테고리 동시 조회
    List<board> findByTitleAndCategory(String title, String category);
}
