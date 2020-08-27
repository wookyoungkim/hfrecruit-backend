package com.hanium.hfrecruit.oauth2.google;

import com.hanium.hfrecruit.domain.user.google.Role;
import lombok.RequiredArgsConstructor;
import com.hanium.hfrecruit.oauth2.google.CustomOAuth2UserService;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable() // (2)
                .and()
                    .authorizeRequests() // (3)
                    .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**", "/login/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated() // (5)
                .and()
                    .oauth2Login()// (7)
                        .userInfoEndpoint() // (8)
                            .userService(customOAuth2UserService)
                .and()
                    .defaultSuccessUrl("/loginSuccess")
                    .failureUrl("/loginFailure");
//                .and()
//                    .exceptionHandling()
//                    .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));
    }
    private ClientRegistration getRegistration(OAuth2ClientProperties clientProperties, String client) {
        if ("google".equals(client)) {
            OAuth2ClientProperties.Registration registration = clientProperties.getRegistration().get("google");
            return CommonOAuth2Provider.GOOGLE.getBuilder(client)
                    .clientId(registration.getClientId())
                    .clientSecret(registration.getClientSecret())
                    .scope("email", "profile")
                    .build();
        }

        return null;
    }
}
