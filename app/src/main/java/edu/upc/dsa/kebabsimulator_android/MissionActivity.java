package edu.upc.dsa.kebabsimulator_android;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.upc.dsa.kebabsimulator_android.models.API;
import edu.upc.dsa.kebabsimulator_android.models.Enemy;
import edu.upc.dsa.kebabsimulator_android.models.Mission;
import edu.upc.dsa.kebabsimulator_android.models.Player;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MissionActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MissionAdapter adapter;

    private String userName;

    private Player currentPlayer = new Player();

    private List<Mission> missionscompleted;
    private List<Mission> missions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission);

        userName = getIntent().getStringExtra("username");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MissionAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        doApiCall();
        doApiCallUser();

        Button completedMissionsButton = findViewById(R.id.completedMissionsButton);
        completedMissionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                completedMission();
            }
        });
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
                missions = response.body();
                adapter.setData(missions);
            }

            @Override
            public void onFailure(retrofit2.Call<List<Mission>> call, Throwable t) {
                Log.e("FAIL(onFailure)", "Error in Retrofit: " + t.toString());

            }
        });
    }
    private void doApiCallUser() {
        API apiService = API.retrofit.create(API.class);
        retrofit2.Call<Player> call = apiService.getPlayerByName(userName);


        call.enqueue(new retrofit2.Callback<Player>() {
            @Override
            public void onResponse(retrofit2.Call<Player> call, retrofit2.Response<Player> response) {

              currentPlayer = response.body();

            }

            @Override
            public void onFailure(retrofit2.Call<Player> call, Throwable t) {
                Log.e("FAIL(onFailure)", "Error in Retrofit: " + t.toString());

            }
        });
    }

    public void completedMission() {

        for (Mission m : missions) {
            if (m.getIdMission() < currentPlayer.getCurrentMission()) {
                missionscompleted.add(m);
            }
        }
        Toast.makeText(MissionActivity.this, "Completed missions;" + missionscompleted.size(), Toast.LENGTH_SHORT).show();
        if (missionscompleted.isEmpty()) {
            Toast.makeText(MissionActivity.this, "No completed missions", Toast.LENGTH_SHORT).show();
        }
        else
        {
            adapter.setData(missionscompleted);
        }
    }
}