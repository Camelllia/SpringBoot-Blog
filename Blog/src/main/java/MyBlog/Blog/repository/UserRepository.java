package MyBlog.Blog.repository;

import MyBlog.Blog.model.User;
import MyBlog.Blog.model.board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    // 유저이름 단위 조회용
    User findByUsername(String username);
}
