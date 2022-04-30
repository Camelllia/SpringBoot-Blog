package MyBlog.Blog.repository;

import MyBlog.Blog.model.User;
import MyBlog.Blog.model.board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
