package br.edu.ufabc.ufabfood.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

   @Autowired
   private RepositoryAuthenticationProvider authProvider;

   @Override
   protected void configure(HttpSecurity httpSecurity) throws Exception {
       
      httpSecurity.cors().and().csrf().disable().authorizeRequests()
         .antMatchers(HttpMethod.POST, "/login").permitAll()
         .anyRequest().authenticated().and()
         // filtra requisições de login
			.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
         UsernamePasswordAuthenticationFilter.class)
         // filtra outras requisições para verificar a presença do JWT no header
         .addFilterBefore(new JWTAuthenticationFilter(),
         UsernamePasswordAuthenticationFilter.class)
         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
         .and().headers().cacheControl();
   } 

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.authenticationProvider(this.authProvider);
   }

   @Bean
   public CorsFilter corsFilter() {
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      CorsConfiguration config = new CorsConfiguration();
      config.setAllowCredentials(true);
      config.addAllowedOrigin("*");
      config.addAllowedHeader("*");
      config.addAllowedMethod(HttpMethod.GET);
      config.addAllowedMethod(HttpMethod.POST);
      config.addAllowedMethod(HttpMethod.PUT);
      config.addAllowedMethod(HttpMethod.DELETE);
      config.addAllowedMethod(HttpMethod.OPTIONS);
      config.addExposedHeader("Authorization");
      config.addExposedHeader("Username");
      source.registerCorsConfiguration("/**", config);
      return new CorsFilter(source);
   }
    
}