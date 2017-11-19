package com.poussiere.onedayonepoetry;

import java.util.ArrayList;

/**
 * Created by poussiere on 16/10/17.
 */

public class Poetry {

    private String title;
    private String author;
    private ArrayList<String> lines;

    public Poetry(String t, String a, ArrayList<String> l) {
        title = t;
        author = a;
        lines = l;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public ArrayList<String> getLinesArray() {
        return lines;
    }

    public String getLinesString() {
        String linesString = "";
        for (String s : lines) {
            linesString = linesString + s + "\n";
        }

        return linesString;
    }

    // get a string which contains Author, title and lines
    public String getAllStrings(){
        String allStrings = "\n"+title + "\n\n"+author+"\n"+"\n";

        for (String s : lines) {
            allStrings = allStrings + s +"\n";
        }

        return allStrings;
    }

}
