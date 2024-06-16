package edu.upc.dsa.kebabsimulator_android;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.upc.dsa.kebabsimulator_android.models.Ability;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder>{

    public List<Ability> values;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtAbilityName;
        public TextView txtAbilityDescription;
        //public TextView txtWeaponDamage;
        public TextView txtAbilityPrice;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtAbilityName = v.findViewById(R.id.weaponName);
            txtAbilityDescription = v.findViewById(R.id.weaponDescription);

            txtAbilityPrice = v.findViewById(R.id.weaponPrice);
            txtAbilityName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        remove(position);
                    }
                }
            });
        }
    }


    public void setData(List<Ability> myDataset) {
        values = myDataset;
        notifyDataSetChanged();
        Log.d("API", "setData called:"+getItemCount());
    }

    public void add(int position, Ability item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    public StoreAdapter() {
        values = new ArrayList<>();
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public StoreAdapter(List<Ability> myDataset) {
        values = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public StoreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        StoreAdapter.ViewHolder vh = new StoreAdapter.ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final StoreAdapter.ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Ability w = values.get(position);
        final String name = w.getNombre();
        holder.txtAbilityName.setText(name);

        holder.txtAbilityPrice.setText("Precio: " + w.getPrice());
        /*holder.txtWeaponName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(holder.getAdapterPosition());
            }
        });*/
        holder.txtAbilityDescription.setText("Descripción: " + w.getDescripcion());




      /*  GlideApp.with(holder.icon.getContext())
                .load(c.avatar_url)
                .into(holder.icon);

       */


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }
}
