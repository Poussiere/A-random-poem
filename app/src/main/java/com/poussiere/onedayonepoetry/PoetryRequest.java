package com.poussiere.onedayonepoetry;

import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PoetryRequest {

    public static final String BASE_URL = "http://poetrydb.org";

    //get author, lines and titles of all the titles
    @GET("//title/all/author,title,lines")
    List<Poetry> listPoetry();

}
