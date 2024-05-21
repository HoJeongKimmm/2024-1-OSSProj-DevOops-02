package com.example.demo.service;

import com.example.demo.dto.LoginDTO;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class LoginService extends DefaultOAuth2UserService {

    private static final String ALLOWED_DOMAIN = "@dgu.ac.kr";

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 사용자 정보를 추가로 처리
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");

        // 이메일 도메인 검증
        if (!email.endsWith(ALLOWED_DOMAIN)) {
            throw new OAuth2AuthenticationException("허용되지 않는 이메일 도메인입니다.");
        }

        // 사용자 정보를 포함한 커스터마이징된 OAuth2User 객체 생성
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                attributes,
                "email"
        );
    }
}