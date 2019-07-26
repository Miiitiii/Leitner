package com.miti.leitner.Model;

public class NewWord
{

    String PersianMeaning,EnglishMeaning,Difficult;

    public NewWord()
    {
    }

    public NewWord(String persianMeaning, String englishMeaning, String difficult) {
        PersianMeaning = persianMeaning;
        EnglishMeaning = englishMeaning;
        Difficult = difficult;
    }

    public String getPersianMeaning() {
        return PersianMeaning;
    }

    public void setPersianMeaning(String persianMeaning) {
        PersianMeaning = persianMeaning;
    }

    public String getEnglishMeaning() {
        return EnglishMeaning;
    }

    public void setEnglishMeaning(String englishMeaning) {
        EnglishMeaning = englishMeaning;
    }

    public String getDifficult() {
        return Difficult;
    }

    public void setDifficult(String difficult) {
        Difficult = difficult;
    }
}
