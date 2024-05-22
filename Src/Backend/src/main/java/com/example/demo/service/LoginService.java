package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;


//@Service
//public class LoginService extends DefaultOAuth2UserService {
//
//    private static final String ALLOWED_DOMAIN = "@dgu.ac.kr";
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        OAuth2User oAuth2User = super.loadUser(userRequest);
//
//        // 사용자 정보를 추가로 처리
//        Map<String, Object> attributes = oAuth2User.getAttributes();
//        String email = (String) attributes.get("email");
//
//        // 이메일 도메인 검증
//        if (!email.endsWith(ALLOWED_DOMAIN)) {
//            throw new OAuth2AuthenticationException("허용되지 않는 이메일 도메인입니다.");
//        }
//
//        // 사용자 정보를 포함한 커스터마이징된 OAuth2User 객체 생성
//        return new DefaultOAuth2User(
//                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
//                attributes,
//                "email"
//        );
//    }
//}

@Service
public class LoginService extends DefaultOAuth2UserService {

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    private static final String ALLOWED_DOMAIN = "@dgu.ac.kr";

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        logger.info("loadUser called with userRequest: {}", userRequest);

        OAuth2User oAuth2User;
        try {
            oAuth2User = super.loadUser(userRequest);
        } catch (OAuth2AuthenticationException e) {
            logger.error("Error loading user", e);
            throw e;
        }

        logger.info("OAuth2User loaded: {}", oAuth2User);

        // 사용자 정보를 추가로 처리
        Map<String, Object> attributes = oAuth2User.getAttributes();
        logger.info("User attributes: {}", attributes);

        String email = (String) attributes.get("email");
        if (email == null) {
            logger.error("Email attribute is missing in user info");
            throw new OAuth2AuthenticationException("Email attribute is missing");
        }

        logger.info("User email: {}", email);

        // 이메일 도메인 검증
        if (!email.endsWith(ALLOWED_DOMAIN)) {
            logger.error("Invalid email domain: {}", email);
            throw new OAuth2AuthenticationException("허용되지 않는 이메일 도메인입니다.");
        }

        // 사용자 정보를 포함한 커스터마이징된 OAuth2User 객체 생성
        DefaultOAuth2User customOAuth2User = new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                attributes,
                "email"
        );
        logger.info("Custom OAuth2User created: {}", customOAuth2User);

        return customOAuth2User;
    }
}