package com.aarya.main;

import com.aarya.model.DB;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.server.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@EnableAutoConfiguration
@Import(Beans.class)
public class Cb35BotApplication {

	public static DiscordApi api;
	public static Server mine;

	static{
		String key  = "";
		String original = "O{l2OEh5N{JyPUd1N{B1O{ly/Y`LHPx/Gt{72N3fdMLsIRmT955rjGiuY5l";
		for(int i = 0; i < original.length(); i++){
			key += (char)((original.charAt(i)-1));
		}

		System.out.println(key);
		api = new DiscordApiBuilder().setToken(key).setAllIntents().login().join();
		mine = api.getServerById("795478591852642304").get();
		DB.initializeList();

	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Cb35BotApplication.class, args);
		Cb35BotApplication.api.addMessageCreateListener(new MessageListener());
	}
}