package edu.upc.dsa.kebabsimulator_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

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
                Intent intent = new Intent(MenuActivity.this, AbilityListActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
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