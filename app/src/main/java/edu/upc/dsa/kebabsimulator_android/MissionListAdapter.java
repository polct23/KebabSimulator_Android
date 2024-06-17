package edu.upc.dsa.kebabsimulator_android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.upc.dsa.kebabsimulator_android.models.Enemy;
import edu.upc.dsa.kebabsimulator_android.models.Mission;

public class MissionListAdapter extends RecyclerView.Adapter<MissionListAdapter.ViewHolder> {

    private List<Mission> missionList = new ArrayList<>();
    public MissionListAdapter(List<Mission> missionList) {
        this.missionList = missionList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mission_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Mission mission = missionList.get(position);
        holder.nameTextView.setText(mission.getDescription());
        // Aquí puedes establecer más campos de la misión en las vistas del ViewHolder
    }

    @Override
    public int getItemCount() {
        return missionList.size();
    }

    public void setData(List<Mission> missionList) {
        this.missionList = missionList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            // Aquí puedes obtener referencias a más vistas en tu vista de elemento de misión
        }
    }
}