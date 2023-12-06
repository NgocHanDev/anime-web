package vn.group04.animeweb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import vn.group04.animeweb.filter.JwtFilter;
import vn.group04.animeweb.service.UserService;

import java.util.Arrays;
@Configuration
public class SecurityConfiguration {
    private final JwtFilter jwtFilter;
    public SecurityConfiguration(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserService userService){
        DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
        dap.setUserDetailsService(userService);
        dap.setPasswordEncoder(passwordEncoder());
        return dap;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                auth -> auth
                        .requestMatchers(HttpMethod.GET, Enpoints.PUBLIC_GET_ENPOINTS).permitAll()
                        .requestMatchers(HttpMethod.POST, Enpoints.PUBLIC_POST_ENPOINTS).permitAll()
                        .requestMatchers(HttpMethod.PATCH, Enpoints.PUBLIC_PATCH_ENPOINTS).permitAll()
                        .requestMatchers(HttpMethod.DELETE, Enpoints.PUBLIC_DELETE_ENPOINTS).permitAll()
                        .requestMatchers(HttpMethod.GET, Enpoints.ADMIN_ENPOINTS).hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, Enpoints.ADMIN_ENPOINTS).hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, Enpoints.ADMIN_ENPOINTS).hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, Enpoints.ADMIN_ENPOINTS).hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, Enpoints.ADMIN_ENPOINTS).hasAuthority("ADMIN")
                        .anyRequest().permitAll()
        );

        httpSecurity.cors(httpSecurityCorsConfigurer ->httpSecurityCorsConfigurer.configurationSource(
                request -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.addAllowedOrigin("*");
                    corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH"));
                    corsConfiguration.addAllowedHeader("*");
                    return corsConfiguration;
                }
        ));

        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.csrf(csrf -> csrf.disable());

        return httpSecurity.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
