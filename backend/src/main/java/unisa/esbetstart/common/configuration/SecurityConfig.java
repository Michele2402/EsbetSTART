package unisa.esbetstart.common.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final String[] unauthorizedPaths = {
            "/users/login", "/users/register", "/games/get-all",
            "/competitions/get-all-by-game/**", "/events/get-all-by-competition/**", "/users/get/**"
    };

    private final String[] eventManagerPaths = {
            "/events/add", "/events/update", "/events/odds/update", "/events/remove/**", "/events/end",
            "/competitions/add", "/competitions/update", "/competitions/remove/**",
            "/games/add", "/games/update", "/games/remove"
    };

    private final String[] gamblerPaths = {
            "/slip/**", "/slip/place-bet",  "/tickets/open", "/tickets/getByGamblerEmail", "/transactions/show",
            "/bets/show", "/users/transaction/create", "/users/email", "/users/update",
            "/users/balance/**"
    };

    private final String [] transactionManagerPaths = {
            "/transactions/showAll", "/offers/add", "/offers/update", "/offers/remove/**"
    };

    private final String[] customerServiceOperatorPaths = {
            "/tickets/accept", "/tickets/getByOperatorEmail"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        (requests) -> requests
                                .requestMatchers("/**").permitAll()
                                .requestMatchers(gamblerPaths).hasAuthority("GAMBLER")
                                .requestMatchers(eventManagerPaths).hasAuthority("EVENT_MANAGER")
                                .requestMatchers("tickets/sendMessage", "tickets/readMessage").hasAnyAuthority("GAMBLER", "CUSTOMER_SERVICE_OPERATOR")
                                .requestMatchers(transactionManagerPaths).hasAuthority("TRANSACTION_MANAGER")
                                .requestMatchers(customerServiceOperatorPaths).hasAuthority("CUSTOMER_SERVICE_OPERATOR")
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(14);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
