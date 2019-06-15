package com.blackwell.api;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.blackwell.api.model.GoogleBookRespond;
import com.blackwell.api.model.ItemAPIModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


import okhttp3.OkHttpClient;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
public class BookAPIServiceImpl implements BookAPIService {

    @Value("${googleAPI.key}")
    private String googleAPIKey;

    @Override
    public List<ItemAPIModel> searchBookList(String searchText, Integer startIndex) {

        List<ItemAPIModel> itemsAPIList = null;
        itemsAPIList = this.searchAPIItemsList(searchText, startIndex);

        return itemsAPIList;
    }

    private List<ItemAPIModel> searchAPIItemsList(String searchText, Integer startIndex){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        BookAPI googleBookApi = retrofit.create(BookAPI.class);
        Call<GoogleBookRespond> callSync = googleBookApi.searchBooks(searchText, startIndex);

        GoogleBookRespond respond = null;
        try {
            Response<GoogleBookRespond> response = callSync.execute();
            respond = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return respond == null ? Collections.EMPTY_LIST : respond.getItems();


    }
}
