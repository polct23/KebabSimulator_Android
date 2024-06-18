package edu.upc.dsa.kebabsimulator_android;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.upc.dsa.kebabsimulator_android.models.API;
import edu.upc.dsa.kebabsimulator_android.models.Ability;
import edu.upc.dsa.kebabsimulator_android.models.Player;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StoreAdapter adapter;

    private String userName;

    private Player currentplayer;

    private final String TAG = StoreActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_store);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        userName = getIntent().getStringExtra("username");
        recyclerView = (RecyclerView) findViewById(R.id.myRecylcerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setHasFixedSize(true);

        doApiCallUser();
        adapter = new StoreAdapter(new ArrayList<Ability>(),currentplayer);
        recyclerView.setAdapter(adapter);

        try {
            doApiCall();
        } catch (Exception e) {
            Log.w(TAG,"excp", e);
            throw new RuntimeException(e);
        }

    }
    private void doApiCall() {
        API apiService = API.retrofit.create(API.class);
        Call<List<Ability>> call = apiService.weapons();

        call.enqueue(new Callback<List<Ability>>() {
            @Override
            public void onResponse(Call<List<Ability>> call, Response<List<Ability>> response) {
                int code = response.code();
                List<Ability> abilityList = response.body();
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setData(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Log.w(TAG, "Respuesta no exitosa o cuerpo nulo, HTTP " + response.code());
                    Toast.makeText(StoreActivity.this, "Failed to retrieve data. HTTP code: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Ability>> call, Throwable t) {
                Log.e(TAG, "Error in Retrofit: " + t.toString());
                Toast.makeText(StoreActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void doApiCallUser() {
        API apiService = API.retrofit.create(API.class);
        retrofit2.Call<Player> call = apiService.getPlayerByName(userName);


        call.enqueue(new retrofit2.Callback<Player>() {
            @Override
            public void onResponse(retrofit2.Call<Player> call, retrofit2.Response<Player> response) {

                currentplayer = response.body();

            }

            @Override
            public void onFailure(retrofit2.Call<Player> call, Throwable t) {
                Log.e("FAIL(onFailure)", "Error in Retrofit: " + t.toString());

            }
        });
    }


}