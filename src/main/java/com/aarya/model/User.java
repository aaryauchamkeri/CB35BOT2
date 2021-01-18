package com.aarya.model;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

import com.aarya.main.Cb35BotApplication;

public class User implements Comparable<User> {

    private long points;
    private String id;
    private String name;
    private final int needed = 100;

    public User() {
        points = 0;
        id = "";
        name = "";
    }

    public User(long points, String id, String name) {
        this.points = points;
        this.id = id;
        this.name = name;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points += points;
        if (this.points < 0) {
            this.points = 0;
        } else if(points > 0 && points %100 == 0) {
            try {
                org.javacord.api.entity.user.User justGot = Cb35BotApplication.api.getUserById(this.id).get();
                justGot.sendMessage("Hey! you just got 1 rick roll");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean getRrs(){
        if(points >= needed || this.id.equals("557706668486557736")){
            if(this.id.equals("557706668486557736")){
                return true;
            } else {
                points -= needed;
                return true;
            }
        } else {
            return false;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(User user) {
        if(user.getName().equals(this.name) && user.getId().equals(this.id)) {
            return 1;
        }
        else
            return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        
        return Objects.equals(id, user.id) &&
               Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash (id, name);
    }
}
