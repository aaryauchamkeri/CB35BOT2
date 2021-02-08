package com.aarya.controllers;

import com.aarya.main.Cb35BotApplication;
import com.aarya.model.JsonMessage;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageSet;
import org.javacord.api.entity.message.embed.Embed;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/resources")
public class ResourceController {

    private final ServerTextChannel stx = Cb35BotApplication.mine.getTextChannelById("803082527991398450").orElse(null);

    @GetMapping(value = "/images", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JsonMessage> getImageIds(){
        List<JsonMessage> imageIds = new ArrayList<>();
        MessageSet ms;
        try {
            ms = stx.getMessages(820000000).get();
            for(Message m : ms){
                if(m.getEmbeds().size() > 0){
                    if(m.getAuthor().getIdAsString().equalsIgnoreCase("795488321974304791")) {
                        Embed embed = m.getEmbeds().get(0);
                        try {
                            String author = embed.getAuthor().get().getName();
                            String content = embed.getDescription().get();
                            String imageUrl = embed.getImage().get().getUrl().toExternalForm();
                            JsonMessage message = new JsonMessage(author, content, imageUrl);
                            imageIds.add(message);
                        } catch (NullPointerException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch(InterruptedException | ExecutionException | NullPointerException e) {
            e.printStackTrace();
        }
        return imageIds;
    }

    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void upload(@RequestParam MultipartFile file, @RequestParam String id, @RequestParam String description) throws IOException {
        User messageAuthor = Cb35BotApplication.mine.getMemberById(id).orElse(null);
        try {
            EmbedBuilder builder = new EmbedBuilder()
                    .setAuthor(messageAuthor)
                    .setDescription(description)
                    .setImage(file.getBytes());
            stx.sendMessage(builder);
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}
