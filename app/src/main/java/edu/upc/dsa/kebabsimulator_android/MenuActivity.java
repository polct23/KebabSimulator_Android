package edu.upc.dsa.kebabsimulator_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.upc.dsa.kebabsimulator_android.models.Player;
import edu.upc.dsa.kebabsimulator_android.models.SharedPrefManager;

public class MenuActivity extends AppCompatActivity {

    private SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        TextView usernameTextView = findViewById(R.id.playerNameTextView);

        usernameTextView.setText(username);

        Button profileButton = findViewById(R.id.profileButton);

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MyProfileActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        Button startGameButton = findViewById(R.id.startGameButton);
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MenuActivity.this, GameActivity.class);
                //startActivity(intent);
            }
        });

        Button enemiesButton = findViewById(R.id.enemiesButton);
        enemiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, EnemyListActivity.class);
                startActivity(intent);
            }
        });

        Button shopButton = findViewById(R.id.shopButton);
        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, StoreActivity.class);
                startActivity(intent);
            }
        });


    }
}