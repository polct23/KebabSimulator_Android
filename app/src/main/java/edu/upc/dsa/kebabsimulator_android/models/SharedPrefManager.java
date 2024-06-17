package edu.upc.dsa.kebabsimulator_android.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "my_shared_pref";

    private static SharedPrefManager instance;
    private Context context;

    private SharedPrefManager(Context context) {
        this.context = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefManager(context);
        }
        Log.d("SharedPrefManager", "Context: " + context.toString());
        return instance;
    }

    public void saveUser(Player player) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("idUser", player.getIdPlayer());
        editor.putString("userName", player.getUserName());
        editor.putString("password", player.getPassword());

        editor.apply();
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("userName", null) != null;
    }

    public Player getUser() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String userName = sharedPreferences.getString("idUser", null);
        String idUser = sharedPreferences.getString("userName", null);
        String password = sharedPreferences.getString("password", null);

        Log.d("SharedPrefManager", "GetUser - idUser: " + idUser + ", userName: " + userName + ", password: " + password);

        return new Player(idUser, userName, password);
    }

    public void logout() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
