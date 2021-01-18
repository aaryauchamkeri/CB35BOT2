package com.aarya.main;

import java.awt.Color;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;

import com.aarya.controllers.MainController;
import com.aarya.model.DB;
import com.aarya.model.LavaplayerAudioSource;
import com.aarya.model.MemerThing;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

import org.javacord.api.audio.AudioSource;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class MessageListener implements MessageCreateListener {

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        MainController.messageCount++;
        if (event.getMessageContent().equalsIgnoreCase("!meme")) {
            addPoint(event.getMessageAuthor().getIdAsString());
            try {
                MemerThing.sendMeme(event);
            } catch (Exception e) {
                event.getServerTextChannel().get()
                        .sendMessage(event.getMessageAuthor().asUser().get().getMentionTag() + " an error occured.");
            }
        } else if (event.getMessageContent().toLowerCase().equals("!schedule")) {
            addPoint(event.getMessageAuthor().getIdAsString());
            EmbedBuilder eb = new EmbedBuilder().setTitle("8-Bit's schedule for streaming")
                    .addField("Wednesday", "5-7pst").addField("Saturday", "5-7pst").addField("Sunday", "5-7pst")
                    .setColor(Color.PINK.darker());
            event.getChannel().sendMessage(eb);
        } else if (event.getMessageContent().startsWith("!rick ")) {
            if (DB.dataBase.get(event.getMessageAuthor().asUser().get().getIdAsString()).getRrs()) {
                if(event.getMessage().isPrivateMessage()){
                    StringTokenizer stk = new StringTokenizer(event.getMessageContent());
                    if (stk.countTokens() == 2) {
                        stk.nextToken();
                        String user = stk.nextToken();
                        if (!DB.dataBase.containsKey(user)) {
                            event.getChannel().sendMessage("Enter a valid id please.");
                        } else {
                            ServerVoiceChannel svc = Cb35BotApplication.mine.getVoiceChannelById("795478591852642309")
                                    .get();
                            svc.connect().thenAccept(audioConnection -> {
                                AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
                                playerManager.registerSourceManager(new YoutubeAudioSourceManager());
                                AudioPlayer player = playerManager.createPlayer();
                
                                AudioSource source = new LavaplayerAudioSource(Cb35BotApplication.api, player);
                                audioConnection.setAudioSource(source);
                
                                player.addListener(new AudioEventAdapter() {
                
                                    @Override
                                    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
                                        audioConnection.close();
                                    }
                
                                    @Override
                                    public void onTrackException(AudioPlayer player, AudioTrack track, FriendlyException exception) {
                
                                    }
                
                                });
                
                                int x = (int) (Math.random() * 10) + 1;
                
                                System.out.println(x);
                
                                playerManager.loadItem(x == 1 ? "https://www.youtube.com/watch?v=avCWDDox1nE"
                                        : "https://www.youtube.com/watch?v=dQw4w9WgXcQ", new AudioLoadResultHandler() {
                
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
                            try {
                                User toMove = Cb35BotApplication.api.getUserById(user).get();
                                System.out.println(Cb35BotApplication.mine.isMember(toMove));
                                Cb35BotApplication.mine.moveUser(toMove, svc).get();
                            } catch (InterruptedException | ExecutionException e) {
                                e.printStackTrace();
                            }
                        }
                    } 
                } else {
                    event.getMessage().delete();
                    String mention = event.getMessageAuthor().asUser().get().getMentionTag();
                    event.getChannel().sendMessage(mention + " why would you let them know idiot.");
                }
            } else {
                event.getChannel().sendMessage("Not enough points for rick roll!");
            }
        } else if(event.getMessageContent().equals("!points")){
            long points = DB.dataBase.get(event.getMessageAuthor().getIdAsString()).getPoints();
            event.getChannel().sendMessage(event.getMessageAuthor().asUser().get().getMentionTag() + " you have " + points + " points.");
        } else {
            addPoint(event.getMessageAuthor().getIdAsString());
        }
    }

    public void addPoint(String id){
        DB.dataBase.get(id).setPoints(1);
    }
}