package az.task.parceldelivery.security;

import az.task.parceldelivery.security.filter.AuthenticationFilter;
import az.task.parceldelivery.security.filter.AuthorizationFilter;
import az.task.parceldelivery.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * @author Get Arrays (https://www.getarrays.io/)
 * @version 1.0
 * @since 7/10/2021
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManagerBean(), userService);
        authenticationFilter.setFilterProcessesUrl("/user/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers("/user/login/**", "/customer/sign-up/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(authenticationFilter);
        http.addFilterBefore(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
