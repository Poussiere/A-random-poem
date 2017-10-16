package com.poussiere.onedayonepoetry;

/**
 * Created by poussiere on 16/10/17.
 */

public class Poetry {

    private String title;
    private String author;
    private String lines;

    public Poetry (String t, String a, String l ){
        title=t;
        author=a;
        lines=l;
    }

    public String getTitle(){
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getLines(){
        return lines;
    }


}
