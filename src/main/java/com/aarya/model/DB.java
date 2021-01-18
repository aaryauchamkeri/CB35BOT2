package com.aarya.model;

import com.aarya.main.Cb35BotApplication;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.server.Server;

import java.util.*;

public class DB{

    public static Map<String, User> dataBase = new HashMap<>();

    public static void initializeList(){
        Server server = Cb35BotApplication.mine;
        if(server != null){
            System.out.println("running");
            ArrayList<org.javacord.api.entity.user.User> members =
                    new ArrayList<>(server.getMembers());
            for(org.javacord.api.entity.user.User u : members){
                System.out.println(u.getName());
                User entity = new User(0, u.getIdAsString(), u.getName());
                dataBase.put(u.getIdAsString(), entity);
            }
        } else {
            throw new NullPointerException("SERVER DOESN'T EXIST");
        }
    }

    public DB(){
    }

    public static synchronized void executeQuery(User user){
        if(!dataBase.containsKey(user.getName())){
            dataBase.put(user.getName(), user);
        }
    }
}
