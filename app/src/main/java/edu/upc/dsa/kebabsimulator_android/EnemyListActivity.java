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
import edu.upc.dsa.kebabsimulator_android.models.Player;
import edu.upc.dsa.kebabsimulator_android.models.Enemy;

public class EnemyListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EnemyListAdapter adapter;
    private final String TAG = EnemyListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_enemy_list);

        recyclerView = findViewById(R.id.myRecylcerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Enemy> enemyList = new ArrayList<>();


        adapter = new EnemyListAdapter(enemyList);
        recyclerView.setAdapter(adapter);

        try {
            doApiCall();
        } catch (Exception e) {
            Log.w(TAG,"excp", e);
            throw new RuntimeException(e);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void doApiCall() {
        API apiService = API.retrofit.create(API.class);
        retrofit2.Call<List<Enemy>> call = apiService.enemies();


        call.enqueue(new retrofit2.Callback<List<Enemy>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Enemy>> call, retrofit2.Response<List<Enemy>> response) {

                List<Enemy> enemies = response.body();
                adapter.setData(enemies);
            }

            @Override
            public void onFailure(retrofit2.Call<List<Enemy>> call, Throwable t) {
                Log.e("FAIL(onFailure)", "Error in Retrofit: " + t.toString());

            }
        });
    }

}