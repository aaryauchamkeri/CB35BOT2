package com.aarya.main;



import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("com.aarya")
@Scope("prototype")
public class Beans implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        
    }

    // @Bean(name = "db")
    // public DB getDB(){
    //     return new DB();
    // }

}