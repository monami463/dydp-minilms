package kr.co.dongyang.study.minilms.config;

import kr.co.dongyang.study.minilms.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationFailureHandler getAuthenticationFailureHandler(){

        return new UserAuthenticationFailureHandler();
    }

    @Override
    public void init(WebSecurity web) throws Exception {

        web.ignoring()
                .antMatchers("favicon.ico", "/res/**");


        super.init(web);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userService)
                .passwordEncoder(getPasswordEncoder());

        super.configure(auth);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().disable();
        http.headers().frameOptions().sameOrigin();

        http.authorizeRequests()
                .antMatchers("/"
                        , "/user/register"
                        ,"/user/register-complete"
                        , "/api/user/register.api"
                ).permitAll();

        http.logout()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true);

        http.exceptionHandling()
                        .accessDeniedPage("/common/denied");

        http.authorizeRequests()
                        .antMatchers("/admin/**")
                                .hasAuthority("ROLE_ADMIN");


        http.formLogin()
                .loginPage("/user/login")
                .failureHandler(getAuthenticationFailureHandler())
                .permitAll();


        super.configure(http);
    }
}
