package com.miniproject.spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.miniproject.spring.dto.SignUpRequestDto;
import com.miniproject.spring.service.KakaoUserService;
import com.miniproject.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;

    @Autowired
    public UserController(UserService userService, KakaoUserService kakaoUserService){
        this.userService = userService;
        this.kakaoUserService = kakaoUserService;
    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    //회원가입 페이지
    @GetMapping("/signup")
    public String signup() {

        return "signup";
    }

    //카카오
    @GetMapping("/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        kakaoUserService.kakaoLogin(code);
        return "redirect:/";
    }

    //가입 요청 처리
    @PostMapping("/signup")
    public String registerUser(SignUpRequestDto requestDto, Model model) {

        return "redirect:/";
    }
}
