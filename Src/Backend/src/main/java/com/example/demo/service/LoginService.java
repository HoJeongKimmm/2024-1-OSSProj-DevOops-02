package com.example.demo.service;

import com.example.demo.dto.LoginDTO;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 사용자 정보를 추가로 처리
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String id = (String) attributes.get("sub"); // Google의 고유 사용자 ID
        String email = (String) attributes.get("email");
        String nickname = (String) attributes.get("name");

        // LoginDTO 생성 및 값 설정
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setId(id);
        loginDTO.setEmail(email);
        loginDTO.setNickname(nickname);



        return oAuth2User;
    }
}