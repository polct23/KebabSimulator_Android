package edu.upc.dsa.kebabsimulator_android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import edu.upc.dsa.kebabsimulator_android.models.API;
import edu.upc.dsa.kebabsimulator_android.models.User;
import edu.upc.dsa.kebabsimulator_android.models.Weapon;


import org.json.JSONException;
import org.json.JSONObject;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText usernameField;
    private EditText passwordField;
    private Button loginButton;

    private Button registerButton;
    private Button listaButton;
    List<User> listaUsers = new ArrayList<User>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameField = findViewById(R.id.username);
        passwordField = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        listaButton = findViewById(R.id.listaButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    loginUser();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //Intent intent = new Intent(MainActivity.this, WeaponsListActivity.class);
                //startActivity(intent);
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Toast.makeText(MainActivity.this, "REGISTER", Toast.LENGTH_SHORT).show();
                    addUser(usernameField.getText().toString(), passwordField.getText().toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        listaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WeaponsListActivity.class);
                startActivity(intent);
            }
        });

    }

    private void loginUser() throws Exception {
        //String url = "http://10.0.2.2:8080/dsaApp/users/login"; // Cambia esto por la URL de tu servidor
        try {
            doApiCall();
        } catch (Exception e) {
            Log.w("TAG","excp", e);
            throw new RuntimeException(e);
        }
        if(listaUsers!= null){
            if (checkUser(usernameField.getText().toString(), passwordField.getText().toString())) {
                Toast.makeText(MainActivity.this, "Se ha iniciado sesión", Toast.LENGTH_SHORT).show();
                Log.d("Success", "Usuario logeado con éxito");
                Intent intent = new Intent(MainActivity.this, WeaponsListActivity.class);startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }

        }

    }


    private void doApiCall() {
        API apiService = API.retrofit.create(API.class);
        retrofit2.Call<List<User>> call = apiService.users();

        call.enqueue(new retrofit2.Callback<List<User>>() {
            @Override
            public void onResponse(retrofit2.Call<List<User>> call, retrofit2.Response<List<User>> response) {
                Toast.makeText(MainActivity.this, "Respuesta recibida", Toast.LENGTH_SHORT).show();
                 listaUsers = response.body();


                if (response.body() != null) {
                    Log.d("Success", "Respuesta recibida con éxito");

                } else {
                    Log.w("FAIL", "Response.body vacío " + response.code());

                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<User>> call, Throwable t) {
                Log.e("FAIL(onFailure)", "Error in Retrofit: " + t.toString());

            }
        });
    }
    private void addUser(String username, String password) {
        User newUser = new User(username, password);
        API apiService = API.retrofit.create(API.class);
        retrofit2.Call<User> call = apiService.addUser(newUser);

        call.enqueue(new retrofit2.Callback<User>() {
            @Override
            public void onResponse(retrofit2.Call<User> call, retrofit2.Response<User> response) {
                if (response.isSuccessful()) {
                    Log.d("Success", "User added successfully");
                } else {
                    Log.w("FAIL", "Failed to add user, HTTP " + response.code());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<User> call, Throwable t) {
                Log.e("FAIL(onFailure)", "Error in Retrofit: " + t.toString());
            }
        });
    }

    private boolean checkUser(String username, String password) {
        for (User user : listaUsers) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

}
