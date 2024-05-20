package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model, @AuthenticationPrincipal OAuth2User principal) {
        String id = principal.getAttribute("sub");
        String email = principal.getAttribute("email");
        String nickname = principal.getAttribute("name");

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setId(id);
        loginDTO.setEmail(email);
        loginDTO.setNickname(nickname);

        model.addAttribute("user", loginDTO);
        return "home";
    }
}