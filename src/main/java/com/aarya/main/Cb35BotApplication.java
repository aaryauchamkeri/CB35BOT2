package com.aarya.main;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.server.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Cb35BotApplication {

	public static DiscordApi api;
	public static Server mine;

	static{
		api = new DiscordApiBuilder().setToken("Nzk1NDg4MzIxOTc0MzA0Nzkx.X_KGOw.eavdH-uDi6Usewgw75kmaM_6dz4").setAllIntents().login().join();
	}

	public static void main(String[] args) {
		SpringApplication.run(Cb35BotApplication.class, args);
		ApplicationContext appContext = new AnnotationConfigApplicationContext(Beans.class);
		Cb35BotApplication.api.addMessageCreateListener(new MessageListener());
	}
}