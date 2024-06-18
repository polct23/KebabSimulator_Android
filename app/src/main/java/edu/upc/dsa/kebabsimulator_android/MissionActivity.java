package edu.upc.dsa.kebabsimulator_android;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.upc.dsa.kebabsimulator_android.models.API;
import edu.upc.dsa.kebabsimulator_android.models.Enemy;
import edu.upc.dsa.kebabsimulator_android.models.Mission;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MissionActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MissionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MissionAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        doApiCall();
    }
    private void doApiCall() {
        API apiService = API.retrofit.create(API.class);
        retrofit2.Call<List<Mission>> call = apiService.missions();


        call.enqueue(new retrofit2.Callback<List<Mission>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Mission>> call, retrofit2.Response<List<Mission>> response) {

                if (response.body()==null) {
                    Log.i("onResponse", "OK");
                    Toast.makeText(MissionActivity.this, "Missions loaded", Toast.LENGTH_SHORT).show();
                }
                List<Mission> missions = response.body();
                adapter.setData(missions);
            }

            @Override
            public void onFailure(retrofit2.Call<List<Mission>> call, Throwable t) {
                Log.e("FAIL(onFailure)", "Error in Retrofit: " + t.toString());

            }
        });
    }
}