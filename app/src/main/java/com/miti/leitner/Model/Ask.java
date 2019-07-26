package com.miti.leitner.Model;

public class Ask
{
    int id;
    String english_word,persian_word;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnglish_word() {
        return english_word;
    }

    public void setEnglish_word(String english_word) {
        this.english_word = english_word;
    }

    public String getPersian_word() {
        return persian_word;
    }

    public void setPersian_word(String persian_word) {
        this.persian_word = persian_word;
    }
}
