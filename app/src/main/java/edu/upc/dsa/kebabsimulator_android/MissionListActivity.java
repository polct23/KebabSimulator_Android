package edu.upc.dsa.kebabsimulator_android;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.concurrent.CountDownLatch;


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
import edu.upc.dsa.kebabsimulator_android.models.Enemy;
import edu.upc.dsa.kebabsimulator_android.models.Mission;
import edu.upc.dsa.kebabsimulator_android.models.Player;

public class MissionListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MissionListAdapter adapter;
    private Button misionesCompletadasBtn;
    private final String TAG = MissionListActivity.class.getSimpleName();
    private String username;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mission_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        misionesCompletadasBtn = findViewById(R.id.listarMisionesBtn);

        recyclerView = findViewById(R.id.myRecylcerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Mission> missionList = new ArrayList<>();


        adapter = new MissionListAdapter(missionList);
        recyclerView.setAdapter(adapter);


        doApiCall1();

        misionesCompletadasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listarMisiones();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    private void listarMisiones() {

        try {
            doApiCall2();
        } catch (Exception e) {
            Log.w(TAG, "excp", e);
            throw new RuntimeException(e);
        }
        List<Mission> misionesCompletadas = new ArrayList<>();
        for (int i = 0; i < player.getCurrentMission(); i++) {
            misionesCompletadas.add(missionList.get(i));
        }
        adapter.setData(misionesCompletadas);
    }

    static List<Mission> missionList = new ArrayList<>();
    static Player player;

    private void doApiCall1() {
        API apiService = API.retrofit.create(API.class);
        retrofit2.Call<List<Mission>> call = apiService.missions();
        call.enqueue(new retrofit2.Callback<List<Mission>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Mission>> call, retrofit2.Response<List<Mission>> response) {

                List<Mission> missions = response.body();
                missionList = missions;
            }

            @Override
            public void onFailure(retrofit2.Call<List<Mission>> call, Throwable t) {
                Log.e("FAIL(onFailure)", "Error in Retrofit: " + t.toString());

            }
        });
    }

    private void doApiCall2() {
        API apiService = API.retrofit.create(API.class);
        retrofit2.Call<Player> call = apiService.getPlayerByName(username);
        call.enqueue(new retrofit2.Callback<Player>() {
            @Override
            public void onResponse(retrofit2.Call<Player> call, retrofit2.Response<Player> response) {
                player = response.body();
            }

            @Override
            public void onFailure(retrofit2.Call<Player> call, Throwable t) {
                Log.e("FAIL(onFailure)", "Error in Retrofit: " + t.toString());

            }
        });
    }
}