package MyBlog.Blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 블로그 홈
    @GetMapping()
    public String index() {
        return "index";
    }
}