package com.lmh.sessionlogin.web;

import com.lmh.sessionlogin.config.SecurityConfig;
import com.lmh.sessionlogin.web.dto.PostsSaveRequestDto;
import com.lmh.sessionlogin.web.dto.PostsUpdateRequestDto;
import com.lmh.sessionlogin.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final SecurityConfig securityConfig;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return securityConfig.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return securityConfig.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id) {
        return securityConfig.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        securityConfig.delete(id);
        return id;
    }
}
