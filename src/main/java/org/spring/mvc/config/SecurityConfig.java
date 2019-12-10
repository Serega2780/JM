package org.spring.mvc.config;

import org.spring.mvc.security.CustomSimpleUrlAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages =
        "org.spring.mvc")
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    @Resource(name = "userDetailsService")
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
//                .and()
//                .inMemoryAuthentication().withUser("admin")
//                .password("admin").roles("ADMIN")
        ;
    }

    @Bean
    @SuppressWarnings("deprecation")
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }


    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomSimpleUrlAuthenticationSuccessHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable();


        // For ADMIN only.
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
        ;

        // For USER and OWNER
        http
                .authorizeRequests()
                .antMatchers("/users/**", "/list/**")
                .hasAnyRole("ADMIN", "MAGICIAN", "USER")
                .anyRequest().authenticated()
        ;

        // Config for Login Form
        http
                .authorizeRequests()
                .and()
                .formLogin()//
                .usernameParameter("login")//
                .passwordParameter("password")
                .loginPage("/")
                .loginProcessingUrl("/perform_login")
                .successHandler(customAuthenticationSuccessHandler())
                .failureUrl("/")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .permitAll()
                .invalidateHttpSession(true)
                .and()
        ;

//        http.authorizeRequests()
//                .antMatchers("/")
//                .permitAll()
//                .antMatchers("/**")
//                .hasAnyRole("ADMIN", "USER")
//                .and()
//                .formLogin()
//                .usernameParameter("login")
//                .passwordParameter("password")
//                .loginPage("/")
//                .loginProcessingUrl("/perform_login")
//                .defaultSuccessUrl("/users")
//                .failureUrl("/perform_login?error=true")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutSuccessUrl("/perform_login?logout=true")
//                .invalidateHttpSession(true)
//                .permitAll()
//                .and()
//                .csrf()
//                .disable();
    }
}
