package com.hanium.hfrecruit.oauth2;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

public enum CustomOAuthProvider {

    KAKAO {
        @Override
        public ClientRegistration.Builder getBuilder() {
            return getBuilder("kakao", ClientAuthenticationMethod.POST)
                    .scope("profile", "talk_message") // 요청할 권한
                    .authorizationUri("https://kauth.kakao.com/oauth/authorize") // authorization code 얻는 API
                    .tokenUri("https://kauth.kakao.com/oauth/token") // access Token 얻는 API
                    .userInfoUri("https://kapi.kakao.com/v2/user/me") // 유저 정보 조회 API
                    .clientId("f9580fd7455b0683b9b55e0d431b1e00")
                    .clientSecret("G6hy1S3Z1xsnBiavVyXCYyRxdWa9kzh4")
                    .userNameAttributeName("id") // userInfo API Response에서 얻어올 ID 프로퍼티
                    .clientName("Kakao"); // spring 내에서 인식할 OAuth2 Provider Name
        }
    };

    private static final String DEFAULT_LOGIN_REDIRECT_URL = "{baseUrl}/login/oauth2/code/{registrationId}";

    protected final ClientRegistration.Builder getBuilder(String registrationId,
                                                          ClientAuthenticationMethod method) {

        ClientRegistration.Builder builder = ClientRegistration.withRegistrationId(registrationId);
        builder.clientAuthenticationMethod(method);
        builder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);
        builder.redirectUriTemplate(CustomOAuthProvider.DEFAULT_LOGIN_REDIRECT_URL);
        return builder;
    }

    public abstract ClientRegistration.Builder getBuilder();
}