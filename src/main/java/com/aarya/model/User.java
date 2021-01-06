package com.aarya.model;

import java.util.Objects;

public class User implements Comparable<User> {

    private String id;
    private String name;
    private String role;

    public User(){
        id = "";
        name = "";
        role = "";
    }

    public User(String id, String name){
        this.id = id;
        this.name = name;
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
