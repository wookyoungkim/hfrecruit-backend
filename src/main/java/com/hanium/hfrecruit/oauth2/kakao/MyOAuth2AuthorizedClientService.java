package com.hanium.hfrecruit.oauth2.kakao;

import com.hanium.hfrecruit.domain.user.kakao.Member;
import com.hanium.hfrecruit.domain.user.kakao.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * Created by momentjin@gmail.com on 2019-12-11
 * Github : http://github.com/momentjin
 */

@Service
public class MyOAuth2AuthorizedClientService implements OAuth2AuthorizedClientService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void saveAuthorizedClient(OAuth2AuthorizedClient oAuth2AuthorizedClient, Authentication authentication) {
        String providerType = oAuth2AuthorizedClient.getClientRegistration().getRegistrationId();
        OAuth2AccessToken accessToken = oAuth2AuthorizedClient.getAccessToken();

        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        String id = String.valueOf(oauth2User.getAttributes().get("id"));
        String name = (String) ((LinkedHashMap) ((LinkedHashMap) oauth2User.getAttribute("kakao_account")).get("profile")).get("nickname");

        Member member = new Member(id, name, providerType, accessToken.getTokenValue());
        memberRepository.save(member);
    }

    @Override
    public <T extends OAuth2AuthorizedClient> T loadAuthorizedClient(String s, String s1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeAuthorizedClient(String s, String s1) {
        throw new UnsupportedOperationException();
    }

}