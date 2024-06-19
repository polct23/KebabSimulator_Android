package edu.upc.dsa.kebabsimulator_android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.upc.dsa.kebabsimulator_android.models.Mission;
import android.widget.TextView;

public class MissionAdapter extends RecyclerView.Adapter<MissionAdapter.MissionViewHolder> {
    private List<Mission> missions;

    public MissionAdapter(List<Mission> missions) {
        this.missions = missions;
    }
    public void setData(List<Mission> newMissions) {
        this.missions = newMissions;
        notifyDataSetChanged();
    }
    @Override
    public MissionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mission_item, parent, false);
        return new MissionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MissionViewHolder holder, int position) {
        Mission mission = missions.get(position);
        holder.idMissionTextView.setText("ID: " + String.valueOf(mission.getIdMission()));
        holder.rewardTextView.setText("Recompensa: " + String.valueOf(mission.getReward()));
        holder.descriptionTextView.setText("Descripción: "+mission.getDescription());
    }

    @Override
    public int getItemCount() {
        return missions.size();
    }

    public void updateData(List<Mission> newMissions) {
        this.missions = newMissions;
        notifyDataSetChanged();
    }

    public static class MissionViewHolder extends RecyclerView.ViewHolder {
        TextView idMissionTextView;
        TextView rewardTextView;
        TextView descriptionTextView;

        public MissionViewHolder(View itemView) {
            super(itemView);
            idMissionTextView = itemView.findViewById(R.id.idMissionTextView);
            rewardTextView = itemView.findViewById(R.id.rewardTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }
    }
}