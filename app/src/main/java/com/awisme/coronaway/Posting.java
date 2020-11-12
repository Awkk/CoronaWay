package com.awisme.coronaway;

public class Posting {
    String date;
    String name;
    String email;
    Boolean pressed;

    public Posting() {}

    public Posting(String date, String name, String email, Boolean pressed){
        this.date = date;
        this.name = name;
        this.email = email;
        this.pressed=pressed;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getPressed() {
        return pressed;
    }

    public void setPressed(Boolean pressed) {
        this.pressed = pressed;
    }
}
