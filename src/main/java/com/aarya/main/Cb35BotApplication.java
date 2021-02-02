package com.aarya.main;

import com.aarya.model.DB;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.server.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Map;

@Configuration
@EnableAutoConfiguration
@Import(Beans.class)
public class Cb35BotApplication {

	public static DiscordApi api;
	public static Server mine;

	static{
		Map<String, String> envars = System.getenv();
		String key = envars.get("BOTID");
		if(key == null){
			throw new RuntimeException("NEED BOT ID ENVIRONMENT VARIABLE");
		}
		api = new DiscordApiBuilder().setToken(key).setAllIntents().login().join();
		mine = api.getServerById("795478591852642304").get();
		DB.initializeList();

	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Cb35BotApplication.class, args);
		Cb35BotApplication.api.addMessageCreateListener(new MessageListener());
	}
}