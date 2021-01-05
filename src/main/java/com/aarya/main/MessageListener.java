package com.aarya.main;

import com.aarya.controllers.MainController;
// import com.aarya.model.DB;
import com.aarya.model.MemerThing;

// import org.javacord.api.entity.message.embed.EmbedBuilder;
// import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

// import java.awt.*;

public class MessageListener implements MessageCreateListener {

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        MainController.messageCount++;
        
        if(event.getMessageContent().toLowerCase().equals("!meme")){
            try {
                MemerThing.sendMeme(event);
            } catch (Exception e) {
                event.getServerTextChannel().get().sendMessage(
                        event.getMessageAuthor().asUser().get().getMentionTag() + " an error occured.");
            }
        }

    }

}