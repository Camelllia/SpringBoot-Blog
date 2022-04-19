package com.Blog.Blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

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
