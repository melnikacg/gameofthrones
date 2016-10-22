package org.melnikacg.testapp.retrofit;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface ThronesApi {

    @GET("/houses/")
    List<HouseRetrofit> houseByName(@Query("name") String apiKey);

    @GET("/characters/{id}")
    CharacterRetrofit characterById(@Path("id") String value);


}
