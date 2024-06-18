package edu.upc.dsa.kebabsimulator_android.models;

import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {
    // Ensure the base URL is correct and ends with a '/'

    String BASE_URL = "http://147.83.7.203:80/dsaApp/";
    //String BASE_URL = "http://10.0.2.2:8080/dsaApp/";

    @GET("abilities/getAbilities")  // Ensure there is no leading '/' if the base URL ends with one
    Call<List<Ability>> weapons();

    @GET("players/abilities/{userName}")
    Call<List<Ability>> getInventario(@Path("userName") String userName);

    @GET("players")
    Call<List<Player>> users();

    @GET("players/{userName}")
    Call<Player> getPlayerByName(@Path("userName") String userName);

    @POST("players")
    Call<Player> addUser(@Body Player player);

    @GET("missions/getMissions")  // Ensure there is no leading '/' if the base URL ends with one
    Call<List<Mission>> missions();

    @GET("enemies/getEnemies")  // Ensure there is no leading '/' if the base URL ends with one
    Call<List<Enemy>> enemies();

    // Static initialization block to ensure Retrofit is set up correctly
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
