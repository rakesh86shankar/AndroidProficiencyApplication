package com.example.demo.demoapplication.network;

import com.example.demo.demoapplication.modal.NewsList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("s/2iodh4vg0eortkl/facts.json")
    Call<NewsList>  getNewsList();


}
