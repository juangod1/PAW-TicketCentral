package ar.edu.itba.paw2018b.webapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.io.*;
import java.util.concurrent.TimeUnit;

@EnableWebSecurity
@Configuration
@ComponentScan("ar.edu.itba.paw2018b.webapp.auth")
public class WebAuthConfig extends WebSecurityConfigurerAdapter {

    @Value("classpath:rememberme.key")
    private Resource rememberMeKey;

    @Autowired
    private UserDetailsService atlasUserDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(atlasUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.userDetailsService(atlasUserDetailsService)
                .sessionManagement()
                .invalidSessionUrl("/login")
              .and().authorizeRequests()
                .antMatchers("/login").anonymous()
                .antMatchers("/register").anonymous()
                .antMatchers("/create").anonymous()
                .antMatchers("/").permitAll()
                .antMatchers("/json/transaction/confirmCheckout").hasRole("USER")
                .antMatchers("/json/transaction/getTransactionsByUser/{userId}").hasRole("ADMIN")
                .antMatchers("/json/transaction/getTransactionById/{id}").hasRole("ADMIN")
                .antMatchers("/json/transaction/getTransactionsByScreening/{screeningId}").hasRole("ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
               .and().formLogin()
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .defaultSuccessUrl("/",false)
                .loginPage("/login")
              .and().rememberMe()
                .rememberMeParameter("j_rememberme")
                .key(getRememberMeKey())
                .userDetailsService(atlasUserDetailsService)
                .tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(30))
              .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
              .and().exceptionHandling()
                .accessDeniedPage("/403")
              .and().csrf().disable();
    }

    private String getRememberMeKey() {
        final StringWriter sw = new StringWriter();
        try (Reader reader = new InputStreamReader(rememberMeKey.getInputStream())){
            char[] data = new char[1024];
            int len;
            while ((len = reader.read(data))!= -1){
                sw.write(data,0,len);
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        return sw.toString();
    }

    @Override
    public void configure(final WebSecurity web) throws Exception{
        web.ignoring()
                .antMatchers("/resources/css/**", "/resources/vendor/**", "/resources/js/**", "/resources/img/**", "/favicon.ico", "/403");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }
}
