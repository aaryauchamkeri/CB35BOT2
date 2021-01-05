package com.aarya.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MemerThing {
    public static void sendMeme(MessageCreateEvent event) throws Exception{
        BufferedImage image = null;
        String jsonRecieved = "";
        URL url = new URL("https://meme-api.herokuapp.com/gimme");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        InputStream is = httpURLConnection.getInputStream();
        ObjectMapper om = new ObjectMapper();
        FetchedData fd = om.readValue(is, FetchedData.class);
        String[] memes = fd.getPreview();
        String memeUrl = memes[memes.length - 1];
        URL urlMeme = new URL(memeUrl);
        image = ImageIO.read(urlMeme);
        event.getChannel().sendMessage(
                new EmbedBuilder()
                        .setTitle(fd.getTitle())
                        .setAuthor("@" + fd.getAuthor() + " on reddit")
                        .setImage(image)
        );
    }
}