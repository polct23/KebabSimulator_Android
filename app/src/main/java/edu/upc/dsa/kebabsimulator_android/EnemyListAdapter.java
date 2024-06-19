package edu.upc.dsa.kebabsimulator_android;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.upc.dsa.kebabsimulator_android.models.Ability;
import edu.upc.dsa.kebabsimulator_android.models.Player;
import edu.upc.dsa.kebabsimulator_android.models.Enemy;

import java.util.List;

import edu.upc.dsa.kebabsimulator_android.models.Enemy;

public class EnemyListAdapter extends RecyclerView.Adapter<EnemyListAdapter.EnemyViewHolder> {
    private List<Enemy> enemyList;

    public EnemyListAdapter(List<Enemy> enemyList) {
        this.enemyList = enemyList;
    }

    @NonNull
    @Override
    public EnemyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.enemy_item, parent, false);
        return new EnemyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EnemyViewHolder holder, int position) {
        Enemy currentEnemy = enemyList.get(position);
        holder.nameTextView.setText(currentEnemy.getName());
        holder.speedTextView.setText("Speed: " + currentEnemy.getSpeed());
        holder.meatTextView.setText("Meat: " + currentEnemy.getMeat());
        holder.descritpionTextView.setText("Description: " + currentEnemy.getDescription());
        // Aquí puedes establecer más propiedades del enemigo en las vistas del ViewHolder
    }

    @Override
    public int getItemCount() {
        return enemyList.size();
    }
    public void setData(List<Enemy> myDataset) {
        enemyList = myDataset;
        notifyDataSetChanged();
        Log.d("API", "setData called:"+getItemCount());
    }

    public static class EnemyViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView speedTextView;
        TextView meatTextView;

        TextView descritpionTextView;

        public EnemyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            speedTextView = itemView.findViewById(R.id.speedTextView);
            meatTextView = itemView.findViewById(R.id.meatTextView);
            descritpionTextView = itemView.findViewById(R.id.descriptionTextView);

            // Aquí puedes obtener referencias a más vistas en el item de enemigo
        }
    }
}