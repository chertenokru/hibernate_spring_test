package ru.chertenok.spring.hibernate.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // (1)
        auth.jdbcAuthentication().dataSource(dataSource).
                usersByUsernameQuery("SELECT NAME, PASSWORD, ENABLED FROM USERS WHERE NAME=?")
                .authoritiesByUsernameQuery("SELECT u.name,p.name FROM users u" +
                        " JOIN  user_role ur ON u.id=ur.user_id " +
                        " JOIN role_permission rp ON rp.role_id=ur.role_id " +
                        " JOIN permissions p ON p.id=rp.permission_id " +
                        " WHERE u.name = ?").rolePrefix("ROLE_");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/user/**").hasRole("COURSE-LIST")
                .and().formLogin().loginProcessingUrl("/authenticateTheUser").permitAll();
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
