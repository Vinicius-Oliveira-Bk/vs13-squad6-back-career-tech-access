package br.com.dbc.vemser.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Md4PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final TokenService tokenService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable().and()
                .cors().and()
                .csrf().disable()
                .authorizeHttpRequests((authz) -> authz
                        .antMatchers("/auth", "/", "/usuario").permitAll()   // Para liberar o acesso de tudo: "/**"
                        .antMatchers("/auth/usuario-logado").authenticated()
                        //AGENDA
                        .antMatchers(HttpMethod.GET, "/agenda").hasRole("ADMIN")
                        .antMatchers(HttpMethod.GET, "/agenda/profissional").hasAnyRole("PROFISSIONAL", "ADMIN")
                        .antMatchers(HttpMethod.GET, "/agenda/cliente").hasAnyRole("CLIENTE", "ADMIN")
                        .antMatchers(HttpMethod.GET, "/agenda/relatorio").hasAnyRole("PROFISSIONAL", "ADMIN")
                        .antMatchers(HttpMethod.POST, "/agenda").hasAnyRole("PROFISSIONAL", "ADMIN")
                        .antMatchers(HttpMethod.PUT, "/agenda/cancelar/**").hasAnyRole("PROFISSIONAL", "CLIENTE", "ADMIN")
                        .antMatchers(HttpMethod.PUT, "/agenda/agendar/**").hasAnyRole("CLIENTE", "ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/agenda").hasAnyRole("PROFISSIONAL", "ADMIN")
                        .antMatchers(HttpMethod.PUT, "/agenda/alterarHorario").hasAnyRole("PROFISSIONAL", "ADMIN")
                        .antMatchers(HttpMethod.PUT, "/agenda/marcar-presente").hasAnyRole("PROFISSIONAL", "ADMIN")
                        //CLIENTE
                        .antMatchers(HttpMethod.DELETE,"/cliente").hasRole("ADMIN")
                        .antMatchers(HttpMethod.GET,"/cliente/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.POST,"/cliente/criar-cliente").hasAnyRole("USUARIO", "ADMIN")
                        .antMatchers(HttpMethod.POST,"/cliente/criar-cliente-admin/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.PUT,"/cliente").hasAnyRole("CLIENTE", "ADMIN")
                        //CONTATO
                        .antMatchers(HttpMethod.DELETE,"/contato").hasAnyRole("USUARIO", "ADMIN")
                        .antMatchers(HttpMethod.GET,"/contato/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.POST,"/contato").hasAnyRole("USUARIO", "ADMIN")
                        .antMatchers(HttpMethod.PUT,"/contato").hasAnyRole("USUARIO", "ADMIN")
                        //ENDERECO
                        .antMatchers(HttpMethod.DELETE,"/endereco").hasAnyRole("USUARIO", "ADMIN")
                        .antMatchers(HttpMethod.GET,"/endereco/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.POST,"/endereco").hasAnyRole("USUARIO", "ADMIN")
                        .antMatchers(HttpMethod.PUT,"/endereco").hasAnyRole("USUARIO", "ADMIN")
                        //MENTOR
                        .antMatchers(HttpMethod.DELETE,"/profissional-mentor").hasRole("ADMIN")
                        .antMatchers(HttpMethod.GET,"/profissional-mentor/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.POST,"/profissional-mentor/criar-mentor").hasAnyRole("USUARIO", "ADMIN")
                        .antMatchers(HttpMethod.POST,"/profissional-mentor/criar-mentor-admin/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.PUT,"/profissional-mentor").hasAnyRole("PROFISSIONAL", "ADMIN")
                        //USUARIO
                        .antMatchers(HttpMethod.DELETE,"/usuario").hasRole("ADMIN")
                        .antMatchers(HttpMethod.GET,"/usuario/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.POST,"/usuario/create-admin").hasRole("ADMIN")
                        .antMatchers(HttpMethod.PUT,"/usuario").hasAnyRole("USUARIO", "ADMIN")
                        .antMatchers(HttpMethod.PUT,"/usuario/alterar-senha").hasAnyRole("USUARIO", "ADMIN")
                        .antMatchers(HttpMethod.PUT,"/usuario/ativar-inativar").hasAnyRole("USUARIO", "ADMIN")
                        .antMatchers(HttpMethod.PUT,"/usuario/ativar-inativar-admin").hasRole( "ADMIN")

                        .antMatchers("/**").hasRole("ADMIN")
                        .anyRequest().denyAll()
                );
        http.addFilterBefore(new TokenAuthenticationFilter(tokenService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/v3/api-docs",
                "/v3/api-docs/**",
                "/swagger-resources/**",
                "/swagger-ui/**");
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .exposedHeaders("Authorization");
            }
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new Md4PasswordEncoder();
    }

}
