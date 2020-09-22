package com.hanium.hfrecruit.auth;

import com.hanium.hfrecruit.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable() // (2)
                .and()
                .authorizeRequests() // (3)
                .antMatchers("/", "/css/**","/images/**","/js/**","/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated() // (5)
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService)
                .and()
                .defaultSuccessUrl("/loginsuccessed", true);
    }
//    private ClientRegistration getRegistration(OAuth2ClientProperties clientProperties, String client) {
//        if ("google".equals(client)) {
//            OAuth2ClientProperties.Registration registration = clientProperties.getRegistration().get("google");
//            return CommonOAuth2Provider.GOOGLE.getBuilder(client)
//                    .clientId(registration.getClientId())
//                    .clientSecret(registration.getClientSecret())
//                    .scope("email", "profile")
//                    .build();
//        }
//
//        return null;
//    }
}
