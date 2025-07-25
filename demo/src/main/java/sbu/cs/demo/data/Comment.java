package sbu.cs.demo.data;

import java.util.Date;

public class Comment {
    private String username;
    private String text;
    private Date date;

    public Comment(String username, String text, Date date) {
        this.username = username;
        this.text = text;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        String s = "[" + (date.getYear() + 1900) + "/" + date.getDate() + "/" + date.getMonth() + " ," + date.getHours() + " : " + date.getMinutes() + "] " + username + ": \n" + text;
        return s;
    }
}
