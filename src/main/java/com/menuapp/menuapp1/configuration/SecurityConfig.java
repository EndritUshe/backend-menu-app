package com.menuapp.menuapp1.configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableMethodSecurity
@Configuration
@AllArgsConstructor
//SecurityScheme perdoret per testimin ne SWAGGER
@SecurityScheme( name = "basicAuth", type = SecuritySchemeType.HTTP, scheme = "basic")
public class SecurityConfig {

//    private UserDetails userDetails;

    //Authorization
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf)->csrf.disable())
                .authorizeHttpRequests((authorize)-> authorize
//                        .requestMatchers(HttpMethod.POST,"/api/users/save").permitAll()
                        .requestMatchers(HttpMethod.GET,"/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/product/findall").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/product//findby/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/review/findByProductId/{productId}").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/review/findBy/{productId}/{reviewId}").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/vendor/findAll").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/vendor/findByProductId/{productId}").permitAll()
                        .requestMatchers(HttpMethod.GET,("/findBy/{productId}/{vendorId}" )).permitAll()

                        .anyRequest().authenticated()).httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Bej Role per cdo kategori (USER, ADMIN, HR, MANAGER) dhe nje default qe do shfaqe
    //Home Page kur te hapet fronti

    //In memory authentication
//    @Bean
//    UserDetailsService userDetailsService() {
//        UserDetails endrit = User.builder()
//                .username("endrit")
//                .password(passwordEncoder().encode("endrit"))
//                .roles("USER")
//                .build();
//
//        UserDetails erisa = User.builder()
//                .use rname("erisa")
//                .password(passwordEncoder().encode("erisa"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(endrit, erisa);
//    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}