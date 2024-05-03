package edu.upc.dsa.kebabsimulator_android;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.upc.dsa.kebabsimulator_android.models.Weapon;


public class WeaponsListAdapter extends RecyclerView.Adapter<WeaponsListAdapter.ViewHolder> {
    public List<Weapon> values;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtWeaponName;
        public TextView txtWeaponDescription;
        public TextView txtWeaponDamage;
        public TextView txtWeaponPrice;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtWeaponName = v.findViewById(R.id.weaponName);
            txtWeaponDescription = v.findViewById(R.id.weaponDescription);
            txtWeaponDamage = v.findViewById(R.id.weaponDamage);
            txtWeaponPrice = v.findViewById(R.id.weaponPrice);
            txtWeaponName.setOnClickListener(new OnClickListener() {
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


    public void setData(List<Weapon> myDataset) {
        values = myDataset;
        notifyDataSetChanged();
        Log.d("API", "setData called:"+getItemCount());
    }

    public void add(int position, Weapon item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    public WeaponsListAdapter() {
        values = new ArrayList<>();
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public WeaponsListAdapter(List<Weapon> myDataset) {
        values = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Weapon w = values.get(position);
        final String name = w.getNombre();
        holder.txtWeaponName.setText(name);
        holder.txtWeaponDamage.setText("Daño: " + w.getDamage());
        holder.txtWeaponPrice.setText("Precio: " + w.getPrice());
        /*holder.txtWeaponName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(holder.getAdapterPosition());
            }
        });*/
        holder.txtWeaponDescription.setText("Descripción: " + w.getDescripcion());




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