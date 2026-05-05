package com.lmh.sessionlogin.web;

import com.lmh.sessionlogin.config.SecurityConfig;
import com.lmh.sessionlogin.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final SecurityConfig securityConfig;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", securityConfig.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = securityConfig.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
