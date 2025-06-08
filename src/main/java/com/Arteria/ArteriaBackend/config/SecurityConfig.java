package com.Arteria.ArteriaBackend.config;


import com.Arteria.ArteriaBackend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    @Lazy
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    @Lazy
    private UsuarioService userService;

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/auth/login", "/auth/register").permitAll()
//                        .anyRequest().authenticated())
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Deshabilita CSRF para APIs REST
                .authorizeHttpRequests(authorize -> authorize
                        // Rutas que no requieren autenticación (permitAll):
                        .requestMatchers(
                                "/usuarios/registro", // Para que los usuarios puedan registrarse
                                "/usuarios/login"     // Para que los usuarios puedan iniciar sesión
                        ).permitAll()

                        // Permitir acceso de lectura (GET) a todos para obras, categorías, imágenes y usuarios:
                        // Esto es para que cualquiera pueda ver los listados o detalles de estos recursos.
                        .requestMatchers(HttpMethod.GET, "/obras/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/categoria/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/imagenes/**").permitAll()

                        // Rutas que requieren el rol de ADMIN para operaciones de modificación:
                        // Solo un usuario con el rol "ADMIN" podrá realizar estas operaciones.
                        // HttpMethod.POST: Para crear nuevos recursos.
                        .requestMatchers(HttpMethod.POST, "/obras/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/categoria/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/imagenes/**").hasRole("ADMIN")
                        // HttpMethod.PUT: Para actualizar recursos existentes.
                        .requestMatchers(HttpMethod.PUT, "/obras/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/categoria/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/imagenes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/usuarios/**").hasRole("ADMIN") // Para editar usuarios
                        // HttpMethod.DELETE: Para eliminar recursos.
                        .requestMatchers(HttpMethod.DELETE, "/obras/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/categoria/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/imagenes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/usuarios/**").hasRole("ADMIN") // Para eliminar usuarios

                        .anyRequest().authenticated() // Cualquier otra solicitud (que no sea GET público o ADMIN específico) requiere autenticación.
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Esencial para JWT: no usar sesiones
                );

        // Añade nuestro filtro JWT antes del filtro de autenticación estándar de Spring
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        // Este bean es necesario para que el AuthenticationManager esté disponible para inyectar en tu controlador
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Define el bean para el cifrado de contraseñas
        return new BCryptPasswordEncoder();
    }
}