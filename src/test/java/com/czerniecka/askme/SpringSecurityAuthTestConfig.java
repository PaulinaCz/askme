package com.czerniecka.askme;

import com.czerniecka.askme.model.User;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@TestConfiguration
public class SpringSecurityAuthTestConfig {

    @Bean
    @Primary
    public UserDetailsService userDetailsService(){

        User user = new User("John", "Snow", "johnsnow", "johnsnow@gmail.com", "JohnSnow123." );

        return new InMemoryUserDetailsManager(user);
    }
}
