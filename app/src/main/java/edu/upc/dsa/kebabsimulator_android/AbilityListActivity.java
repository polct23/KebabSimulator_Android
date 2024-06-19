package edu.upc.dsa.kebabsimulator_android;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.upc.dsa.kebabsimulator_android.models.API;
import edu.upc.dsa.kebabsimulator_android.models.Mission;
import edu.upc.dsa.kebabsimulator_android.models.Player;
import edu.upc.dsa.kebabsimulator_android.models.SharedPrefManager;
import edu.upc.dsa.kebabsimulator_android.models.Ability;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AbilityListActivity extends AppCompatActivity  {
    private RecyclerView recyclerView;
    private AbilityListAdapter adapter;

    private final String TAG = AbilityListActivity.class.getSimpleName();

    private List<Player> playerList;

    private String username;

    private List<Ability> abilityList2;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_weapons_list);


        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        TextView usernameTextView = findViewById(R.id.playerNameTextView);
        usernameTextView.setText(username);

        ProgressBar progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.INVISIBLE);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        recyclerView = findViewById(R.id.myRecylcerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager

        // Set the adapter
        adapter = new AbilityListAdapter();
        recyclerView.setAdapter(adapter);


        //adapter2 = new MissionListAdapter();
        //recyclerView.setAdapter(adapter2);
        //progressBar.setVisibility(View.VISIBLE);
        try {
            doApiCall();

        } catch (Exception e) {
            Log.w(TAG,"excp", e);
            throw new RuntimeException(e);
        }
        progressBar.setVisibility(View.INVISIBLE);
       // progressBar.setVisibility(View.INVISIBLE);



    }

    //Hcemos una llamada a la API para recibir la lista de habilidades del jugador
    private void doApiCall() {
        API apiService = API.retrofit.create(API.class);
        Call<List<Ability>> call = apiService.getInventario(username);

        call.enqueue(new Callback<List<Ability>>() {
            @Override
            public void onResponse(Call<List<Ability>> call, Response<List<Ability>> response) {
                int code = response.code();

                if (response.isSuccessful() && response.body() != null) {
                    abilityList2 = response.body();
                    adapter.setData(abilityList2);
                    adapter.notifyDataSetChanged();

                    Toast.makeText(AbilityListActivity.this, "Data loaded :" + response.body().get(0).getAbilityName(), Toast.LENGTH_LONG).show();
                } else {
                    Log.w(TAG, "Respuesta no exitosa o cuerpo nulo, HTTP " + response.code());
                    Toast.makeText(AbilityListActivity.this, "Failed to retrieve data. HTTP code: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Ability>> call, Throwable t) {
                Log.e(TAG, "Error in Retrofit: " + t.toString());
                Toast.makeText(AbilityListActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }







}