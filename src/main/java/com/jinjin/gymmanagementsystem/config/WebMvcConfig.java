package com.jinjin.gymmanagementsystem.config;

import com.jinjin.gymmanagementsystem.security.SessionAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author shenjin
 * @date 2026/4/26
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final SessionAuthInterceptor sessionAuthInterceptor;

    public WebMvcConfig(SessionAuthInterceptor sessionAuthInterceptor) {
        this.sessionAuthInterceptor = sessionAuthInterceptor;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionAuthInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns(
                        "/api/userLogin",
                        "/api/adminLogin");
    }
}
