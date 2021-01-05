// package com.aarya.model;

// import com.aarya.main.Cb35BotApplication;
// import org.javacord.api.DiscordApi;
// import org.javacord.api.entity.server.Server;

// import java.io.*;
// import java.util.*;

// public class DB{

//     public static Map<String, User> dataBase;

//     static{
//         System.out.println("Exists");
//         dataBase = initializeList();
//     }

//     private static Map<String, User> initializeList(){
//         Map<String, User> returned = new HashMap<>();
//         DiscordApi api = Cb35BotApplication.api;
//         Optional<Server> serverContainer = api.getServerById("775508449907048480");
//         if(serverContainer.isPresent()){
//             System.out.println("running");
//             Server server = serverContainer.get();
//             ArrayList<org.javacord.api.entity.user.User> members =
//                     new ArrayList<>(server.getMembers());
//             for(org.javacord.api.entity.user.User u : members){
//                 System.out.println(u.getName());
//                 User entity = new User(0, u.getIdAsString(), u.getName());
//                 returned.put(u.getName(), entity);
//             }
//             return returned;
//         } else {
//             throw new NullPointerException("SERVER DOESN'T EXIST");
//         }
//     }

//     public DB(){
//     }

//     public static synchronized void executeQuery(User user){
//         if(!dataBase.containsKey(user.getName())){
//             dataBase.put(user.getName(), user);
//         }
//     }
// }
