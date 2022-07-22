package com.coconet.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

   @Bean
   public CorsFilter corsFilter() {
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      CorsConfiguration config = new CorsConfiguration();
      config.setAllowCredentials(true);
      config.addAllowedOrigin("http://localhost:3000/");
      config.addAllowedHeader("*");
      config.addAllowedMethod("*");
      config.addExposedHeader("Jwt_Access_Token");
      config.addExposedHeader("Jwt_Refresh_Token");

      source.registerCorsConfiguration("/coconet/**", config);
      return new CorsFilter(source);
   }

}