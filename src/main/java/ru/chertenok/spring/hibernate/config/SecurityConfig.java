package ru.chertenok.spring.hibernate.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ru.chertenok.spring.hibernate.services.UserService;

import javax.sql.DataSource;

import static ru.chertenok.spring.hibernate.config.Config.*;
import static ru.chertenok.spring.hibernate.config.Config.PAGE_MAP;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private DataSource dataSource;
    private UserService userService;
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCustomAuthenticationSuccessHandler(CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // (1)
        auth.jdbcAuthentication().dataSource(dataSource).
                usersByUsernameQuery(SQL_GET_USER_LIST)
                .authoritiesByUsernameQuery(SQL_GET_USER_WITH_PERMISSION).rolePrefix(PREFIX_PERMISSION);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll()
            //    .antMatchers("/course/**").hasRole("COURSE-LIST")
                .and()
                .formLogin().loginPage(PAGE_MAP.get(PagesName.userLoginForm).getURL() )
                .loginProcessingUrl("/authenticateTheUser").permitAll()
                .and().logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage(PAGE_MAP.get(PagesName.userAccessDenied).getURL());
        // без этого не работает, требует наличия поля csrf для подтверждения подлинности запроса
        http.csrf().ignoringAntMatchers("/api/**");

    }

// sample
// @Override
// protected void configure(AuthenticationManagerBuilder auth) throws Exception { // (2)
// User.UserBuilder users = User.withDefaultPasswordEncoder();
// auth.inMemoryAuthentication()
// .withUser(users.username("user1").password("pass1").roles("USER", "ADMIN"))
// .withUser(users.username("user2").password("pass2").roles("USER"));
// }


}
