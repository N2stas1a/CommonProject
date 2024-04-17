package com.example.commonapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String> Cur_Name, Cur_ID, Cur_Abbreviation;
    private FragmentManager fragmentManager;
    private SecondFragment secondFragment = new SecondFragment();

    public CustomAdapter(Context context, ArrayList<String> name, ArrayList<String> id, ArrayList<String> abbreviation, FragmentManager fragmentManager) {
        this.context = context;
        this.Cur_Name = name;
        this.Cur_ID = id;
        this.Cur_Abbreviation = abbreviation;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.TextName.setText(Cur_Name.get(position));
        holder.TextUsage.setText(Cur_ID.get(position));
        holder.TextAbbreviation.setText(Cur_Abbreviation.get(position));

        holder.ROW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView, secondFragment).commit();
                secondFragment.setPosition(holder.getAdapterPosition());
                fragmentTransaction.addToBackStack(null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Cur_Name.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout ROW;
        TextView TextName, TextUsage, TextAbbreviation;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TextName = itemView.findViewById(R.id.Сur_Name);
            TextUsage = itemView.findViewById(R.id.Сur_Abbreviation);
            TextAbbreviation = itemView.findViewById(R.id.Cur_ID);
            ROW = itemView.findViewById(R.id.row);
        }
    }
}
