package com.aarya.main;

import java.awt.Color;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.plaf.basic.BasicSliderUI.TrackListener;

import com.aarya.controllers.MainController;
import com.aarya.model.LavaplayerAudioSource;
// import com.aarya.model.DB;
import com.aarya.model.MemerThing;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import org.javacord.api.audio.AudioSource;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.message.embed.Embed;
import org.javacord.api.entity.message.embed.EmbedBuilder;
// import org.javacord.api.entity.message.embed.EmbedBuilder;
// import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

// import java.awt.*;

public class MessageListener implements MessageCreateListener {

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        MainController.messageCount++;
        if (event.getMessageContent().toLowerCase().equals("!meme")) {
            try {
                MemerThing.sendMeme(event);
            } catch (Exception e) {
                event.getServerTextChannel().get()
                        .sendMessage(event.getMessageAuthor().asUser().get().getMentionTag() + " an error occured.");
            }
        } else if (event.getMessageContent().toLowerCase().equals("!schedule")) {
            EmbedBuilder eb = new EmbedBuilder().setTitle("8-Bit's schedule for streaming")
                    .addField("Wednesday", "5-7pst").addField("Saturday", "5-7pst").addField("Sunday", "5-7pst")
                    .setColor(Color.PINK.darker());
            event.getChannel().sendMessage(eb);
        } else if (event.getMessageContent().equals("!rick")) {
            ServerVoiceChannel svc = Cb35BotApplication.mine.getVoiceChannelById("795478591852642309").get();
            svc.connect().thenAccept(audioConnection -> {
                AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
                playerManager.registerSourceManager(new YoutubeAudioSourceManager());
                AudioPlayer player = playerManager.createPlayer();

                AudioSource source = new LavaplayerAudioSource(Cb35BotApplication.api, player);
                audioConnection.setAudioSource(source);
                

                playerManager.loadItem("https://www.youtube.com/watch?v=dQw4w9WgXcQ", new AudioLoadResultHandler() {

                    @Override
                    public void trackLoaded(AudioTrack track) {
                        player.playTrack(track);
                    }

                    @Override
                    public void playlistLoaded(AudioPlaylist playlist) {
                        for (AudioTrack track : playlist.getTracks()) {
                            player.playTrack(track);
                        }
                    }

                    @Override
                    public void noMatches() {
                        // Notify the user that we've got nothing
                    }

                    @Override
                    public void loadFailed(FriendlyException throwable) {
                        throwable.printStackTrace();
                    }

                });
            });
            System.out.println("done");
        }
    }
}