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

import edu.upc.dsa.kebabsimulator_android.models.API;
import edu.upc.dsa.kebabsimulator_android.models.Player;
import edu.upc.dsa.kebabsimulator_android.models.SharedPrefManager;

public class MyProfileActivity extends AppCompatActivity {

    private EditText emailAddress;
    private EditText currentLevel;
    private EditText money;
    private Button listarMisionesBtn;
    private Button listarAbilitiesBtn;
    private TextView userNameText;
    private final String TAG = MyProfileActivity.class.getSimpleName();
    private SharedPrefManager sharedPrefManager;


    private String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_my_profile);
            Intent intent = getIntent();
            userName = intent.getStringExtra("username");

        sharedPrefManager = SharedPrefManager.getInstance(getApplicationContext());


        emailAddress = findViewById(R.id.editTextTextEmailAddress);
            currentLevel = findViewById(R.id.editTextNumberDecimal2);
            money = findViewById(R.id.editTextNumberDecimal3);

            listarAbilitiesBtn = findViewById(R.id.listarAbilitiesBtn);
            userNameText = findViewById(R.id.usernameTextView);
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
        Button inventoryButton = findViewById(R.id.inventoryButton);
        inventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyProfileActivity.this, MissionActivity.class);
                intent.putExtra("username", userName);
                startActivity(intent);
            }
        });
        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
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


    private void logoutUser() {
        sharedPrefManager.logout();
        Intent intent = new Intent(MyProfileActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}