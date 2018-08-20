package com.aarya.controllers;

import com.aarya.main.Cb35BotApplication;
import com.aarya.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/main")
public class MainController {

    private final DiscordApi api;
    public static int messageCount = 500;
    private final boolean access = true;

    @Autowired
    public MainController(DiscordApi api){
        this.api = api;
    }


    @RequestMapping("/")
    public void getHome(HttpServletResponse res) throws IOException{
        res.sendRedirect("/index.html");
    }

    @RequestMapping("/sendLiveUpdate")
    public void sendLiveUpdate(){
        ServerTextChannel ch = Cb35BotApplication.mine.getTextChannelById("795485817714769970").get();
        ch.sendMessage(Cb35BotApplication.mine.getEveryoneRole().getMentionTag() + " I'm live");
        ch.sendMessage("https://www.twitch.tv/sb808bit");
    }

    @RequestMapping(value = "/getUserInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public String userInfo(@RequestHeader String sessionId) throws Exception{
        ObjectMapper om = new ObjectMapper();
        UserInfo user = AuthorizationController.sessions.get(sessionId);
        return om.writeValueAsString(user);
    }

    @RequestMapping("/checkSession")
    public void check(){ }

    @GetMapping("/getMessages")
    public List<JsonMessage> getMessages(HttpServletRequest req, HttpServletResponse res) throws ServerException, ExecutionException, InterruptedException {
        if(access){
            ServerTextChannel channel = Cb35BotApplication.mine.getTextChannelById(req.getHeader("channelId")).get();
            MessageSet set = channel.getMessages(messageCount).get();
            List<JsonMessage> messages = new ArrayList<>();
            for(Message m : set){
                if(m.getEmbeds().size() > 0){
                    org.javacord.api.entity.user.User u = m.getAuthor().asUser().get();
                    messages.add(new JsonMessage(m.getAuthor().getName(), "EMBED", u.getAvatar().getUrl().toExternalForm()));
                } else if(!m.getContent().equals("")){
                    JsonMessage message = new JsonMessage(m.getAuthor().getName(), m.getContent(), m.getAuthor().asUser().get().getAvatar().getUrl().toExternalForm());
                    messages.add(message);
                } else if(m.getAttachments().size() > 0){
                    if(m.getAttachments().get(0).isImage()){
                        JsonMessage message = new JsonMessage(m.getAuthor().getName(), m.getAttachments().get(0).getUrl().toExternalForm(), m.getAuthor().asUser().get().getAvatar().getUrl().toExternalForm());
                        messages.add(message);
                    }
                } else {
                    JsonMessage message = new JsonMessage(m.getAuthor().getName(), String.format("%s joined server", m.getAuthor().getName()), m.getAuthor().asUser().get().getAvatar().getUrl().toExternalForm());
                    messages.add(message);
                }
            }
            return messages;
        } else {
            throw new ServerException("NOT PRESENT");
        }

    }

    @GetMapping("/textChannels")
    public List<TextChannelInfo> channels() throws ServerException{
        if(access){
            List<TextChannelInfo> listOfChannels = new ArrayList<>();
            List<ServerTextChannel> channels = Cb35BotApplication.mine.getTextChannels();
            for(ServerTextChannel s : channels){
                listOfChannels.add(new TextChannelInfo(s.getIdAsString(), s.getName()));
            }
            return listOfChannels;
        } else {
            throw new ServerException("NOT PRESENT");
        }
    }

    @GetMapping("/getUsers")
    public List<User> getUsers() throws ServerException{
        if(access){
            List<User> listModeled = new ArrayList<>();
            Set<String> set = DB.dataBase.keySet();
            for(String name : set){
                listModeled.add(DB.dataBase.get(name));
            }
            return listModeled;
        } else{
            throw new ServerException("NOT PRESENT");
        }
    }

    @PostMapping("/sendMessage")
    public void sendMessage(@RequestBody String value, HttpServletRequest req) throws ServerException{
        if(access){
            Optional<ServerTextChannel> channel = Cb35BotApplication.mine.getTextChannelById(req.getHeader("server").trim());
            if(channel.isEmpty()){
                // do nothing
            } else{
                channel.get().sendMessage(value);
            }
        } else{
            throw new ServerException("NOT PRESENT");
        }
    }

    @PostMapping("/banUser")
    public void banUser(@RequestBody String s) throws ServerException, InterruptedException, ExecutionException {
        if(access){
            Cb35BotApplication.mine.banUser(this.api.getUserById(s).get());
        } else {
            throw new ServerException("NOT PRESENT");
        }
    }
}
