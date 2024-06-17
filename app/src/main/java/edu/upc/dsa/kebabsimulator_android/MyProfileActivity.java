package edu.upc.dsa.kebabsimulator_android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import edu.upc.dsa.kebabsimulator_android.models.API;
import edu.upc.dsa.kebabsimulator_android.models.Enemy;
import edu.upc.dsa.kebabsimulator_android.models.Player;

public class MyProfileActivity extends AppCompatActivity {

    private EditText emailAddress;
    private EditText currentLevel;
    private EditText money;
    private Button listarMisionesBtn;
    private Button listarAbilitiesBtn;
    private TextView userNameText;
    private final String TAG = MyProfileActivity.class.getSimpleName();

    private String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_profile);
        Intent intent = getIntent();
        userName = intent.getStringExtra("username");



        emailAddress = findViewById(R.id.editTextTextEmailAddress);
        currentLevel = findViewById(R.id.editTextNumberDecimal2);
        money = findViewById(R.id.editTextNumberDecimal3);
        listarMisionesBtn = findViewById(R.id.listarMisionesBtn);
        listarAbilitiesBtn = findViewById(R.id.listarAbilitiesBtn);
        userNameText = findViewById(R.id.playerNameTextView);
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
        listarAbilitiesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyProfileActivity.this, AbilityListActivity.class);
                intent.putExtra("username", userName);
                startActivity(intent);
            }
        });

        listarMisionesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyProfileActivity.this, AbilityListActivity.class);
                intent.putExtra("username", userName);
                startActivity(intent);
            }
        });
    }

    private void doApiCall() {
        API apiService = API.retrofit.create(API.class);
        retrofit2.Call<Player> call = apiService.getPlayerByName(userName);


        call.enqueue(new retrofit2.Callback<Player>() {
            @Override
            public void onResponse(retrofit2.Call<Player> call, retrofit2.Response<Player> response) {

                Player player = response.body();
                userNameText.setText(player.getUserName());
                emailAddress.setText(player.getEmail());
                currentLevel.setText(String.valueOf(player.getCurrentLevel()));
                money.setText(String.valueOf(player.getMoney()));
            }

            @Override
            public void onFailure(retrofit2.Call<Player> call, Throwable t) {
                Log.e("FAIL(onFailure)", "Error in Retrofit: " + t.toString());

            }
        });
    }
}