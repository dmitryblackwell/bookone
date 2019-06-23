package com.blackwell.api;

import com.blackwell.api.model.GoogleBookRespond;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BookAPI {
    @GET("/books/v1/volumes?langRestrict=en&printType=books&maxResults=40")
    public Call<GoogleBookRespond> searchBooks(@Query("q") String searchText, @Query("startIndex") Integer startIndex);
}
