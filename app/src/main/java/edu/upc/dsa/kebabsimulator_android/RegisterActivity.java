package edu.upc.dsa.kebabsimulator_android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import edu.upc.dsa.kebabsimulator_android.models.API;
import edu.upc.dsa.kebabsimulator_android.models.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private Button registerButton;

    List<User> listaUsers = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_register);
        ProgressBar progressBar = findViewById(R.id.progressBar3);
        progressBar.setVisibility(View.INVISIBLE);

        nameEditText = findViewById(R.id.name);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confirmPassword);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                //String name = nameEditText.getText().toString();
                //String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                if (password.equals(confirmPassword)) {
                    // Aquí puedes manejar el registro del usuario
                    try {
                        if(nameEditText.getText().toString().isEmpty() || passwordEditText.getText().toString().isEmpty() || emailEditText.getText().toString().isEmpty()){
                            Toast.makeText(RegisterActivity.this, "Register Failed", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            addUser(nameEditText.getText().toString(), passwordEditText.getText().toString(), emailEditText.getText().toString());
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                            //SharedPrefManager.getInstance(MainActivity.this).saveUser(new User(usernameField.getText().toString(), passwordField.getText().toString()));

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    // Las contraseñas no coinciden
                    Toast.makeText(RegisterActivity.this, "Las contraseñas no coinciden!", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.INVISIBLE);



            }
        });
    }

    private void addUser(String username, String password, String email) {
        User newUser = new User(username, password, email);
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
}