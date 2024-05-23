package org.example.proyectofinal.security;

import org.example.proyectofinal.security.jwt.filter.JwtAuthFilter;
import org.example.proyectofinal.security.jwt.logout.CustomLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @Autowired
    JwtAuthFilter jwtAuthFilter;
    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;
    @Autowired
    private UserDetailsService userDetailsService;


    //@Bean
    //public UserDetailsService userDetailsService() {
    //    return new UserDetailsServiceImp();
    //}

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(
                auth -> auth
                    .requestMatchers("/login/login").permitAll()
                    .requestMatchers("/login/token").permitAll()
                    .requestMatchers("/empleados/rrhh/**").hasAnyAuthority("RECURSOS HUMANOS")
                    .requestMatchers("/empleados/**").hasAnyAuthority("SERVICIOS", "VENTAS", "MANAGER", "SUPPORT",
                        "DEVELOPER", "TESTER", "ANALYST", "CONSULTANT", "ENGINEER", "RECURSOS HUMANOS", "SALES",
                        "MARKETING", "FINANCE", "OPERATIONS")
                    .requestMatchers("/clientes/**").hasAuthority("CLIENTE")
                    //.anyRequest().permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin(clientesLogin -> clientesLogin
                .loginPage("/login/login")
                .failureForwardUrl("/login/login?error")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/doLogout")
                .logoutSuccessUrl("/login/login?logout")
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(customLogoutSuccessHandler)
                .clearAuthentication(true)
                .invalidateHttpSession(true))
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
