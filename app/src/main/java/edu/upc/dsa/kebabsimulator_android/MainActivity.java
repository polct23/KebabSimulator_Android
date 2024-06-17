package edu.upc.dsa.kebabsimulator_android;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import edu.upc.dsa.kebabsimulator_android.models.API;
import edu.upc.dsa.kebabsimulator_android.models.SharedPrefManager;
import edu.upc.dsa.kebabsimulator_android.models.Player;


import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText usernameField;
    private EditText passwordField;
    private Button loginButton;

     //SharedPreferences sharedPreferences;

    public int tiempo= 300;
    private Handler handler = new Handler();
    private Runnable runnable;


    List<Player> listaPlayers = new ArrayList<Player>();

    SharedPrefManager sharedPrefManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        TextView noAccountTextView = findViewById(R.id.noAccountTextView);
        String text = "No tienes una cuenta? Regístrate ahora!";
        SpannableString spannableString = new SpannableString(text);


        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };

        spannableString.setSpan(clickableSpan, text.indexOf("Regístrate ahora!"), text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        noAccountTextView.setText(spannableString);
        noAccountTextView.setMovementMethod(LinkMovementMethod.getInstance());
        noAccountTextView.setHighlightColor(Color.TRANSPARENT);

        usernameField = findViewById(R.id.username);
        passwordField = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);



        sharedPrefManager = SharedPrefManager.getInstance(getApplicationContext());
        if (sharedPrefManager.isLoggedIn()) {
            //Redirige al usuario a la actividad que desees

            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            intent.putExtra("username", sharedPrefManager.getUser().getUserName());
            startActivity(intent);
           // finish();
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                try {

                    //sharedPrefManager.saveUser(new Player(usernameField.getText().toString(), passwordField.getText().toString()));
                    //Player loggedInPlayer = sharedPrefManager.getUser();
                    loginUser();



                    // Crear un nuevo Handler y Runnable para desactivar el ProgressBar después de un retraso
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }, tiempo);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                //Intent intent = new Intent(MainActivity.this, WeaponsListActivity.class);
                //startActivity(intent);
            }

        });





    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }

    private void loginUser() throws Exception {
        //String url = "http://10.0.2.2:8080/dsaApp/users/login"; // Cambia esto por la URL de tu servidor

        try {
            doApiCall();
        } catch (Exception e) {
            Log.w("TAG","excp", e);
            throw new RuntimeException(e);
        }
        if(listaPlayers != null){
            if (checkUser(usernameField.getText().toString(), passwordField.getText().toString())) {
                Toast.makeText(MainActivity.this, "Se ha iniciado sesión", Toast.LENGTH_SHORT).show();
                Log.d("Success", "Usuario logeado con éxito");
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                intent.putExtra("username", usernameField.getText().toString());
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }

        }



    }


    private void doApiCall() {
        API apiService = API.retrofit.create(API.class);
        retrofit2.Call<List<Player>> call = apiService.users();


        call.enqueue(new retrofit2.Callback<List<Player>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Player>> call, retrofit2.Response<List<Player>> response) {
                Toast.makeText(MainActivity.this, "Respuesta recibida", Toast.LENGTH_SHORT).show();
                 listaPlayers = response.body();


                if (response.body() != null) {
                    Log.d("Success", "Respuesta recibida con éxito");

                } else {
                    Log.w("FAIL", "Response.body vacío " + response.code());

                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<Player>> call, Throwable t) {
                Log.e("FAIL(onFailure)", "Error in Retrofit: " + t.toString());

            }
        });
    }


    private boolean checkUser(String username, String password) {
        for (Player player : listaPlayers) {
            if (player.getUserName().equals(username) && player.getPassword().equals(password)) {
                sharedPrefManager.saveUser(new Player(usernameField.getText().toString(), passwordField.getText().toString()));

                return true;
            }
        }
        return false;
    }


}
