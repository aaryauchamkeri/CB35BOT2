package com.aarya.main;



import com.aarya.model.DB;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
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

     @Bean(name = "db")
     public DB getDB(){
         return new DB();
     }

    @Bean(name = "api")
    public DiscordApi getApi(){
         String s = "Myj0MCf3LyHwNSb/Ly@/Myjw-W^JFNv-Ery50L1dbKJqGPkR733phEgsW3j";
         String toEnter = "";
         for(int i = 0; i < s.length(); i++){
             toEnter += (char)(s.charAt(i)+1);
         }
         DiscordApi api = new DiscordApiBuilder().setToken(toEnter).setAllIntents().login().join();
         return api;
    }

}