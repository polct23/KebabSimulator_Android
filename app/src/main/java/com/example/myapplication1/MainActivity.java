package com.example.myapplication1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.example.myapplication1.API.github.API;
import com.example.myapplication1.API.github.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    String nombreUsuario;
    String contraseñaUsuario;
    HashMap<String, Usuario> usuarios = new HashMap<String, Usuario>();
    //List<String> usuarios = new ArrayList<String>();
    List<Usuario> listausuarios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
   public void registerbtnClick(View v)
   {
       EditText editTextNombre = findViewById(R.id.editTextNombre);
        nombreUsuario = editTextNombre.getText().toString();

       EditText editTextContraseña = findViewById(R.id.editTextContraseña);
        contraseñaUsuario = editTextContraseña.getText().toString();
        Usuario usuario = new Usuario(nombreUsuario, contraseñaUsuario);
        usuarios.put(nombreUsuario, usuario);

       Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();


   }
   public void loginbtnClick(View v)
   {
       sincronizarconAPI();

       EditText editTextNombre = findViewById(R.id.editTextNombre);
        nombreUsuario = editTextNombre.getText().toString();

       EditText editTextContraseña = findViewById(R.id.editTextContraseña);
        contraseñaUsuario = editTextContraseña.getText().toString();

        if(usuarios.containsKey(nombreUsuario))
        {
            Usuario usuario = usuarios.get(nombreUsuario);

            if(usuario.getContraseña().equals(contraseñaUsuario))
            {
                System.out.println("Usuario logeado");
                Toast.makeText(this, "Usuario logeado con éxito", Toast.LENGTH_SHORT).show();


                // Iniciar la actividad del menú
                Intent intent = new Intent(this, MenuActivity.class);
                startActivity(intent);

            }
            else
            {
                System.out.println("Contraseña incorrecta");
                Toast.makeText(this, "La contraseña no es correcta", Toast.LENGTH_SHORT).show();

            }
        }
        else
        {
            System.out.println("Usuario no existe");
            Toast.makeText(this, "El usuario no existe", Toast.LENGTH_SHORT).show();

        }

   }
   public void sincronizarconAPI()
   {
       API APIService = API.retrofit.create(API.class);
       Call<List<Usuario>> call = APIService.usuarios();

       call.enqueue(new Callback<List<Usuario>>() {
           @Override
           public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
               /*final TextView textView = (TextView) findViewById(R.id.textView);
               textView.setText(response.body().toString());*/
               Log.d("Succesful", "Ha funcionado");
               /*for (Usuario usuario : response.body()) {
                  usuarios.put(usuario.getNombre(), usuario);

               }*/
               //listausuarios = response.body();
               if(response.body()!=null)
               Log.d("lista","grg"+response.body().size());

               else
                   Log.d("aA","adadajdoiefoiaefihafihafpihaof");


           }
           @Override
           public void onFailure(Call<List<Usuario>> call, Throwable t) {
               /*final TextView textView = (TextView) findViewById(R.id.textView);
               textView.setText("Something went wrong: " + t.getMessage());*/
                System.out.println("Something went wrong: " + t.getMessage());
               Log.e("ERROR", "anindaindilaedfnilaedfianed");


           }
       });
   }
}