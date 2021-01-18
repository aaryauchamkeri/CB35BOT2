package com.aarya.main;

import com.aarya.model.DB;
import com.aarya.model.User;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.server.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class Cb35BotApplication {
	public static DiscordApi api;
	public static Server mine;

	static{

		String s = "Myj0MCf3LyHwNSb/Ly@/Myjw-W^JFNv-Ery50L1dbKJqGPkR733phEgsW3j";
		String toEnter = "";
		for(int i = 0; i < s.length(); i++){
			toEnter += (char)(s.charAt(i)+1);
		}
		api = new DiscordApiBuilder().setToken(toEnter).setAllIntents().login().join();
	}

	public static void main(String[] args) {
		SpringApplication.run(Cb35BotApplication.class, args);
		ApplicationContext appContext = new AnnotationConfigApplicationContext(Beans.class);
		Cb35BotApplication.api.addMessageCreateListener(new MessageListener());
	}
}