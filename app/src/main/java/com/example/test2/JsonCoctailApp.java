package com.example.test2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonCoctailApp {

    @GET("search.php")
    Call<PostList> getPosts(@Query("s") String name);

}
